---
title: Concurrency
date: 2018-10-09 11:23:53
tags:
---

## 项目工程搭建

建立一个SpringBoot的工程，加入一些必要的依赖

PS：选中一段代码后按TAB键可以使代码整体右移，SHIFT+TAB可以使整体左移。

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

写一些注解来为以后的代码作为标注

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface Recommend {

    String value() default "";
}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotRecommend {

    String value() default "";
}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface ThreadSafe {

    String value() default "";
}
```

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotThreadSafe {

    String value() default "";
}
```

使用代码模拟一下并发，作为HelloWorld

```java
@Slf4j
@NotThreadSafe
public class ConcurrencyTest {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}", count);
    }

    private static void add(){
        count ++;
    }
}
```

Result:

16:45:52.580 [main] INFO com.iceberg.examples.example00 - count4929



## 线程安全性

### 原子性

之前的直接count++的code是线程不安全的，要做到线程安全，先从Atomic开始说起。首先把count改为new AtomicInteger(0)，count++改为count.incrementAndGet()，++count对应为count.getAndIncrement();

```java
@Slf4j
@ThreadSafe
public class AtomicIntegerExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}", count.get());
    }

    private static void add(){
        count.incrementAndGet();
    }
}
```

Result:

18:56:28.069 [main] INFO com.iceberg.examples.AtomicIntegerExample - count5000



可以发现现在程序已经变成了线程安全的了，线程安全的秘密就在下面这个函数中，当当前值var2（处于工作内存）与底层值var5（处于主内存中）相同的话，就把var5更新为var5+var4，要是不同的话，就一直取出底层的值与当前预期的值作比较，要是相同了，就更新。也就是靠着这种不断比较的过程可以保证数据最终的一致。

```java
public final int getAndAddInt(Object var1, long var2, int var4) {
        int var5;
        do {
            var5 = this.getIntVolatile(var1, var2);
        } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));

        return var5;
    }
```

LongAddr也可以保证数据的一致

```java
@Slf4j
@ThreadSafe
public class LongAdderExample {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count{}", count);
    }

    private static void add(){
        count.increment();
    }
}
```

Result:

18:57:12.374 [main] INFO com.iceberg.examples.LongAdderExample - count5000



在线程竞争激烈的时候使用LongAddr会有比较好的性能，AtomicLong是保证全局唯一的。

#### AtomicReference&&AtomicReferenceFieldUpdater

```java
@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(1, 3);
        count.compareAndSet(3, 4);
        count.compareAndSet(2, 3);
        count.compareAndSet(3, 4);
        count.compareAndSet(3, 5);
        log.info("count:{}", count.get());
    }
}
```

Result:

18:54:46.107 [main] INFO com.iceberg.examples.AtomicReferenceExample - count:4



```java
@Slf4j
@ThreadSafe
public class AtomicFieldUpdaterExample {

    private static AtomicIntegerFieldUpdater<AtomicFieldUpdaterExample> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicFieldUpdaterExample.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicFieldUpdaterExample example = new AtomicFieldUpdaterExample();

        if (updater.compareAndSet(example, 100, 120)){
            log.info("update success, {}", example.getCount());
        }
        if (updater.compareAndSet(example, 100, 120)){
            log.info("update success, {}", example.getCount());
        }else {
            log.info("update failed, {}", example.getCount());
        }
    }
}
```

Result:

18:52:08.986 [main] INFO com.iceberg.examples.AtomicFieldUpdaterExample - update success, 120
18:52:08.989 [main] INFO com.iceberg.examples.AtomicFieldUpdaterExample - update failed, 120



#### AtomicBoolean

很多时候只需要改变某个值一次，改变一次即可，这时候可以使用AtomicBoolean

```java
@Slf4j
@ThreadSafe
public class AtomicBooleanExample {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++){
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                }catch (Exception e){
                    log.error("Exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("isHappened: {}", isHappened.get());
    }

    private static void test(){
        if (isHappened.compareAndSet(false, true)){
            log.info("executed");
        }
    }
}
```

Result:

19:16:35.521 [pool-1-thread-1] INFO com.iceberg.examples.AtomicBooleanExample - executed
19:16:35.540 [main] INFO com.iceberg.examples.AtomicBooleanExample - isHappened: true



#### Synchronized&&Lock

Synchronized：依赖于JVM，在这个作用对象的作用范围内，都是同一时刻只有一个线程可以操作。

Lock：依赖特殊的CPU指令，代码实现，比较常见的有ReentrantLock

这里先介绍Synchronized，Synchronized有四种修饰，分别是修饰代码块，修饰函数，修饰静态方法，修饰一个类。

修饰代码块

```java
public void sync(int j) {
    synchronized (this) {
        for (int i = 0; i < 10; i++) {
            log.info("sync {} - {}", j, i);
        }
    }
}
```

修饰方法

```java
public synchronized void test2(int j) {
    for (int i = 0; i < 10; i++) {
        log.info("sync {} - {}", j, i);
    }
}
```

修饰类

```java
public static void sync(int j) {
    synchronized (SynchronizedExample.class) {
        for (int i = 0; i < 10; i++) {
            log.info("sync {} - {}", j, i);
        }
    }
}
```

修饰静态方法

```java
public static synchronized void sync(int j) {
    for (int i = 0; i < 10; i++) {
        log.info("sync {} - {}", j, i);
    }
}
```

Synchronized：不可中断锁，适合竞争不激烈，可读性好

Lock：可中断锁，多样化同步，竞争激烈时能维持常态

Atomic：竞争激烈时可维持常态，性能比Lock好，只能同步一个值。

### 可见性

### 有序性







