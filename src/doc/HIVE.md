## HIVE

## HIVE安装

https://archive.apache.org/dist/hive/

```shell
vaderwang@vaderwang-x399:~/Downloads$ tar -zxvf apache-hive-2.1.1-bin.tar.gz -C ~/software/

```

配置环境变量

```shell
export HIVE_HOME=/home/vaderwang/software/apache-hive-2.1.1-bin
export PATH=$HIVE_HOME/bin:$PATH
```

配置

```bash
apache-hive-2.1.1-bin/conf$ cp hive-env.sh.template hive-env.sh

HADOOP_HOME=/home/vaderwang/software/hadoop-2.7.3
```

```shell
vaderwang@vaderwang-x399:~/software/apache-hive-2.1.1-bin/conf$ vim hive-site.xml
```

```shell
cd $HIVE_HOME/scripts/metastore/upgrade/mysql/

< Login into MySQL >

mysql> drop database IF EXISTS hive;
mysql> create database hive;
mysql> use hive;
mysql> source hive-schema-2.1.1.mysql.sql;
```

```xml
<configuration>
  <property>
    <name>javax.jdo.option.ConnectionURL</name>
    <value>jdbc:mysql://localhost:3306/hive?useSSL=false</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionDriverName</name>
    <value>com.mysql.jdbc.Driver</value>
  </property> 
  <property>
    <name>javax.jdo.option.ConnectionUserName</name>
    <value>debian-sys-maint</value>
  </property>
  <property>
    <name>javax.jdo.option.ConnectionPassword</name>
    <value>2a3AGpN7nW2FJtvJ</value>
  </property>
</configuration>
```

把JDBC驱动复制到hive的bin目录

```shell
vaderwang@vaderwang-x399:~/software/apache-hive-2.1.1-bin/lib$ cp ~/software/mysql-connector-java-5.1.27-bin.jar .
```

启动hive

```shell
vaderwang@vaderwang-x399:~/software/apache-hive-2.1.1-bin/bin$ ./hive
```

## Word Count

```sql
hive> create table word_count(context string);

hive> show tables;
```

可以使用mysql查看创建的表的信息

```sql
select * from TBLS;

select * from COLUMNS_V2;
```

进行简单的word count

```
hello	world	welcome
hello	welcome
```

```sql
hive> load data local inpath '/home/vaderwang/data/word_count.txt' into table word_count;

hive> select * from word_count;
OK
hello	world	welcome
hello	welcome
Time taken: 0.801 seconds, Fetched: 2 row(s)

hive> select word, count(1) from word_count lateral view explode(split(context, '\t')) wc as word group by word;
```

```sql
按照指定分割符进行拆解
lateral view explode(split(context, '\t'))
```

```
hive> select word, count(1) from word_count lateral view explode(split(context, '\t')) wc as word group by word;
WARNING: Hive-on-MR is deprecated in Hive 2 and may not be available in the future versions. Consider using a different execution engine (i.e. spark, tez) or using Hive 1.X releases.
Query ID = vaderwang_20190119203154_e6762fd2-854f-4742-8eac-483b2c774bbb
Total jobs = 1
Launching Job 1 out of 1
Number of reduce tasks not specified. Estimated from input data size: 1
In order to change the average load for a reducer (in bytes):
  set hive.exec.reducers.bytes.per.reducer=<number>
In order to limit the maximum number of reducers:
  set hive.exec.reducers.max=<number>
In order to set a constant number of reducers:
  set mapreduce.job.reduces=<number>
Job running in-process (local Hadoop)
2019-01-19 20:31:56,680 Stage-1 map = 100%,  reduce = 100%
Ended Job = job_local706953421_0001
MapReduce Jobs Launched: 
Stage-Stage-1:  HDFS Read: 136 HDFS Write: 68 SUCCESS
Total MapReduce CPU Time Spent: 0 msec
OK
hello	2
welcome	2
world	1
Time taken: 1.697 seconds, Fetched: 3 row(s)
```

可以看到其实这个是一个Map Reduce

#### 小demo

```
10	ACCOUNTING	NEW YORK
20	RESEARCH	DALLAS
30	SALES	CHICAGO
40	OPERATIONS	BOSTON
```

