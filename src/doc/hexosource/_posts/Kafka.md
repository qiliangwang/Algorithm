---
title: Kafka
date: 2019-07-10 20:18:12
tags:
---

安装

https://www.apache.org/dyn/closer.cgi?path=/kafka/2.1.0/kafka_2.11-2.1.0.tgz

```shell
tar -zxvf kafka_2.11-2.1.0.tgz 
```

kafka是基于zookeeper的

启动zookeeper

```shell
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ ls bin | grep zoo
zookeeper-security-migration.sh
zookeeper-server-start.sh
zookeeper-server-stop.sh
zookeeper-shell.sh

vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ nohup ./bin/zookeeper-server-start.sh  config/zookeeper.properties &
```

启动kafka

```
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ nohup ./bin/kafka-server-start.sh config/server.properties &
```

启动topic

```shell
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic hello
```

查看topic

```
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ ./bin/kafka-topics.sh --list --zookeeper localhost:2181
```

使用topic发送消息

```
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ ./bin/kafka-console-producer.sh --broker-list localhost:9092 --topic hello
```

接受消息

```shell
vaderwang@vaderwang-x399:~/software/kafka_2.11-2.1.0$ ./bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic hello --from-beginning
```

## Kafka shell

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


# kafka安装

消息系统分为2类，一种是点对点的消息系统一种是发布订阅的消息系统

![](Kafka\Screenshot-04.png)


点对点的消息系统使用一个队列存储消息，当消息消费后从队列中删除，在这种模式下，即使多个消费者同时消费，消费的顺序也是一致的。

发布订阅系统将消息持久化到一个Topic中，消费者可以订阅多个Topic，同一条数据可以被多个消费者消费，消息不会被删除。

kafka是一个分布式的消息订阅系统，有着消费着组的概念（一个消费者组可以消费全部消息），多出partition数量的consumer不会被分配消息。所以consumer的数量不会多于partition。

![](Kafka\Screenshot-05.png)
![](Kafka\Screenshot-06.png)

https://kafka.apache.org/downloads

```shell
tar -zxvf kafka_2.12-2.1.0.tgz
```

```
kafka_2.12-2.1.0/config/server.properties
broker.id=1
log.dir=/kafka/logs
```

启动 ZK Kafka 安装包自带 ZK，可以单节点启动

```shell
bin/zookeeper-server-start.sh -daemon config/zookeeper.properties
```

启动 Kafka 服务器

```
bin/kafka-server-start.sh config/server.properties
```

创建 Topic（test）

```
bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test
```

Topic 列表 

```
bin/kafka-topics.sh --list --zookeeper localhost:2181
```

启动 Producer 

```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic test
```

启动 Consumer

```
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092
--topic test --from-beginning
```

Topic 相关信息（test）

```
bin/kafka-topics.sh --describe --zookeeper localhost:2181 --topic test
```



## 安装GUI界面

使用yahoo的kafka-manager

到github下载编译后会有一个zip包，修改里面的zookeeper地址即可使用，这里我使用了scp从本地上传（之前已经编译，懒）

```shell
$ scp ./kafka-manager-2.0.0.2.zip wangql@192.168.0.108:/home/wangql/Documents/bin/
```

http://localhost:9000/

![](Kafka\Screenshot-00.png)
![](Kafka\Screenshot-01.png)
![](Kafka\Screenshot-02.png)
![](Kafka\Screenshot-03.png)



使用DockerCompose安装Kafka

```yaml
version: '2.1'

services:
  zoo1:
    image: zookeeper:3.4.9
    hostname: zoo1
    ports:
      - "2181:2181"
    environment:
        ZOO_MY_ID: 1
        ZOO_PORT: 2181
        ZOO_SERVERS: server.1=zoo1:2888:3888
    volumes:
      - ./zk-single-kafka-single/zoo1/data:/data
      - ./zk-single-kafka-single/zoo1/datalog:/datalog

  kafka1:
    image: confluentinc/cp-kafka:5.2.2
    hostname: kafka1
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: LISTENER_DOCKER_INTERNAL://kafka1:19092,LISTENER_DOCKER_EXTERNAL://${DOCKER_HOST_IP:-127.0.0.1}:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: LISTENER_DOCKER_INTERNAL:PLAINTEXT,LISTENER_DOCKER_EXTERNAL:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: LISTENER_DOCKER_INTERNAL
      KAFKA_ZOOKEEPER_CONNECT: "zoo1:2181"
      KAFKA_BROKER_ID: 1
      KAFKA_LOG4J_LOGGERS: "kafka.controller=INFO,kafka.producer.async.DefaultEventHandler=INFO,state.change.logger=INFO"
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    volumes:
      - ./zk-single-kafka-single/kafka1/data:/var/lib/kafka/data
    depends_on:
      - zoo1

```




