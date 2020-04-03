---
title: Crawl-Spider
date: 2019-08-05 12:54:06
tags:
---



# CrawlerSpider

`CrawlerSpider`继承了`Spider`类，然后重写了`parse`方法，并结合`Rule`等规则类，来完成`Request`的自动提取逻辑。

 ```shell
scrapy genspider -t crawl baidu www.baidu.com
 ```

