---
title: Scrapy
date: 2019-08-02 13:48:47
tags:
---

### 设置pip源

设置pip源，临时指定pip源可以使用-i参数，设置默认需要在～文件目录下创建.pip文件夹，并在该文件夹中创建pip.conf文件，输入以下内容。

```shell
mkdir ~/.pip
```

```shell
touch pip.conf
```

```
[global]
trusted-host=mirrors.aliyun.com
index-url=http://mirrors.aliyun.com/pypi/simple/
```

### 安装Scrapy

```shell
pip install -i https://pypi.douban.com/simple scrapy
```

```shell
pip install scrapy
```

### 运行Scrapy

构建和运行一个爬虫只需完成以下几步：

- 使用`scrapy startproject`创建爬虫模板或自己编写爬虫脚本
- 爬虫类继承`scrapy.Spider`，重写`parse`方法
- `parse`方法中`yield`或`return`字典、`Request`、`Item`
- 使用`scrapy crawl <spider_name>`或`scrapy runspider <spider_file.py>`运行

经过简单的几行代码，就能采集到某个网站下一些页面的数据，非常方便。


```python
class JobboleSpider(scrapy.Spider):

    name = "baidu"
    allowed_domains = ["www.baidu.com"]
    start_urls = ['https://www.baidu.com/']

    def parse(self, response):
      pass
```

### scrapy调试

```shell
scrapy shell https://www.baidu.com
```

### Scrapy运行流程
 ![scrapy_architecture](./Scrpay/scrapy_architecture.png)

# 核心组件

Scrapy有以下几大组件：

- `Scrapy Engine`：核心引擎，负责控制和调度各个组件，保证数据流转；
- `Scheduler`：负责管理任务、过滤任务、输出任务的调度器，存储、去重任务都在此控制；
- `Downloader`：下载器，负责在网络上下载网页数据，输入待下载URL，输出下载结果；
- `Spiders`：用户自己编写的爬虫脚本，可自定义抓取意图；
- `Item Pipeline`：负责输出结构化数据，可自定义输出位置；

除此之外，还有两大中间件组件：

- `Downloader middlewares`：介于引擎和下载器之间，可以在网页在下载前、后进行逻辑处理；
- `Spider middlewares`：介于引擎和爬虫之间，可以在调用爬虫输入下载结果和输出请求/数据时进行逻辑处理；

# 数据流转

架构图中的数据流转是这样的：

1. **Engine**获取**start_urls**；
2. **Engine**把该请求放入**Scheduler**中，同时**Engine**向**Scheduler**获取一个待下载的请求；
3. **Scheduler**返回给**Engine**一个待下载的请求；
4. **Engine**发送请求给**Downloader**，中间会经过一系列**Downloader middlewares**；
5. 这个请求通过**Downloader**下载完成后，生成一个**Response**，返回给引擎，这中间会再次经过一系列**Downloader middlewares**；
6. **Engine**接收到下载返回的响应对象后，然后发送给**Spider**，执行爬虫逻辑，中间会经过一系列**Spider middlewares**；
7. **Spider**执行对应的回调方法，处理这个响应，完成爬虫逻辑后，会yield**Item**或**Request**给**Engine**，再次经过一系列**Spider middlewares**；
8. **Engine**把返回的**Item**交由**Item Pipeline**处理，把**Request**通过引擎再交给**Scheduler**；