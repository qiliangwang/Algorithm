---
title: Spark
date: 2019-01-16 20:50:02
tags:
---

## Scala

安装Scala

https://www.scala-lang.org/

https://www.scala-lang.org/download/2.11.8.html

设置SCALA_HOME系统变量然后把scala的bin目录添加到环境变量里面

```
SCALA_HOME	C:\Program Files\Scala
PATH		%SCALA_HOME%\bin
```

```shell
$ scala
Welcome to Scala 2.11.8 (Java HotSpot(TM) 64-Bit Server VM, Java 1.8.0_172).
Type in expressions for evaluation. Or try :help.

scala>
```

scala的lazy:没有调用的时候不使用

#### 安装Idea Scala插件

https://plugins.jetbrains.com/

选择对应版本的idea就可以了（插件对应的version是idea的version）

#### Scala的函数



#### Scala的OOP

## Spark安装

#### 安装JAVA

```
JAVA_HOME	C:\Program Files\Java\jdk1.8.0_172
PATH		%JAVA_HOME%\bin
```

#### 安装Hadoop

https://archive.apache.org/dist/hadoop/common/

```
HADOOP_HOME	D:\Software\hadoop-2.6.0
PATH		%HADOOP_HOME%\bin
```

ps: 对于windows平台需要到 https://github.com/steveloughran/winutils，下载对应版本的winutils

#### 安装Scala

https://www.scala-lang.org/download/2.11.8.html

```
SCALA_HOME	C:\Program Files\Scala
PATH		%SCALA_HOME%\bin
```

#### 安装Spark

http://spark.apache.org/downloads.html

```
SPARK_HOME	D:\Software\spark-2.1.0-bin-hadoop2.6
PATH		%SPARK_HOME%\bin
```

#### 运行Spark

```shell
spark-shell --master local[2]
```

http://localhost:4040/jobs/

这样Spark就成功运行了。

PS For Linux:

```shell
sudo /etc/profile
```

```
export JAVA_HOME=/usr/local/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH

export SCALA_HOME=/home/vaderwang/software/scala-2.11.8
export PATH=$SCALA_HOME/bin:$PATH

export HADOOP_HOME=/home/vaderwang/software/hadoop-2.7.3
export PATH=$HADOOP_HOME/bin:$PATH

export SPARK_HOME=/home/vaderwang/software/spark-2.1.0
export PATH=$SPARK_HOME/bin:$PATH
```

```shell
source /etc/profile
```

Spark Stand Alone运行

修改spark的配置文件

```shell
cp spark-env.sh.template spark-env.sh
```

在spark-env.sh中添加如下内容

```sh
SPARK_MASTER_HOST=localhost
SPARK_WORKER_CORES=4
SPARK_WORKER_MEMORY=4g
SPARK_WORKER_INSTANCES=1
```

```shell
./sbin/start-all.sh
```

 http://localhost:8080/

配置单机多节点运行

```
./sbin/stop-all.sh
```

```shell
SPARK_MASTER_HOST=localhost
SPARK_WORKER_CORES=4
SPARK_WORKER_MEMORY=4g
SPARK_WORKER_INSTANCES=2
```

```shell
spark-shell --master spark://localhost:7077
```

### Spark Core RDD

