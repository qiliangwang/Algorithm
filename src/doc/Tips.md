---
title: Tips
date: 2018-09-10 00:34:30
tags:
---

## Linux

```shell
cat ~/.ssh/id_rsa.pub


export SPARK_HOME=/home/vaderwang/software/spark-2.1.0
export PATH=$SPARK_HOME/bin:$PATH
```

### VScode格式化代码

Windows:	Shift + Alt + F

Mac:		Shift + Option + F

Ubuntu:		 Ctrl + Shift + I

### IDEA格式化代码

control + N

control + shift + N

control + shift + H



快速抽取返回值：

control + alt + v

万能：

alt+enter 

点击showMenbers可以查看类中的方法等

格式化代码：

control + alt + shift + L

快速下一行：

win + shift + enter

提取变量：

control + alt + v

变量名大写：

control+ shift + u

批量修改变量名：

control + shift + alt + j

为了能够使用javac需要把下面路径加入环境变量Path

```
C:\Program Files\Java\jdk1.8.0_172\bin
```

### GIT冲突解决

方法一：stash

```
git stash
git pull
git stash pop
```

方法二：直接完全覆盖本地修改

```
git reset --hard
git pull
```

## Ubuntu

```shell
sudo gdebi xxx.deb
```

```shell
$ scp -r software/ vaderwang@ubuntu:/home/vaderwang/software
```

Ubuntu下安装.bundle文件

```shell
sudo chmod +x ./VMware-Workstation-Full-12.5.9-7535481.x86_64.bundle 
sudo ./VMware-Workstation-Full-12.5.9-7535481.x86_64.bundle 
```

为了使用Anaconda的pip

```shell
/home/vaderwang/anaconda3/bin/pip install opencv-python
```

```shell
/home/vaderwang/anaconda3/bin/pip install https://storage.googleapis.com/tensorflow/mac/cpu/tensorflow-1.8.0-py3-none-any.whl
```

```shell
ln -s /home/vaderwang/anaconda3/bin/pip3.7 /usr/local/bin/pip
```

软链接方便以后使用pip

```
pip install virtualenv
useradd deploy
su - deploy
```

## Lantern

修改settings.yaml

addr行参数，改为`addr: ‘[::]:9029’`，

表示监听本机IP，localhost，127所有ip的9029端口，

自然也就支持192ip加端口号，

改好后，重启lantern



要网络代理的地方其实不止是浏览器，很多命令行工具也会访问网络。比如，我们通过 `homebrew` 安装 `dart` 的时候，brew 命令会从 Google 的服务器上下载安装文件。然后你就会看到网络连接错误的提示信息。

要解决这类问题，只需要为 Shell 设置两个环境变量 `HTTP_PROXY` 和 `HTTPS_PROXY` 即可。我们直接利用蓝灯在本地启动好的代理端口。

我们首先找到蓝灯在本地启动的具体端口号。打开蓝灯，依次选择 `Settings` -> `ADVANCED SETTINGS` 即可看到蓝灯在本地选择的端口号。

然后去 Shell 里执行以下两个命令设置环境变量：

```bash
export HTTP_PROXY=http://127.0.0.1:51350
export HTTPS_PROXY=http://127.0.0.1:51350
```



## Chrome

网页截图

开发者模式（F12）下control + shift + p 输入caputer full size screenshot即可

## VM

control + alt 弹出虚拟机

## CentOS

安装vscode

```shell
sudo rpm --import https://packages.microsoft.com/keys/microsoft.asc
sudo sh -c 'echo -e "[code]\nname=Visual Studio Code\nbaseurl=https://packages.microsoft.com/yumrepos/vscode\nenabled=1\ngpgcheck=1\ngpgkey=https://packages.microsoft.com/keys/microsoft.asc" > /etc/yum.repos.d/vscode.repo'
yum check-update
sudo yum install code
```

```shell
service network restart
```

## Logs

10:56	Error running 'RearServicesApplication': Command line is too long. Shorten command line for RearServicesApplication or also for Spring Boot default configuration.

Solve:

在该项目文件夹下.idea/workspace.xml中添加

```xml
<component name="PropertiesComponent">
  ...
  <property name="dynamic.classpath" value="true" />
</component>
```