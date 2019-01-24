---
title: JVM
date: 2018-12-16 23:09:07
tags:
---

## JVM

```
-Xmx32M -Xms32M

-XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
```

```java
@RestController
public class MemoryController {
	
	private List<User>  userList = new ArrayList<User>();
	private List<Class<?>>  classList = new ArrayList<Class<?>>();
	
	/**
	 * -Xmx32M -Xms32M
	 * */
	@GetMapping("/heap")
	public String heap() {
		int i=0;
		while(true) {
			userList.add(new User(i++, UUID.randomUUID().toString()));
		}
	}
	
	/**
	 * -XX:MetaspaceSize=32M -XX:MaxMetaspaceSize=32M
	 * */
	@GetMapping("/nonheap")
	public String nonheap() {
		while(true) {
			classList.addAll(Metaspace.createClasses());
		}
	}
	
}
```

内存益处自动导出

```
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./
```

使用Jmap手动导出

```
jmap -dump:format=b,file=heap.hprof 26463

jstack 26463 > result.txt
```

```
print "%x" 4481
ps -ef | grep java
```

JVisualVm



GCViewer

https://github.com/chewiebug/GCViewer

```shell
$ git clone git@github.com:chewiebug/GCViewer.git

$ cd GCViewer

$ mvn clean package -Dmaven.test.skip

$ java -jar gcviewer-1.36-SNAPSHOT.jar 
```



```shell
$ javap -verbose xxxx.class > xxxx.txt
```





