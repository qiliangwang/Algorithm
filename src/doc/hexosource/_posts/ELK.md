---
title: ELK
date: 2018-11-11 00:34:30
tags:
---

# ELK

### 安装Elasticsearch

可以去Elasticsearch的官网下载，但是由于我们使用的是中文，需要安装很多的插件，所以这里我们安装Elasticsearch-rtf（预先安装了很多的插件这样就不需要我们自己安装了）

https://github.com/medcl/elasticsearch-rtf

下载即可，进入bin目录

elasticsearch默认不允许插件，所以我们需要修改elasticsearch的安全策略

在elasticsearch-5.1.1\config\elasticsearch.yml中，添加下面的内容

```yaml
http.cors.enabled: true
http.cors.allow-origin: "*"
http.cors.allow-methods: OPTIONS, HEAD, GET, POST, PUT, DELETE
http.cors.allow-headers: "X-Requested-With, Content-Type, Content-Length, X-User"
```

```cmd
elasticsearch.bat
```

 http://localhost:9200/

### 安装head

https://github.com/mobz/elasticsearch-head

```shell
git clone git://github.com/mobz/elasticsearch-head.git
```

```shell
cd elasticsearch-head/
```

```shell
npm install
```

```shell
npm run start
```

 http://localhost:9100/

### 安装kibana

安装Kibana的时候要注意Kibana的版本要和Elasticsearch的版本保持一致，我这里使用的是5.1.1的。

https://www.elastic.co/cn/downloads/past-releases/kibana-5-1-1

下载对应的系统版本后进入bin目录

```
kibana.bat
```

 http://localhost:5601/

### 虚拟环境virtualenv

```shell
pip install -i https://pypi.doubanio.com/simple virtualenv
```

```shell
D:\project\python>virtualenv xxxxxx
```

Win:虚拟环境目录下的Scrapts>activate.bat用于启动虚拟环境，退出deactivate.bat

Linux: source activate

```shell
pip install -i https://pypi.doubanio.com/simple virtualenvwrapper
```

Srapy爬取的数据存到MySQL中

```sql
CREATE TABLE `article` (
  `title` varchar(200) NOT NULL,
  `create_date` date DEFAULT NULL,
  `url` varchar(300) NOT NULL,
  `url_object_id` varchar(50) NOT NULL,
  `front_image_url` varchar(300) DEFAULT NULL,
  `comment_nums` int(11) NOT NULL DEFAULT '0',
  `fav_nums` int(11) NOT NULL DEFAULT '0',
  `praise_nums` int(11) NOT NULL DEFAULT '0',
  `tags` varchar(200) DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`url_object_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

```shell
pip install mysqlclient
```

### 写入数据到es中

elasticsearch-dsl

```shell
pip install -i https://pypi.doubanio.com/simple elasticsearch==5.1
pip install -i https://pypi.doubanio.com/simple elasticsearch-dsl==5.1
```

### Django网站搭建

```shell
D:\project\python>virtualenv search
```

```scheme
Scrapts>activate.bat
```

```shell
pip install -i https://pypi.doubanio.com/simple django==1.11
pip install -i https://pypi.doubanio.com/simple elasticsearch==5.1
pip install -i https://pypi.doubanio.com/simple elasticsearch-dsl==5.1
pip install -i https://pypi.doubanio.com/simple redis
```









