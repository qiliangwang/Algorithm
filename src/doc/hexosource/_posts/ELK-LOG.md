---
title: ELK-LOG
date: 2019-07-15 10:02:13
tags:
---

# 使用ELK搭建日志收集系统

高效的检索日志是处理线上问题的手段，遇到了线上bug第一想到的是去看日志，很多传统的日志处理模式是从服务器上把日志文件下载到本地，然后再使用文本编辑器来查看日志。这样的查看日志的过程十分低效。

下载好ELK后为了处理日志我们只需要对编写Logstash对应的配置文件。



使用ELK收集docker应用的log



使用gradle将springboot工程打包为jar包

```shell
gradle compileToJar
```



编写DockerFile打包为镜像

```dockerfile
FROM openjdk:8-jdk-slim

COPY ./server-1.0.0.jar /springboot-web.jar

ENTRYPOINT ["java", "-jar", "/springboot-web.jar"]
```

```shell
docker built -t springboot-web:v1 .
```



使用docker 命令运行容器

```shell
docker run -t -d --log-driver=syslog --log-opt syslog-address=tcp://10.10.53.82:5000 springboot-web:v1
```



使用logstash收集日志

```shell
bin/logstash -f /Users/mac/Desktop/wosummer-log/characterMineConfig 
```



characterMineConfig:

```json
input {
    tcp {
        port => 5000
        type => syslog
        codec => multiline {
             pattern => "%{TIMESTAMP_ISO8601}"
             negate  => true
             what    => "previous"
       }
    }
}

filter {
    grok { 
       match => {
              "message" => "(?m)%{TIMESTAMP_ISO8601:timestamp}\s+%{DATA:level}\s+%{DATA:class}\s+-\s+%{GREEDYDATA:msg}"
         }
    }

    mutate {
       "add_field" => {"appname" => "character_mine"}
    }

}

output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "character_mine-%{+YYYY.MM.dd}"
  }
}
```

 当然写配置的时候需要debug

```json
output {
  elasticsearch {
    hosts => ["localhost:9200"]
    index => "house-info-%{+YYYY.MM.dd}"
  }
  stdout {codec => rubydebug}
}
```

使用Docker Compose来搭建整个系统。(代码如下)

https://github.com/VaderWang/ELK-LOG