```
7369	SMITH	CLERK	7902	1980-12-17	800.00		20
7499	ALLEN	SALESMAN	7698	1981-2-20	1600.00	300.00	30
7521	WARD	SALESMAN	7698	1981-2-22	1250.00	500.00	30
7566	JONES	MANAGER	7839	1981-4-2	2975.00		20
7654	MARTIN	SALESMAN	7698	1981-9-28	1250.00	1400.00	30
7698	BLAKE	MANAGER	7839	1981-5-1	2850.00		30
7782	CLARK	MANAGER	7839	1981-6-9	2450.00		10
7788	SCOTT	ANALYST	7566	1987-4-19	3000.00		20
7839	KING	PRESIDENT		1981-11-17	5000.00		10
7844	TURNER	SALESMAN	7698	1981-9-8	1500.00	0.00	30
7876	ADAMS	CLERK	7788	1987-5-23	1100.00		20
7900	JAMES	CLERK	7698	1981-12-3	950.00		30
7902	FORD	ANALYST	7566	1981-12-3	3000.00		20
7934	MILLER	CLERK	7782	1982-1-23	1300.00		10
8888	HIVE	PROGRAM	7839	1988-1-23	10300.00	
```

```sql
create table emp (
    emp_no int,
    e_name string,
    job string,
    mgr string,
    hire_date string,
    sale double,
    comm double,
    depot_no int
) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';


create table dept (
    depot_no int,
    d_name string,
    location string
) ROW FORMAT DELIMITED FIELDS TERMINATED BY '\t';


load data local inpath '/home/vaderwang/Videos/SparkSQL/data/emp.txt' into table emp;

load data local inpath '/home/vaderwang/Videos/SparkSQL/data/dept.txt' into table dept;
```

```
hive> select * from dept;
OK
10	ACCOUNTING	NEW YORK
20	RESEARCH	DALLAS
30	SALES	CHICAGO
40	OPERATIONS	BOSTON
Time taken: 0.084 seconds, Fetched: 4 row(s)
hive> select * from emp;
OK
7369	SMITH	CLERK	7902	1980-12-17	800.0	NULL	20
7499	ALLEN	SALESMAN	7698	1981-2-20	1600.0	300.0	30
7521	WARD	SALESMAN	7698	1981-2-22	1250.0	500.0	30
7566	JONES	MANAGER	7839	1981-4-2	2975.0	NULL	20
7654	MARTIN	SALESMAN	7698	1981-9-28	1250.0	1400.0	30
7698	BLAKE	MANAGER	7839	1981-5-1	2850.0	NULL	30
7782	CLARK	MANAGER	7839	1981-6-9	2450.0	NULL	10
7788	SCOTT	ANALYST	7566	1987-4-19	3000.0	NULL	20
7839	KING	PRESIDENT		1981-11-17	5000.0	NULL	10
7844	TURNER	SALESMAN	7698	1981-9-8	1500.0	0.0	30
7876	ADAMS	CLERK	7788	1987-5-23	1100.0	NULL	20
7900	JAMES	CLERK	7698	1981-12-3	950.0	NULL	30
7902	FORD	ANALYST	7566	1981-12-3	3000.0	NULL	20
7934	MILLER	CLERK	7782	1982-1-23	1300.0	NULL	10
8888	HIVE	PROGRAM	7839	1988-1-23	10300.0	NULL	NULL
Time taken: 0.071 seconds, Fetched: 15 row(s)
```

求每个部门的人数

```sql
select depot_no, count(1) from emp group by depot_no;
```

```
hive> select depot_no, count(1) from emp group by depot_no;
WARNING: Hive-on-MR is deprecated in Hive 2 and may not be available in the future versions. Consider using a different execution engine (i.e. spark, tez) or using Hive 1.X releases.
Query ID = vaderwang_20190119210717_4b8b46ea-1db2-4704-8fd1-f849783aef52
Total jobs = 1
Launching Job 1 out of 1
Number of reduce tasks not specified. Estimated from input data size: 1
In order to change the average load for a reducer (in bytes):
  set hive.exec.reducers.bytes.per.reducer=<number>
In order to limit the maximum number of reducers:
  set hive.exec.reducers.max=<number>
In order to set a constant number of reducers:
  set mapreduce.job.reduces=<number>
Job running in-process (local Hadoop)
2019-01-19 21:07:19,443 Stage-1 map = 100%,  reduce = 100%
Ended Job = job_local2026328344_0002
MapReduce Jobs Launched: 
Stage-Stage-1:  HDFS Read: 3094 HDFS Write: 1626 SUCCESS
Total MapReduce CPU Time Spent: 0 msec
OK
NULL	1
10	3
20	5
30	6
Time taken: 1.534 seconds, Fetched: 4 row(s)
```





