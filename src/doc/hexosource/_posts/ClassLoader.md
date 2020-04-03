---
title: Class Loader
date: 2019-07-16 09:09:38
tags:
---

Java是如何运行的，java是将.java文件编译为.class文件，然后这些class文件是可以在各个平台下运行的，也就是所谓的跨平台，只需要不同的平台实现自己的JVM就可以了。而Java的class文件里面的内容我们可以通过javap命令来看字节码的内容。java先生成字节码，然后把

我们先看这段简单Java代码对应的字节码是怎么样的。

```java
public class Sample {
    public static void main(String[] args) {
        int i=1,j=5;
        i++;
        ++j;
        System.out.println(i);
        System.out.println(j);
    }
}
```

```shell
 javap -c com.iceberg.bytecode.Sample
```

```
Compiled from "ByteCodeSample.java"
public class com.iceberg.bytecode.Sample {
  public com.iceberg.bytecode.Sample();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static void main(java.lang.String[]);
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_5
       3: istore_2
       4: iinc          1, 1
       7: iinc          2, 1
      10: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      13: iload_1
      14: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
      17: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
      20: iload_2
      21: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
      24: return
}

```



Java reflect

 ClassLoader

loadClass其实是调用了return loadClass(name, false);

```java
/**
 * Loads the class with the specified <a href="#name">binary name</a>.
 * This method searches for classes in the same manner as the {@link
 * #loadClass(String, boolean)} method.  It is invoked by the Java virtual
 * machine to resolve class references.  Invoking this method is equivalent
 * to invoking {@link #loadClass(String, boolean) <tt>loadClass(name,
 * false)</tt>}.
 *
 * @param  name
 *         The <a href="#name">binary name</a> of the class
 *
 * @return  The resulting <tt>Class</tt> object
 *
 * @throws  ClassNotFoundException
 *          If the class was not found
 */
public Class<?> loadClass(String name) throws ClassNotFoundException {
    return loadClass(name, false);
}
```

这个方法就是真正loadClass的逻辑了

```java
protected Class<?> loadClass(String name, boolean resolve)
    throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            long t0 = System.nanoTime();
            try {
                if (parent != null) {
                    c = parent.loadClass(name, false);
                } else {
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                long t1 = System.nanoTime();
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```

可以看到这段代码中，先查看parent中是否加载了对应的class，这就是ClassLoader的双亲委派机制。

```java
if (parent != null) {
    c = parent.loadClass(name, false);
} else {
    c = findBootstrapClassOrNull(name);
}
```



类的加载方式有2种，一种是隐式加载，比如我们 new 一个对象的时候就是这样加载的

Class.forName 和 loaderClass的区别

类的装载过程有三个步骤（加载， 连接， 初始化）

加载 

      1. 通过classloader加载class文件字节码，生成class对象

链接

      1. 校验：检查加载的class的正确性和安全性
    
      2. 准备：为类变量分配存储空间并设置类变量初始值
    
      3. 解析：JVM将常量池内的符号引用转坏为直接引用

初始化

      1. 执行变量赋值和静态代码块