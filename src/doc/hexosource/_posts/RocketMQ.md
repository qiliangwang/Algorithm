---
title: RocketMQ
date: 2019-03-13 12:48:55
tags:
---

## RocketMQ安装

http://localhost:8000/api/static/file/1/temp.zip

Hosts 添加信息

```shell
vim /etc/hosts
```

```
127.0.0.1 rocketmq-nameserver
127.0.0.1 rocketmq-master
```

```
apache-rocketmq.tar.gz
```

```shell
vaderwang@vaderwang-x399:~/software/rocketmq-4.3.0$  tar -zxf apache-rocketmq.tar.gz 

vaderwang@vaderwang-x399:~/software$ ln -s rocketmq-4.3.0 rocketmq
```

```shell
$ mkdir /home/vaderwang/software/rocketmq/store
$ mkdir /home/vaderwang/software/rocketmq/logs
$ mkdir /home/vaderwang/software/rocketmq/store/commitlog
$ mkdir /home/vaderwang/software/rocketmq/store/consumequeue
$ mkdir /home/vaderwang/software/rocketmq/store/index
```

```shell
$ vim /home/vaderwang/software/rocketmq/conf/2m-2s-sync/broker-a.properties
```

```properties
#所属集群名字
brokerClusterName=rocketmq-cluster
#broker 名字,注意此处不同的配置文件填写的不一样
brokerName=broker-a|broker-b
#0 表示 Master,>0 表示 Slave
brokerId=0
#nameServer 地址,分号分割
namesrvAddr=rocketmq-nameserver:9876
#在发送消息时,自动创建服务器不存在的 topic,默认创建的队列数
defaultTopicQueueNums=4
#是否允许 Broker 自动创建 Topic,建议线下开启,线上关闭
autoCreateTopicEnable=true
#是否允许 Broker 自动创建订阅组,建议线下开启,线上关闭
autoCreateSubscriptionGroup=true
#Broker 对外服务的监听端口
listenPort=10911
#删除文件时间点,默认凌晨 4 点
deleteWhen=04
#文件保留时间,默认 48 小时
fileReservedTime=120
#commitLog 每个文件的大小默认 1G
mapedFileSizeCommitLog=1073741824
#ConsumeQueue 每个文件默认存 30W 条,根据业务情况调整
mapedFileSizeConsumeQueue=300000
#destroyMapedFileIntervalForcibly=120000
#redeleteHangedFileInterval=120000
#检测物理文件磁盘空间
diskMaxUsedSpaceRatio=88
#存储路径
storePathRootDir=/home/vaderwang/software/rocketmq/store
#commitLog 存储路径
storePathCommitLog=/home/vaderwang/software/rocketmq/store/commitlog
#消费队列存储路径存储路径
storePathConsumeQueue=/home/vaderwang/software/rocketmq/store/consumequeue
#消息索引存储路径
storePathIndex=/home/vaderwang/software/rocketmq/store/index
#checkpoint 文件存储路径
storeCheckpoint=/home/vaderwang/software/rocketmq/store/checkpoint
#abort 文件存储路径
abortFile=/home/vaderwang/software/rocketmq/store/abort
#限制的消息大小
maxMessageSize=65536
#flushCommitLogLeastPages=4
#flushConsumeQueueLeastPages=2
#flushCommitLogThoroughInterval=10000
#flushConsumeQueueThoroughInterval=60000
#Broker 的角色
#- ASYNC_MASTER 异步复制 Master
#- SYNC_MASTER 同步双写 Master
#- SLAVE
brokerRole=ASYNC_MASTER
#刷盘方式
#- ASYNC_FLUSH 异步刷盘
#- SYNC_FLUSH 同步刷盘
flushDiskType=ASYNC_FLUSH
#checkTransactionMessageEnable=false
#发消息线程池数量
#sendMessageThreadPoolNums=128
#拉消息线程池数量
#pullMessageThreadPoolNums=128
```

```shell
cd /home/vaderwang/software/rocketmq/conf && sed -i 's#${user.home}#/home/vaderwang/software/rocketmq#g' *.xml
```

rocketmq/bin/

```shell
$ nohup sh mqnamesrv &

$ nohup sh mqbroker -c /home/vaderwang/software/rocketmq/conf/2m-noslave/broker-a.properties >/dev/null 2>&1 &

$ netstat -ntlp
```

```shell
$ git clone git@github.com:apache/rocketmq-externals.git
```

http://localhost:8080/#/

```shell
$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_startup

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_error

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_event

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_usage

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_page

$ bin/kafka-topics.sh --zookeeper localhost:2181 --list
topic_app_error
topic_app_event
topic_app_page
topic_app_startup
topic_app_usage

$ bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic_app_page

$ bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic topic_app_startup
```

