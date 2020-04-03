---
title: Docker Basic
date: 2018-12-05 18:54:42
tags:
---

## Container

Container被翻译为容器技术，听起来很酷，但是容器是什么，Tomcat是容器，很多东西也是容器，按照名字来容器就是放东西的。但是Tomcat和Docker有什么区别？其实Container还有一个意思是集装箱，看看Docker的logo就知道，Docker的Container的意思应该是指集装箱。既然Docker重要的技术是叫做集装箱，那么Docker要实现的功能就和集装箱分不开了。

## 安装Docker

https://store.docker.com/editions/community/docker-ce-desktop-windows

下载Docker, windows下的安装步骤就不多介绍了。

由于墙的原因，Docker再pull的过程中，速度超级慢，所以我们需要使用国内的加速器来加速。

https://www.daocloud.io/mirror

在桌面右下角状态栏中右键 docker 图标，修改在 Docker Daemon 标签页中的 json ，把下面的地址:

```
http://f1361db2.m.daocloud.io
```

加到" `registry-mirrors`"的数组里。点击 Apply 。

## Docker的基本概念

### 镜像

搜索镜像

```powershell
docker search java
```

获取镜像

```powershell
docker pull java
```

查看镜像

```powershell
docker images
```

导出镜像 PS：在windows平台需要在盘符根目录创建一个home的文件夹。

```powershell
Liunx:   docker save java > /home/java.tar.gz
Windows: docker save java -o /home/java.tar
```

导入镜像

```powershell
Linux:   docker load < /home/java.tar.gz
Windows: docker load -i /home/java.tar
```

删除镜像

```powershell
docker rmi java
```

### 容器

首先，什么是container，一个image可以生成多个container，用OOP的概念类比的话，image就是类，而container就是实例了。

运行一个容器十分简单，第一步先拉取一个docker镜像，然后run就可以了！

```powershell
docker pull hello-world
```

```powershell
docker run hello-world
```

当你运行这个docker容器的时候，会得到下面的输出：

```cmd
Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/
```

当我们需要看本地正在运行的容器的时候我们可以用：

```powershell
docker container ls
```

但是当你运行上面那句命令的时候，你会发现，没有任何容器，这是因为Docker的容器运行后就退出了，这时候使用另一个命令，列举出所有的容器。

```powershell
docker container ls -a
```

那么怎样才可以让container一直运行，我们先使用 -it(Interaction tty)来运行。

```powershell
docker run -it centos
```

当然可以输入exit来退出。

接下来怎么删除一个container，docker container rm id||name: 要是嫌弃docker container rm 太长就直接使用docker rm也是一样的！

```powershell
docker container rm condescending_clarke

docker container rm eb831c00873e
```

同理要想删除image就用，同理为了方便可以使用docker rmi

```
docker image rm id||name:
```

要是容器太多想一次性清除

```powershell
docker rm $(docker container ls -aq)
docker container ls -f "status=exited" -q
```

```powershell
docker run -it centos
yum install -y vim
docker commit image_name vvader/centos-vim
```

要构建自己的镜像使用commit固然可以，但不是最好的，接下来通过写Dockerfile来生成Docker image

```dockerfile
FROM centos
RUN yum install -y vim
```

```powershell
docker build -t vaderwang/centos-vim .
```

### Dockerfile

Dockerfile的功能十分强大，现在梳理一下Dockerfile的基本语法

FROM

LABEL

RUN

WORKDIR

ADD || COPY：ADD除了COPY还会解压

ENV

#### 简单的Dockerfile实践



### 网络



### 存储



## Docker实战

人人网前后端部署+WordPress等等

现在先从最简单的开始，部署一个前后端的高可用项目。这是人人网开源的项目，质量是可以的，至少对于一个大学没毕业的我而言，还是有很多值得学习的地方，所以一开始拿这个作为实践是不错的选择。

首先进入https://www.renren.io/ 这个网址，下载人人的项目renren-fast。

![](DockerZero\00.png)

在https://github.com/daxiongYang/renren-fast-vue 下载人人的前端项目，https://gitee.com/renrenio/renren-fast 下载人人的后端项目。

首先，把项目跑起来。新建数据库，导入项目里面的mysql.sql文件。

```mssql
CREATE SCHEMA renren_fast;
```

![](DockerZero\01.png)

这样就成功导入了数据库的信息了。

PS: 安装MySQL的一点小BUG。由于最新MySQL加密算法的原因，许多客户端不一定可以连接MySQL所以

```c&#39;m&#39;d
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY 'password' PASSWORD EXPIRE NEVER;
Query OK, 0 rows affected (0.06 sec)

mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'password';
Query OK, 0 rows affected (0.08 sec)

mysql>  FLUSH PRIVILEGES;
Query OK, 0 rows affected (0.01 sec)
```

## 搭建MySQL集群

MySQL集群有2种常见的解决方案：Replication和PXC。

Replication：速度快，弱一致性，低价值数据，日志，新闻，帖子

PXC：             速度慢，强一致性，高价值数据，订单，账户，财务

下载MySQL集群

```
docker pull percona/percona-xtradb-cluster
```

名字太长不好用所以我们使用tag来改名字

```
docker tag percona/percona-xtradb-cluster pxc
```

删除之前的image

```
docker rmi percona/percona-xtradb-cluster
```

```
docker network create net1

docker network create --subnet=172.18.0.0/24 net1
```

```
docker volume create --name v1
```

```
docker run -d -p 3306:3306
-v v1:/var/lib/mysql
-e MYSQL_ROOT_PASSWORD=abc123456
-e CLUSTER_NAME_=PXC
-e XTRABACKUP_PASSWORD=abc123456
--privileged --name=node1 --net=net1 --ip 172.18.0.2
pxc
```

```
docker run -d -e WORDPRESS_DB_HOST=mysql:3306 --link mysql -p 80:80 wordpress

docker run -d --name mysql -v mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=wordpress mysql
```

## 搭建Redis集群



## 项目部署



## 微服务



## 容器编排

主要为k8s

## CICD和DevOps

CICD为Jenkins+GitLab

DevOps讲讲KVM，Python等等以后过度到OpenStack

好了~这些就是近期的计划了~，可能果断时间会深入数据挖掘，但之前还是先把架构弄好，100w用户的网站。