```python
from pyspark import SparkConf, SparkContext


def main():
    conf = SparkConf().setMaster("local[2]").setAppName("spark0401")
    sc = SparkContext(conf=conf)

    def my_map():
        data = [1, 2, 3, 4, 5]
        rdd = sc.parallelize(data)
        result = rdd.map(lambda x: x * 2).collect()
        print(result)
        pass

    def my_filter():
        data = [1, 2, 3, 4, 5]
        rdd = sc.parallelize(data)
        result = rdd.map(lambda x: x * 2).filter(lambda x: x > 5).collect()
        print(result)
        pass

    def my_flat_map():
        data = ['hello spark', 'hello java', 'hello python', 'hello kafka', 'hello storm']
        rdd = sc.parallelize(data)
        # [['hello', 'spark'], ['hello', 'java'], ['hello', 'python'], ['hello', 'kafka'], ['hello', 'storm']]
        print(rdd.map(lambda line: line.split(' ')).collect())
        # ['hello', 'spark', 'hello', 'java', 'hello', 'python', 'hello', 'kafka', 'hello', 'storm']
        print(rdd.flatMap(lambda line: line.split(' ')).collect())
        split__map = rdd.flatMap(lambda line: line.split(' ')).map(lambda x: (x, 1))
        print(split__map.collect())
        pass

    def my_group_by_key():
        data = ['hello spark', 'hello java', 'hello python', 'hello kafka', 'hello storm']
        rdd = sc.parallelize(data)
        map_rdd = rdd.flatMap(lambda line: line.split(' ')).map(lambda x: (x, 1))
        # [{'java': [1]}, {'python': [1]}, {'storm': [1]}, {'hello': [1, 1, 1, 1, 1]}, {'spark': [1]}, {'kafka': [1]}]
        print(map_rdd.groupByKey().map(lambda x: {x[0]: list(x[1])}).collect())

    def my_reduce_by_key():
        data = ['hello spark', 'hello java', 'hello python', 'hello kafka', 'hello storm']
        rdd = sc.parallelize(data)
        map_rdd = rdd.flatMap(lambda line: line.split(' ')).map(lambda x: (x, 1))
        # [{'java': [1]}, {'python': [1]}, {'storm': [1]}, {'hello': [1, 1, 1, 1, 1]}, {'spark': [1]}, {'kafka': [1]}]
        group_rdd = map_rdd.reduceByKey(lambda a, b: a + b)
        result = group_rdd.map(lambda x: (x[1], x[0])).sortByKey(False).map(lambda x: (x[1], x[0]))
        print(result.collect())

    def group_by_key_word_count():
        data = ['hello spark', 'hello java', 'hello python', 'hello kafka', 'hello storm']
        rdd = sc.parallelize(data)
        map_rdd = rdd.flatMap(lambda line: line.split(' ')).map(lambda x: (x, 1))
        result = map_rdd.groupByKey().map(lambda x: {x[0]: sum(list(x[1]))})
        print(result.collect())

    def my_union():
        pass

    my_reduce_by_key()
    sc.stop()


def word_count(file_dir):
    conf = SparkConf().setMaster("local[2]").setAppName("spark0401")
    sc = SparkContext(conf=conf)
    counts = sc.textFile(file_dir)\
        .flatMap(lambda line: line.split('   '))\
        .map(lambda x: (x, 1))\
        .reduceByKey(lambda a, b: a + b)
    output = counts.collect()
    print(output)
    sc.stop()
    pass


def save_file(file_dir, save_dir):
    conf = SparkConf().setMaster("local[2]").setAppName("spark0401")
    sc = SparkContext(conf=conf)
    counts = sc.textFile(file_dir) \
        .flatMap(lambda line: line.split('   ')) \
        .map(lambda x: (x, 1)) \
        .reduceByKey(lambda a, b: a + b).saveAsTextFile(save_dir)
    # output = counts.collect()
    # print(counts)
    sc.stop()


def topN(file_dir):
    conf = SparkConf().setMaster("local[2]").setAppName("spark0401")
    sc = SparkContext(conf=conf)
    counts = sc.textFile(file_dir) \
        .flatMap(lambda line: line.split('   ')) \
        .map(lambda x: (x, 1)) \
        .reduceByKey(lambda a, b: a + b)\
        .map(lambda x: (x[1], x[0])).sortByKey(False).map(lambda x: (x[1], x[0])).take(5)
    # output = counts.collect()
    print(counts)
    sc.stop()


def average(file_dir):
    conf = SparkConf().setMaster("local[2]").setAppName("spark0401")
    sc = SparkContext(conf=conf)
    data = sc.textFile(file_dir)
    data = data.map(lambda line: line.split(' ')[1])
    total = data.map(lambda age: int(age)).reduce(lambda a, b: a + b)
    avg = total / data.count()
    print(avg)


if __name__ == '__main__':
    # main()
    # word_count('file:///home/vaderwang/PycharmProjects/Spark/*.txt')
    # save_file('file:///home/vaderwang/PycharmProjects/Spark/*.txt', 'file:///home/vaderwang/PycharmProjects/Spark/wc')
    # topN('file:///home/vaderwang/PycharmProjects/Spark/hello1.txt')
    average('file:///home/vaderwang/PycharmProjects/Spark/hello1.txt')

```

## Spark SQL

### DataFrame

```scala
package com.iceberg

import org.apache.spark.sql.SparkSession

/**
 * DataFrame API基本操作
 */
object DataFrameApp {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("DataFrameApp").master("local[2]").getOrCreate()

    val peopleDF = spark.read.format("json").load("data/people.json")

    // 输出dataframe对应的schema信息
    peopleDF.printSchema()

    // 输出数据集的前20条记录
    peopleDF.show()

    //查询某列所有的数据： select name from table
    peopleDF.select("name").show()

    // 查询某几列所有的数据，并对列进行计算： select name, age+10 as age2 from table
    peopleDF.select(peopleDF.col("name"), (peopleDF.col("age") + 10).as("age2")).show()

    //根据某一列的值进行过滤： select * from table where age>19
    peopleDF.filter(peopleDF.col("age") > 19).show()

    //根据某一列进行分组，然后再进行聚合操作： select age,count(1) from table group by age
    peopleDF.groupBy("age").count().show()

    spark.stop()
  }
}
```

