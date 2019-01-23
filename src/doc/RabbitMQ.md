---
title: RabbitMQ
date: 2018-12-03 20:38:50
tags:
---



## 安装RabbitMQ

```shell
[root@localhost vader]# yum install build-essential openssl openssl-devel unixODBC unixODBC-devel make gcc gcc-c++ kernel-devel m4 ncurses-devel tk tc xz
```

```shell
yum -y install wget httpd-tools vim
```

下载对应的安装包

```shell
[root@localhost Software]# rpm -ivh erlang-18.3-1.el7.centos.x86_64.rpm 
```

```shell
[root@localhost Software]# rpm -ivh socat-1.7.3.2-1.1.el7.x86_64.rpm
```

```shell
[root@localhost Software]# rpm -ivh rabbitmq-server-3.6.5-1.noarch.rpm 
```

修改配置文件

配置文件在/usr/lib/rabbitmq/lib/rabbitmq_server-3.6.5/ebin目录下的rabbit.app

```json
{loopback_users, [<<"guest">>]},{loopback_users, [guest]}
```

服务启动:&代表后台启动

```shell
rabbitmq-server start &
```

```shell
[root@localhost vader]# lsof -i:5672
COMMAND    PID     USER   FD   TYPE DEVICE SIZE/OFF NODE NAME
beam.smp 25720 rabbitmq   52u  IPv6  70423      0t0  TCP *:amqp (LISTEN)
```

这样就安装成功了

服务停止

```shell
rabbitmqctl stop_app
```

管理插件

```shell
rabbitmq-plugins enable rabbitmq_management
```

http://192.168.210.100:15672/#/

http://localhost:15672/#/

### 外网访问

CentOS7关闭防火墙：

```shell
[root@localhost vader]# systemctl stop firewalld.service
```

查看防火墙状态

```she&#39;l&#39;l
[root@localhost vader]# firewall-cmd --state
```

## windows

链接：https://pan.baidu.com/s/1nYYe5cP20BpZeoTtzEsv7w 
提取码：ppuf 

```shell
C:\Program Files (x86)\RabbitMQ Server\rabbitmq_server-3.4.1\sbin>rabbitmq-plugins enable rabbitmq_management
```

## SpringBoot&&RabbitMQ





