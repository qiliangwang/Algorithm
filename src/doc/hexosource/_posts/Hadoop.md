---
title: Hadoop
date: 2019-01-04 20:50:11
tags:

---

## Hadoop

https://archive.apache.org/dist/hadoop/core/hadoop-2.7.3/

下载 hadoop-2.7.3.tar.gz

SSH公钥生成

```shell
ssh-keygen
```

一路enter即可

```shell
vaderwang@vaderwang-X399:~/.ssh$ ll
total 24
drwx------  2 vaderwang vaderwang 4096 Nov  9 12:47 ./
drwxr-xr-x 58 vaderwang vaderwang 4096 Nov  9 10:13 ../
-rw-r--r--  1 vaderwang vaderwang 2876 Nov  9 12:44 authorized_keys
-rw-------  1 vaderwang vaderwang 1675 Oct 27 10:18 id_rsa
-rw-r--r--  1 vaderwang vaderwang  418 Oct 27 10:18 id_rsa.pub
-rw-r--r--  1 vaderwang vaderwang 2876 Nov  9 12:44 known_hosts
```

生成SSH公钥后，把公钥的内容复制到authorized_keys文件中

```shell
vaderwang@vaderwang-X399:~/.ssh$ cat id_rsa.pub >> authorized_keys
```

修改文件权限

```shell
vaderwang@vaderwang-X399:~/.ssh$ chmod 600 authorized_keys 
```

测试SSH免密登陆

```shell
vaderwang@vaderwang-X399:~/.ssh$ ssh localhost
Welcome to Ubuntu 18.04.1 LTS (GNU/Linux 4.15.0-38-generic x86_64)
```

配置jdk

```shell
tar -zxf jdk-8u144-linux-x64.tar.gz 
```

```shell
 sudo mv jdk1.8.0_144/ /usr/local/
```

```shell
sudo vim /etc/profile
```

```
export JAVA_HOME=/usr/local/jdk1.8.0_144
export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH
```

```shell
source /etc/profile
```

配置Hadoop

```shell
tar -zxf hadoop-2.7.3.tar.gz
```

修改 hadoop-env.sh

```sh
# export JAVA_HOME=${JAVA_HOME}
```

修改 hdfs-site.xml

```xml
<configuration>
  <property>
    <name>dfs.replication</name>
    <value>1</value>
  </property> 
  <property>
    <name>dfs.namenode.name.dir</name>
    <value>file:/home/vaderwang/hadoop_data/dfs/name</value>
  </property> 
  <property>
    <name>dfs.datanode.data.dir</name>
    <value>file:/home/vaderwang/hadoop_data/dfs/data</value>
  </property> 
</configuration>
```

修改 core-site.xml

```xml
<configuration>
  <property>
    <name>hadoop.tmp.dir</name>
    <value>file:/home/vaderwang/hadoop_data</value>
  </property> 
  <property>
    <name>fs.defaultFS</name>
    <value>hdfs://0.0.0.0:9000</value>
  </property> 
</configuration>
```

```shell
./bin/hdfs namenode -format
```

```shell
./sbin/start-dfs.sh
```

### HDFS

web客户端

http://localhost:50070/explorer.html#/

#### HDFS  shell基本操作

1、 上传文件到hdfs中

```bash
hadoop fs -put /本地文件  /aaa

hadoop fs -put ./hadoop-2.7.3.tar.gz /
```

将本地的hadoop-2.7.3.tar.gz存放到hdfs的/目录下



2、  下载文件到客户端本地磁盘

```bash
hadoop fs -get /hdfs中的路径   /本地磁盘目录
```



3、  在hdfs中创建文件夹

```bash
hadoop fs -mkdir  -p /aaa/xxx
```



 4、 移动hdfs中的文件（更名）

```bash
hadoop fs -mv /hdfs的路径1  /hdfs的另一个路径2
hadoop fs -mv /hadoop-2.7.3.tar.gz /software/rename.gz
```

​	复制hdfs中的文件到hdfs的另一个目录  

```bash
hadoop fs -cp /hdfs路径_1  /hdfs路径_2
hadoop fs -mv /hadoop-2.7.3.tar.gz /software/
```



  5、 删除hdfs中的文件或文件夹

```bash
hadoop fs -rm -r /aaa
```



 6、 查看hdfs中的文本文件内容

```
hadoop fs -cat /demo.txt
hadoop fs -tail -f /demo.txt
```

#### 使用Java api操作HDFS

