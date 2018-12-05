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
```