```scala
package com.iceberg

import org.apache.spark.sql.SparkSession

/**
 * DataFrame中的操作操作
 */
object DataFrameCase {

  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile("data/student.data")

    //注意：需要导入隐式转换
    import spark.implicits._
    val studentDF = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    //show默认只显示前20条
    studentDF.show
    studentDF.show(30)
    studentDF.show(30, false)

    studentDF.take(10)
    studentDF.first()
    studentDF.head(3)

    studentDF.select("email").show(30,false)

    studentDF.filter("name=''").show
    studentDF.filter("name='' OR name='NULL'").show

    //name以M开头的人
    studentDF.filter("SUBSTR(name,0,1)='M'").show

    studentDF.sort(studentDF("name")).show
    studentDF.sort(studentDF("name").desc).show

    studentDF.sort("name","id").show
    studentDF.sort(studentDF("name").asc, studentDF("id").desc).show

    studentDF.select(studentDF("name").as("student_name")).show

    val studentDF2 = rdd.map(_.split("\\|")).map(line => Student(line(0).toInt, line(1), line(2), line(3))).toDF()

    studentDF.join(studentDF2, studentDF.col("id") === studentDF2.col("id")).show

    spark.stop()
  }

  case class Student(id: Int, name: String, phone: String, email: String)

}
```

```scala
package com.iceberg

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{Row, SparkSession}

/**
 * DataFrame和RDD的互操作
 */
object DataFrameRDDApp {

  def main(args: Array[String]) {

    val spark = SparkSession.builder().appName("DataFrameRDDApp").master("local[2]").getOrCreate()

    //inferReflection(spark)

    program(spark)

    spark.stop()
  }

  def program(spark: SparkSession): Unit = {
    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile("data/infos.txt")

    val infoRDD = rdd.map(_.split(",")).map(line => Row(line(0).toInt, line(1), line(2).toInt))

    val structType = StructType(Array(StructField("id", IntegerType, true),
      StructField("name", StringType, true),
      StructField("age", IntegerType, true)))

    val infoDF = spark.createDataFrame(infoRDD,structType)
    infoDF.printSchema()
    infoDF.show()


    //通过df的api进行操作
    infoDF.filter(infoDF.col("age") > 30).show

    //通过sql的方式进行操作
    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()
  }

  def inferReflection(spark: SparkSession) {
    // RDD ==> DataFrame
    val rdd = spark.sparkContext.textFile("file:///Users/rocky/data/infos.txt")

    //注意：需要导入隐式转换
    import spark.implicits._
    val infoDF = rdd.map(_.split(",")).map(line => Info(line(0).toInt, line(1), line(2).toInt)).toDF()

    infoDF.show()

    infoDF.filter(infoDF.col("age") > 30).show

    infoDF.createOrReplaceTempView("infos")
    spark.sql("select * from infos where age > 30").show()
  }

  case class Info(id: Int, name: String, age: Int)

}
```

### Dataset

```scala
package com.iceberg

import org.apache.spark.sql.SparkSession

/**
 * Dataset操作
 */
object DatasetApp {

  def main(args: Array[String]) {
    val spark = SparkSession.builder().appName("DatasetApp")
      .master("local[2]").getOrCreate()

    //注意：需要导入隐式转换
    import spark.implicits._

    val path = "data/sales.csv"

    //spark如何解析csv文件？
    val df = spark.read.option("header","true").option("inferSchema","true").csv(path)
    df.show

    val ds = df.as[Sales]
    ds.map(line => line.itemId).show

    ds.map(line => line.itemId)

    spark.stop()
  }

  case class Sales(transactionId:Int,customerId:Int,itemId:Int,amountPaid:Double)
    
}
```

### External Data Source API

```

```

### ip解析

https://github.com/wzhe06/ipdatabase

```shell
git clone git@github.com:wzhe06/ipdatabase.git
```

安装.jar到本地maven仓库

```shell
 cd ipdatabase/

mvn clean package -DskipTests

mvn install:install-file -Dfile=/home/vaderwang/Github/ipdatabase/target/ipdatabase-1.0-SNAPSHOT.jar -DgroupId=com.ggstar -DartifactId=ipdatabase -Dversion=1.0 -Dpackaging=jar
```

或者简单一些

```shell
mvn clean install -DskipTests
```



### Spark Streaming



Spark MLlib

Spark GraphX