```java
package com.iceberg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;

public class HDFSTest {

    public static final String HDFS_PATH = "hdfs://localhost:9000";

    FileSystem fileSystem = null;
    Configuration configuration = null;

    @Test
    public void mkdir() throws Exception {
        fileSystem.mkdirs(new Path("hdfsExample/test"));
    }

    @Test
    public void create() throws Exception {
        FSDataOutputStream outputStream = fileSystem.create(new Path("hdfsExample/test/a.txt"));
        outputStream.write("hello world".getBytes());
        outputStream.flush();
        outputStream.close();
    }

    @Test
    public void cat() throws Exception {
        FSDataInputStream inputStream = fileSystem.open(new Path("hdfsExample/test/a.txt"));
        IOUtils.copyBytes(inputStream, System.out, 1024);
        inputStream.close();
    }

    @Test
    public void rename() throws Exception {
        Path oldPath = new Path("hdfsExample/test/a.txt");
        Path newPath = new Path("hdfsExample/test/b.txt");

        fileSystem.rename(oldPath, newPath);
    }

    @Test
    public void copyFromLocal() throws Exception {
        Path localPath = new Path("/home/vaderwang/Desktop/kafka-canal.rar");
        Path HDFSPath = new Path("hdfsExample/test/kafka-canal.rar");

        fileSystem.copyFromLocalFile(localPath, HDFSPath);
    }

    @Test
    public void copyFromLocalWithProgress() throws Exception {
        Path localPath = new Path("/home/vaderwang/software/spark-2.3.0-bin-hadoop2.6.tgz");
        Path HDFSPath = new Path("hdfsExample/test/spark-2.3.0-bin-hadoop2.6.tgz");

        fileSystem.copyFromLocalFile(localPath, HDFSPath);

        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("/home/vaderwang/software/spark-2.3.0-bin-hadoop2.6.tgz")));

        FSDataOutputStream outputStream = fileSystem.create(new Path("hdfsExample/test/spark-2.3.0.tgz"), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });

        IOUtils.copyBytes(inputStream, outputStream, 4096);
    }

    @Test
    public void copyToLocal() throws Exception {
        Path HDFSPath = new Path("hdfsExample/test/kafka-canal.rar");
        Path localPath = new Path("/home/vaderwang/Desktop/kafka-canal.rar");

        fileSystem.copyToLocalFile(HDFSPath, localPath);
    }

    @Test
    public void listFiles() throws Exception {
//        Path HDFSPath = new Path("hdfsExample/test/");
        Path HDFSPath = new Path("/software");


        FileStatus[] fileStatuses = fileSystem.listStatus(HDFSPath);

        for (FileStatus fileStatus : fileStatuses) {
            String isDir = fileStatus.isDirectory() ? "directory" : "file";
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            String path = fileStatus.getPath().toString();
            System.out.println(isDir + " " + replication + " " + len + " " + path);
        }
    }

    @Test
    public void delete() throws Exception {
        Path HDFSPath = new Path("hdfsExample/test/kafka-canal.rar");
        fileSystem.delete(HDFSPath, true);
    }

    @Before
    public void setUp() throws Exception{
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "vaderwang");
        System.out.println("HDFS setUp");
    }

    @After
    public void tearDown() throws Exception{
        configuration = null;
        fileSystem = null;
        System.out.println("HDFS tearDown");

    }
}
```

### MapReduce

```java
package com.iceberg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCount {

    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

        LongWritable one = new LongWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            String[] words = line.split(" ");

            for (String word : words) {
                context.write(new Text(word), one);
            }
        }
    }

    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long sum = 0;

            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }

    public static void main(String[] args) throws Exception {

        //创建Configuration
        Configuration configuration = new Configuration();

        // 准备清理已存在的输出目录
        Path outputPath = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(configuration);
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath, true);
            System.out.println("output file exists, has deleted");
        }

        //创建Job
        Job job = Job.getInstance(configuration, "WordCount");

        //设置job的处理类
        job.setJarByClass(WordCount.class);

        //设置作业处理的输入路径
        // hdfs://localhost:9000/hello.txt
        // /home/vaderwang/hello.txt
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //设置map相关参数
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //设置reduce相关参数
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
```

```java
package com.iceberg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * with combiner: ok case : sum
 *                bad case: avg
 */
public class WordCountCombiner {

    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

        LongWritable one = new LongWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            String[] words = line.split(" ");

            for (String word : words) {
                context.write(new Text(word), one);
            }
        }
    }

    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long sum = 0;

            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }

    public static void main(String[] args) throws Exception {

        //创建Configuration
        Configuration configuration = new Configuration();

        // 准备清理已存在的输出目录
        Path outputPath = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(configuration);
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath, true);
            System.out.println("output file exists, has deleted");
        }

        //创建Job
        Job job = Job.getInstance(configuration, "WordCount");

        //设置job的处理类
        job.setJarByClass(WordCountCombiner.class);

        //设置作业处理的输入路径
        // hdfs://localhost:9000/hello.txt
        // /home/vaderwang/hello.txt
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //设置map相关参数
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //设置reduce相关参数
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //通过job设置combiner处理类，其实逻辑上和reduce是一模一样的
        job.setCombinerClass(MyReducer.class);

        //设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
```

