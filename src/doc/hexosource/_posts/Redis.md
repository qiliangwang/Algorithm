---
title: Redis
date: 2019-01-25 20:15:51
tags:
---

### 设置密码

```shell
config set requirepass 123456
auth 123456
```

### 字符串

```bash
$ redis-cli
127.0.0.1:6379> set key "value"
OK
127.0.0.1:6379> get key
"value"
127.0.0.1:6379> set key "value2"
OK
127.0.0.1:6379> get key
"value2"
127.0.0.1:6379> del key
(integer) 1
127.0.0.1:6379> get key
(nil)
127.0.0.1:6379>
```

```shell
127.0.0.1:6379> get count
(nil)
127.0.0.1:6379> incr count
(integer) 1
127.0.0.1:6379> get count
"1"
127.0.0.1:6379> incrby count 10
(integer) 11
127.0.0.1:6379> get count
"11"
127.0.0.1:6379> decr count
(integer) 10
127.0.0.1:6379> get count
"10"
127.0.0.1:6379>
```

如何记录网站用户个人主页的访问量？

```
incr userId pageView
```

缓存，这个在工作中，用的是最多的

```java
public Data getData(long id) {
    Data data = redis.get(id);
    if (data == null) {
        data = mysql.get(id);
        if (data != null) {
            redis.set(id, data);            
        }
    }
    return data;
}
```

分布式id生成

```
incr id
```

```shell
set key value 
#不管key是否存在，都设置

setnx key value
#key不存在，才设置

set key value xx
#key存在，才设置
```

N次 get = N X（网络+命令），mget = N X（命令）+ 网络

```shell
C:\Users\Administrator>redis-cli
127.0.0.1:6379> mset key1 value1 key2 value2 key3 value3
OK
127.0.0.1:6379> mget key1 key2 key3
1) "value1"
2) "value2"
3) "value3"
```

```shell
getset key newValue
#set newValue并返回旧的value

append key value
#将value追加到旧的value中
127.0.0.1:6379> get key
"value"
127.0.0.1:6379> append key appendValue
(integer) 16
127.0.0.1:6379> get key
"valueappendValue"

strlen key
#返回字符串的长度（UTF-8中文2个字节）

getrange key start end
#获取字符串指定下标所有的值

setrange key index value
#更改指定index的值

127.0.0.1:6379> getrange key 0 4
"value"
127.0.0.1:6379> setrange key 4 u
(integer) 16
127.0.0.1:6379> get key
"valuuappendValue"
```

### hash

有时候我们需要存储一个对象，要是我们使用string来实现的话，我们需要先序列化对象，存入redis，然后反序列化回来，修改属性后又序列化存储，这样效率太低，过程繁琐。

```

```

### list

### set

### zset

### bitmap

### hyperloglog

### geo