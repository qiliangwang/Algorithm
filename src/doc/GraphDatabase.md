---
title: Graph Database
date: 2018-09-21 13:38:57
tags:
---

## 图数据库

neo4j是一个图数据库，它可能没有MySQL那么有名，但图数据库具有很大的前景。接下来就介绍一下简单的图数据库概念。

### neo4j安装

Docker:

```shell
docker run --publish=7474:7474 --publish=7687:7687 --volume=$HOME/neo4j/data:/data neo4j
```

或者下载压缩文件解压

```shell
C:\Program Files\neo4j-community-3.4.9\bin>neo4j install-service
Neo4j service installed

C:\Program Files\neo4j-community-3.4.9\bin>neo4j start
Neo4j service started
```

http://localhost:7474/browser/

进去后默认用户名：neo4j，密码：neo4j. 提示修改密码，这里我改成了password.

### py2neo安装

```powershell
pip install -i https://pypi.doubanio.com/simple py2neo
```

