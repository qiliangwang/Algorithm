# Algorithm

http://mirrors.aliyun.com/ubuntu-releases/16.04/

```shell
root@vaderwang-x399:/home/vaderwang/Github# mysql
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 22
Server version: 5.7.24-0ubuntu0.18.04.1 (Ubuntu)

Copyright (c) 2000, 2018, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> update mysql.user set authentication_string=password('password') where user='root';
Query OK, 1 row affected, 1 warning (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 1

mysql> exit
Bye
root@vaderwang-x399:/home/vaderwang/Github# service mysql restart

mysqladmin -u root -p password
```

```
host     = localhost
user     = debian-sys-maint
password = 2a3AGpN7nW2FJtvJ
```

sudo passwd
enter current user password (kubernetes kubernetes for example )
enter root password later (root root for example)

```
sudo apt update
sudo apt install -y openssh-server
sudo apt install -y docker.io

ssh kubernetes@192.168.50.130
ssh kubernetes@192.168.50.131
ssh kubernetes@192.168.50.132


192.168.50.130 server01
192.168.50.131 server02
192.168.50.132 server03

scp kubernetes-bins.tar.gz kubernetes@192.168.50.130:~
scp kubernetes-bins.tar.gz kubernetes@192.168.50.131:~
scp kubernetes-bins.tar.gz kubernetes@192.168.50.132:~

tar -xvf kubernetes-bins.tar.gz

mv kubernetes-bins/ bins

```

```

vim config.properties 

#kubernetes二进制文件目录,eg: /home/michael/bin
BIN_PATH=/home/kubernetes/bin
/home/kubernetes/bin

#当前节点ip, eg: 192.168.1.102
NODE_IP=192.168.50.130

#etcd服务集群列表, eg: http://192.168.1.102:2379
#如果已有etcd集群可以填写现有的。没有的话填写：http://${MASTER_IP}:2379 （MASTER_IP自行替换成自己的主节点ip）
ETCD_ENDPOINTS=http://192.168.50.130:2379
ETCD_ENDPOINTS=http://192.168.210.131:2379

#kubernetes主节点ip地址, eg: 192.168.1.102
MASTER_IP=192.168.50.130
MASTER_IP=192.168.210.131

$ ./gen-config.sh simple
#查看生成的配置文件，确保脚本执行成功
$ find target/ -type f

```