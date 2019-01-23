---
title: Netty
date: 2018-10-31 23:33:12
tags:
---

```sql
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `nickname` varchar(32) NOT NULL COMMENT '昵称',
  `qr_code` varchar(32) NOT NULL COMMENT '二维码',
  `cid` varchar(32) NOT NULL,
  `face_image` varchar(512) DEFAULT NULL COMMENT '小图',
  `face_image_big` varchar(512) DEFAULT NULL COMMENT '大图',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

安装好nginx后，就可以开始下一步的操作了。

在`C:\Windows\System32\drivers\etc`下有host文件，这里把`127.0.0.1`配置为`vaderwang.image.com`

PS:在Windows10中由于权限问题所以要先把host文件复制到其他目录（比如桌面）然后进行修改，修改完成后复制到`C:\Windows\System32\drivers\etc`进行覆盖即可。

然后进入nginx的目录

修改nginx的配置文件加入include vhost/*.conf; 然后在conf目录下新建vhost文件夹，在vhost文件夹里面添加.conf配置文件，这里注意server_name是vaderwang.image.com,以及我要把D:\VVader\image这个路径交给nginx代理。

```
server {
    listen 80;
    autoindex off;
    server_name vaderwang.image.com;
    access_log logs/access.log combined;
    index index.html index.htm index.jsp index.php;
    if ( $query_string ~* ".*[\;'\<\>].*" ){
        return 404;
    }

    location ~ /(mmall_fe|mmall_admin_fe)/dist/view/* {
        deny all;
    }

    location / {
        root D:\VVader\image;
        add_header Access-Control-Allow-Origin *;
    }
}
```

接下来修改host文件然后nginx -s reload测试是否配置成功

http://vaderwang.image.com/1.jpg

接下来是内网穿透

什么是内网穿透呢，简单的来说就是让你可以通过互联网访问你自己的主机，我们知道要访问互联网是需要公网ip的而内网穿透就是让你不需要拥有公网ip也可以使用域名来访问主机。

https://natapp.cn/

natapp.exe -authtoken=xxxxxxx



FastDFS

fastdfs下载

https://github.com/happyfish100/fastdfs/releases/tag/V5.05

libfastcommon下载

https://github.com/happyfish100/libfastcommon/releases/tag/V1.0.7



ssh登陆

```
ssh root@ubuntu
```

配置ssh登陆

```
安装openssh 
sudo apt-get install openssh-server 
安装过程中会有一次询问，输入“yes”

修改root密码（root用户默认不存在，首次使用需要设置密码） 
sudo passwd root 
首先输入当前用户密码，然后输入两次你要设置的root密码

编辑配置文件，允许root用户使用SSH服务 
sudo vim /etc/ssh/sshd_config 
如果没有安装vim，将vim改为vi，vim或者vi的操作自行学习 
找到：PermitRootLogin prohibit-password（禁用的意思） 用#注释掉 
添加：PermitRootLogin yes 
sudo service ssh restart 
--------------------- 
```

scp上传到Ubuntu Server

```shell
$ scp -r fastdfs vaderwang@ubuntu:/home/vaderwang/software/fastdfs
```

```shell
sudo apt -y install libevent
```



ssh root@ubuntu