```java
package com.iceberg;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountPartitioner {

    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            String line = value.toString();

            String[] info = line.split(" ");

            context.write(new Text(info[0]), new LongWritable(Long.parseLong(info[1])));
        }
    }

    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long sum = 0;

            for (LongWritable value : values) {
                sum += value.get();
            }
            context.write(key, new LongWritable(sum));
        }
    }


    public static class MyPartitioner extends Partitioner<Text, LongWritable> {

        @Override
        public int getPartition(Text text, LongWritable longWritable, int i) {
            if (text.toString().equals("xiaomi")) {
                return 0;
            } else if (text.toString().equals("iphone")) {
                return 1;
            } else if (text.toString().equals("huawei")) {
                return 2;
            }
            return 3;
        }
    }

    public static void main(String[] args) throws Exception {

        //创建Configuration
        Configuration configuration = new Configuration();

        // 准备清理已存在的输出目录
        Path outputPath = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(configuration);
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath, true);
            System.out.println("output file exists, has deleted");
        }

        //创建Job
        Job job = Job.getInstance(configuration, "WordCount");

        //设置job的处理类
        job.setJarByClass(WordCountPartitioner.class);

        //设置作业处理的输入路径
        // hdfs://localhost:9000/hello.txt
        // /home/vaderwang/hello.txt
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //设置map相关参数
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //设置reduce相关参数
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //设置job的partition
        job.setPartitionerClass(MyPartitioner.class);

        //设置4个reducer，每个分区一个
        job.setNumReduceTasks(4);
        //设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
```

### MapReduce实战分析日志

```bash
git clone git@github.com:LeeKemp/UserAgentParser.git
```

```bash
cd UserAgentParser/
```

```bash
mvn clean package -DskipTests
```

安装到本地maven仓库

```bash
mvn clean install -DskipTests
```

```xml
<dependency>
    <groupId>com.kumkee</groupId>
    <artifactId>UserAgentParser</artifactId>
    <version>0.0.1</version>
</dependency>
```

简单测试

先提取日志文件的前100条

```bash
head -n 100 access.20161111.log > 100_access.log
```

```java
package com.iceberg;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentLocal {
    public static void main( String[] args ) throws Exception {
//        String path = "/home/vaderwang/Github/Hadoop/data/100_access.log";
        String path = "/home/vaderwang/Videos/SparkSQL/data/access.20161111.log";

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));

        String line = "";

        UserAgentParser userAgentParser = new UserAgentParser();
        HashMap<String, Integer> map = new HashMap<>();
        while (line != null) {
            line = reader.readLine();
            System.out.println(line);
            if (line != null && !line.equals("")) {
                String source = line.substring(getCharacterPosition(line, "\"", 7) + 1);
                System.out.println(source);
                UserAgent agent = userAgentParser.parse(source);
                String browser = agent.getBrowser();
                String engine = agent.getEngine();
                String engineVersion = agent.getEngineVersion();
                String os = agent.getOs();
                String platform = agent.getPlatform();
                boolean mobile = agent.isMobile();

                map.put(browser, map.getOrDefault(browser, 0) + 1);
//                System.out.println(browser + " " + engine + " " + engineVersion + " " + os + " " + platform + " " + mobile);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }

    private static int getCharacterPosition(String value, String operator, int index) {
        Matcher matcher = Pattern.compile(operator).matcher(value);
        int matchIndex = 0;
        while (matcher.find()) {
            matchIndex ++;
            if (matchIndex == index) {
                break;
            }
        }
        return matcher.start();
    }
}
```

```java
package com.iceberg;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentMapReduce {

    private static int getCharacterPosition(String value, String operator, int index) {
        Matcher matcher = Pattern.compile(operator).matcher(value);
        int matchIndex = 0;
        while (matcher.find()) {
            matchIndex ++;
            if (matchIndex == index) {
                break;
            }
        }
        return matcher.start();
    }

    /**
     * Map：读取输入的文件
     */
    public static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        LongWritable one = new LongWritable(1);

        UserAgentParser userAgentParser = null;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            this.userAgentParser = new UserAgentParser();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            // 接收到的每一行数据
            String line = value.toString();

            String source = line.substring(getCharacterPosition(line, "\"", 7) + 1);

            UserAgent agent = userAgentParser.parse(source);

            String browser = agent.getBrowser();

            context.write(new Text(browser), one);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            userAgentParser = null;
        }
    }

    /**
     * Reduce：归并操作
     */
    public static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

            long sum = 0;
            for(LongWritable value : values) {
                // 求key出现的次数总和
                sum += value.get();
            }

            // 最终统计结果的输出
            context.write(key, new LongWritable(sum));
        }
    }

    /**
     * 定义Driver：封装了MapReduce作业的所有信息
     */
    public static void main(String[] args) throws Exception{

        //创建Configuration
        Configuration configuration = new Configuration();

        // 准备清理已存在的输出目录
        Path outputPath = new Path(args[1]);
        FileSystem fileSystem = FileSystem.get(configuration);
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath, true);
            System.out.println("output file exists, but is has deleted");
        }

        //创建Job
        Job job = Job.getInstance(configuration, "UserAgentLog");

        //设置job的处理类
        job.setJarByClass(UserAgentMapReduce.class);

        //设置作业处理的输入路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //设置map相关参数
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //设置reduce相关参数
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //通过job设置combiner处理类，其实逻辑上和我们的reduce是一模一样的
        job.setCombinerClass(MyReducer.class);

        job.setNumReduceTasks(4);

        //设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```



