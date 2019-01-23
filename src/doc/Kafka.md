---
title: Kafka
date: 2019-01-28 23:18:27
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

