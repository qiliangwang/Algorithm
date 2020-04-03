---
layout: w
title: ZooKeeper
date: 2019-01-28 21:12:47
tags:
---

https://archive.apache.org/dist/zookeeper/

下载对应的版本(3.4.9)

启动服务端

```shell
vaderwang@vaderwang-x399:~/software/zookeeper-3.4.9$ ./bin/zkServer.sh start
```

客户端连接

```shell
vaderwang@vaderwang-x399:~/software/zookeeper-3.4.9$ ./bin/zkCli.sh 
```

查看znode结构

```shell
[zk: localhost:2181(CONNECTED) 0] help
ZooKeeper -server host:port cmd args
	stat path [watch]
	set path data [version]
	ls path [watch]
	delquota [-n|-b] path
	ls2 path [watch]
	setAcl path acl
	setquota -n|-b val path
	history 
	redo cmdno
	printwatches on|off
	delete path [version]
	sync path
	listquota path
	rmr path
	get path [watch]
	create [-s] [-e] path data acl
	addauth scheme auth
	quit 
	getAcl path
	close 
	connect host:port
[zk: localhost:2181(CONNECTED) 1] ls /
[zookeeper]
[zk: localhost:2181(CONNECTED) 2] ls /zookeeper 
[quota]
[zk: localhost:2181(CONNECTED) 3] ls /zookeeper/quota
[]
[zk: localhost:2181(CONNECTED) 4] 
```

关闭客户端连接

```
ctr + c
```

常用命令