使用thread local来实现多线程计算

先看一个简单的web项目

```shell
C:\Users\Administrator\Downloads\Apache24\bin>ab -n 10000 -c 100 localhost:8080/add
```



```java
package com.iceberg.netty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class StatController {

    static Set<Val<Integer>> set = new HashSet<>();

    static Set<Val<Integer>> concurrentSet = null;

    static {
        ConcurrentHashMap<Val<Integer>, Integer> map = new ConcurrentHashMap<>();
        concurrentSet = map.newKeySet();
    }

    static ThreadLocal<Val<Integer>> c = new ThreadLocal<Val<Integer>>() {
        @Override
        protected Val<Integer> initialValue() {
            Val<Integer> v = new Val<>();
            v.set(0);
            //if no synchronized then failed or using concurrent Set
//            synchronized (StatController.class) {
//                set.add(v);
//            }
            concurrentSet.add(v);
            return v;
        }
    };

    void doAdd() throws InterruptedException {
        Thread.sleep(100);
        Val<Integer> v = c.get();
        v.set(v.get() + 1);
    }
    @GetMapping("/stat")
    public Integer stat() {
        System.out.print("set size is:" + set.size());
        return concurrentSet.stream()
                .map(x -> x.get())
                .reduce((x, y) -> x + y).get();
    }

    @GetMapping("/add")
    public Integer add() {
        try {
            doAdd();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

```



threadLocal在web领域的使用

先定义一个Holder类

```java
public class RequestHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<SysUser>();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();

    public static void add(User sysUser) {
        userHolder.set(sysUser);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static User getCurrentUser() {
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        userHolder.remove();
        requestHolder.remove();
    }
}

```

```java
package com.mmall.common;

import com.mmall.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {

    private static final String START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI().toString();
        Map parameterMap = request.getParameterMap();
        log.info("request start. url:{}, params:{}", url, JsonMapper.obj2String(parameterMap));
        long start = System.currentTimeMillis();
        request.setAttribute(START_TIME, start);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String url = request.getRequestURI().toString();
//        long start = (Long) request.getAttribute(START_TIME);
//        long end = System.currentTimeMillis();
//        log.info("request finished. url:{}, cost:{}", url, end - start);
        removeThreadLocalInfo();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String url = request.getRequestURI().toString();
        long start = (Long) request.getAttribute(START_TIME);
        long end = System.currentTimeMillis();
        log.info("request completed. url:{}, cost:{}", url, end - start);

        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo() {
        RequestHolder.remove();;
    }
}

```

preHandle

调用时间：Controller方法处理之前

执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序一个接一个执行

若返回false，则中断执行，注意：不会进入afterCompletion

 

postHandle

调用前提：preHandle返回true

调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作

执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。

备注：postHandle虽然post打头，但post、get方法都能处理

 

afterCompletion

调用前提：preHandle返回true

调用时间：DispatcherServlet进行视图的渲染之后

多用于清理资源


