---
title: CICD
date: 2018-12-12 23:53:22
tags:
---

## 安装Gitlab

```bash
$ docker pull registry.cn-hangzhou.aliyuncs.com/imooc/gitlab-ce:latest

$ docker pull gitlab/gitlab-ce:latest
```

### 运行GitLab容器

使用docker命令运行容器，注意修改hostname为自己喜欢的名字，-v部分挂载目录要修改为自己的目录。  
端口映射这里使用的都是安全端口，如果大家的环境没有端口限制或冲突可以使用与容器同端口，如：-p 443:443 -p 80:80 -p 22:22

#### 1. 生成启动文件 - start.sh

```bash
$ cat <<EOF > start.sh
#!/bin/bash
HOST_NAME=gitlab.iceberg.com
GITLAB_DIR=/home/vaderwang/software/gitlab
docker stop gitlab
docker rm gitlab
docker run -d \\
    --hostname \${HOST_NAME} \\
    -p 8443:443 -p 8080:80 -p 2222:22 \\
    --name gitlab \\
    -v \${GITLAB_DIR}/config:/etc/gitlab \\
    -v \${GITLAB_DIR}/logs:/var/log/gitlab \\
    -v \${GITLAB_DIR}/data:/var/opt/gitlab \\
     registry.cn-hangzhou.aliyuncs.com/imooc/gitlab-ce:latest
EOF
```

```shell
#!/bin/bash
HOST_NAME=gitlab.iceberg.com
GITLAB_DIR=/home/vaderwang/software/gitlab
docker stop gitlab
docker rm gitlab
docker run -d \
    --hostname ${HOST_NAME} \
    -p 9443:443 -p 9080:80 -p 2222:22 \
    --name gitlab \
    -v ${GITLAB_DIR}/config:/etc/gitlab \
    -v ${GITLAB_DIR}/logs:/var/log/gitlab \
    -v ${GITLAB_DIR}/data:/var/opt/gitlab \
    registry.cn-hangzhou.aliyuncs.com/imooc/gitlab-ce:latest
```

#### 2. 运行start.sh 启动gitlab

```bash
$ sh start.sh
```

```
docker logs -f xxxxx
```

## 安装Harbor

github上面搜索harbor即可，需要安装docker compose

```shell
lsof -i :80

kill -s 9 1827

./install.sh
```

## 安装Jenkis

https://jenkins.io/doc/pipeline/tour/getting-started/

java -jar jenkins.war --httpPort=8080

```shell
java -jar jenkins.war --httpPort=8080

Jenkins initial setup is required. An admin user has been created and a password generated.
Please use the following password to proceed to installation:

18349fd85b51452a903027a896b7e19c

This may also be found at: /root/.jenkins/secrets/initialAdminPassword
```

```groovy
#!groovy
pipeline {
    agent any

    environment {
        REPOSITORY="http://gitlab.com:9080/vaderwang/microservice.git"
        MODULE="user-edge-service"
    }

    stages {
        stage('获取代码') {
            steps {
                echo "start fetch code from git: ${REPOSITORY}"
                deleteDir()
                git "${REPOSITORY}"
            }
        }

        stage('编译 + 单元测试') {
            steps {
                echo "start compile"
                sh "mvn -U -pl ${MODULE} -am clean package"
            }
        }
    }
}
```





