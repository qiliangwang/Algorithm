---
title: Docker Kubernetes
date: 2018-12-09 10:29:28
tags:
---





## 快速体验

https://kubernetes.io/docs/tutorials/

Learn Kubernetes Basics -> Create a Cluster -> Interactive Tutorial - Creating a Cluster



## Minikube的安装

Linux

```
curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube && sudo mv minikube /usr/local/bin/
```

Windows

从 https://storage.googleapis.com/minikube/releases/latest/minikube-windows-amd64.exe 下载重命名成`minikube.exe`,然后把这个文件的所在目录添加到系统环境变量PATH里。

```
minikube version
```

ps: for specific version

```shell
curl -Lo minikube https://storage.googleapis.com/minikube/releases/v0.25.0/minikube-linux-amd64 && chmod +x minikube && sudo cp minikube /usr/local/bin/ && rm minikube
```

https://storage.googleapis.com/minikube/releases/v0.25.0/minikube-windows-amd64.exe

### 2.安装kubectl

参考https://kubernetes.io/docs/tasks/tools/install-kubectl/

Linux

```
$ curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
$ chmod +x ./kubectl
$ sudo mv ./kubectl /usr/local/bin/kubectl
```

Windows

下载exe https://storage.googleapis.com/kubernetes-release/release/v1.9.0/bin/windows/amd64/kubectl.exe
然后添加到系统PATH环境变量里

安装完以后可以查看版本（我们会看到它有一个报错，因为连不上k8s api server，这个很正常，因为k8s节点还未创建）

```
kubectl version
```

### 3.安装Virtualbox

因为minikube创建K8S虚机是通过Virtualbox来做的（当然还有其它driver，比如KVM，vmware这里就不介绍了）

https://www.virtualbox.org/wiki/Downloads 根据自己的操作系统下载安装

### 3. 运行minikube程序创建k8s

通过 `minikube starat` 去创建k8s环境。如下所示。速度根据个人网速而不同，因为需要下载东西。

```
➜  ~ minikube start
Starting local Kubernetes v1.9.0 cluster...
Starting VM...
Downloading Minikube ISO
 142.22 MB / 142.22 MB [============================================] 100.00% 0s
Getting VM IP address...
Moving files into cluster...
Downloading localkube binary
 162.41 MB / 162.41 MB [============================================] 100.00% 0s
 0 B / 65 B [----------------------------------------------------------]   0.00%
 65 B / 65 B [======================================================] 100.00% 0sSetting up certs...
Connecting to cluster...
Setting up kubeconfig...
Starting cluster components...
Kubectl is now configured to use the cluster.
Loading cached images from config file.
```

执行结束以后呢，我们可以通过`kubectl cluster-info` 去连一下k8s api server.

```
➜  ~ kubectl cluster-info
Kubernetes master is running at https://192.168.99.100:8443

To further debug and diagnose cluster problems, use 'kubectl cluster-info dump'.
```

但是，但是，此时并不代表，整个k8s集群搭建好了，因为k8s里的服务还需要起，比如API server，scheduler，kubelet等等，他们都是以容器的方式在后台启动。

那怎么判断成功了呢？

我们可以通过minikube ssh进到虚机里，然后看看是否有一些container运行起来了

```
➜  ~ minikube ssh
                         _             _
            _         _ ( )           ( )
  ___ ___  (_)  ___  (_)| |/')  _   _ | |_      __
/' _ ` _ `\| |/' _ `\| || , <  ( ) ( )| '_`\  /'__`\
| ( ) ( ) || || ( ) || || |\`\ | (_) || |_) )(  ___/
(_) (_) (_)(_)(_) (_)(_)(_) (_)`\___/'(_,__/'`\____)

$ docker ps
CONTAINER ID        IMAGE                                      COMMAND                  CREATED             STATUS              PORTS               NAMES
c1aec3464788        gcr.io/k8s-minikube/storage-provisioner    "/storage-provisioner"   8 minutes ago       Up 8 minutes                            k8s_storage-provisioner_storage-provisioner_kube-system_05db116c-236a-11e8-985b-08002709a6e3_0
f36a4455bc73        fed89e8b4248                               "/sidecar --v=2 --..."   8 minutes ago       Up 8 minutes                            k8s_sidecar_kube-dns-54cccfbdf8-c7k5b_kube-system_0649fdef-236a-11e8-985b-08002709a6e3_0
e49e279c5717        459944ce8cc4                               "/dnsmasq-nanny -v..."   8 minutes ago       Up 8 minutes                            k8s_dnsmasq_kube-dns-54cccfbdf8-c7k5b_kube-system_0649fdef-236a-11e8-985b-08002709a6e3_0
1ec9842ec31d        512cd7425a73                               "/kube-dns --domai..."   8 minutes ago       Up 8 minutes                            k8s_kubedns_kube-dns-54cccfbdf8-c7k5b_kube-system_0649fdef-236a-11e8-985b-08002709a6e3_0
805346db62f0        e94d2f21bc0c                               "/dashboard --inse..."   8 minutes ago       Up 8 minutes                            k8s_kubernetes-dashboard_kubernetes-dashboard-77d8b98585-ghb5p_kube-system_0630dbdf-236a-11e8-985b-08002709a6e3_0
2c3edf77349e        gcr.io/google_containers/pause-amd64:3.0   "/pause"                 8 minutes ago       Up 8 minutes                            k8s_POD_kube-dns-54cccfbdf8-c7k5b_kube-system_0649fdef-236a-11e8-985b-08002709a6e3_0
19313f1076da        gcr.io/google_containers/pause-amd64:3.0   "/pause"                 8 minutes ago       Up 8 minutes                            k8s_POD_kubernetes-dashboard-77d8b98585-ghb5p_kube-system_0630dbdf-236a-11e8-985b-08002709a6e3_0
a340e072f095        gcr.io/google_containers/pause-amd64:3.0   "/pause"                 8 minutes ago       Up 8 minutes                            k8s_POD_storage-provisioner_kube-system_05db116c-236a-11e8-985b-08002709a6e3_0
dab3d03f880b        d166ffa9201a                               "/opt/kube-addons.sh"    8 minutes ago       Up 8 minutes                            k8s_kube-addon-manager_kube-addon-manager-minikube_kube-system_c4c3188325a93a2d7fb1714e1abf1259_0
75c8fe4570a1        gcr.io/google_containers/pause-amd64:3.0   "/pause"                 8 minutes ago       Up 8 minutes                            k8s_POD_kube-addon-manager-minikube_kube-system_c4c3188325a93a2d7fb1714e1abf1259_0
```

然后退出来，在本地运行`minikube dashboard` 会在本地弹出浏览器，就是Kubernetes的dashboard，安装成功。

```
➜  ~ minikube dashboard
Opening kubernetes dashboard in default browser...
```

## Ubuntu下Kubernetes集群部署

<http://mirrors.aliyun.com/ubuntu-releases/16.04/>

选择ubuntu_server下载安装

设置root用户

```shell
sudo passwd
```

enter current user password (kubernetes kubernetes for example )
enter root password later (root root for example)

remove old version

```shell
sudo apt-get remove docker docker-engine docker.io
```

install docker

```
echo "deb https://download.docker.com/linux/ubuntu zesty edge" > /etc/apt/sources.list.d/docker.list
```

```
apt update && apt install docker-ce
```

install ssh

```shell
sudo apt update
sudo apt install -y openssh-server
```

接着打开本地Terminal

```shell
ssh kubernetes@192.168.50.130
ssh kubernetes@192.168.50.131
ssh kubernetes@192.168.50.132 
```

接受所有ip的数据包转发

```bash
$ vi /lib/systemd/system/docker.service

#找到ExecStart=xxx，在这行上面加入一行，内容如下：(k8s的网络需要)
ExecStartPost=/sbin/iptables -I FORWARD -s 0.0.0.0/0 -j ACCEPT
```

- 启动服务

```shell
scp kubernetes-bins.tar.gz kubernetes@192.168.50.130:~
scp kubernetes-bins.tar.gz kubernetes@192.168.50.131:~
scp kubernetes-bins.tar.gz kubernetes@192.168.50.132:~
```

安装kubernetes

```shell
tar -xvf kubernetes-bins.tar.gz

mv kubernetes-bins/ bin
```

```BASH
vim /etc/profile
export KUBERNETES_HOME=/home/kubernetes
export PATH=$KUBERNETES_HOME/bin:$PATH
source /etc/profile
```

```shell
vim config.properties 

#kubernetes二进制文件目录,eg: /home/michael/bin
BIN_PATH=/home/kubernetes/bin
/home/kubernetes/bin

#当前节点ip, eg: 192.168.1.102
NODE_IP=192.168.50.130

#etcd服务集群列表, eg: http://192.168.1.102:2379
#如果已有etcd集群可以填写现有的。没有的话填写：http://${MASTER_IP}:2379 （MASTER_IP自行替换成自己的主节点ip）
ETCD_ENDPOINTS=http://192.168.50.130:2379

#kubernetes主节点ip地址, eg: 192.168.1.102
MASTER_IP=192.168.50.130
```

启动服务

```bash
netstat -ntlp
```

基础配置

ETCD（主节点）

```bash
#把服务配置文件copy到系统服务目录
$ cp ~/kubernetes-starter/target/master-node/etcd.service /lib/systemd/system/
#enable服务
$ systemctl enable etcd.service
#创建工作目录(保存数据的地方)
$ mkdir -p /var/lib/etcd
# 启动服务
$ service etcd start
# 查看服务日志，看是否有错误信息，确保服务正常
$ journalctl -f -u etcd.service
```

APIServer（主节点）

```bash
$ cp target/master-node/kube-apiserver.service /lib/systemd/system/
$ systemctl enable kube-apiserver.service
$ service kube-apiserver start
$ journalctl -f -u kube-apiserver
```

ControllerManager（主节点）

```bash
$ cp target/master-node/kube-controller-manager.service /lib/systemd/system/
$ systemctl enable kube-controller-manager.service
$ service kube-controller-manager start
$ journalctl -f -u kube-controller-manager
```

Scheduler（主节点）

```bash
$ cp target/master-node/kube-scheduler.service /lib/systemd/system/
$ systemctl enable kube-scheduler.service
$ service kube-scheduler start
$ journalctl -f -u kube-scheduler
```

部署CalicoNode（所有节点）

```bash
$ cp target/all-node/kube-calico.service /lib/systemd/system/
$ systemctl enable kube-calico.service
$ service kube-calico start
$ journalctl -f -u kube-calico
```

```bash
$ docker ps
CONTAINER ID   IMAGE                COMMAND        CREATED ...
4d371b58928b   calico/node:v2.6.2   "start_runit"  3 hours ago...
```

```bash
$ calicoctl node status
Calico process is running.
IPv4 BGP status
+---------------+-------------------+-------+----------+-------------+
| PEER ADDRESS  |     PEER TYPE     | STATE |  SINCE   |    INFO     |
+---------------+-------------------+-------+----------+-------------+
| 192.168.1.103 | node-to-node mesh | up    | 13:13:13 | Established |
+---------------+-------------------+-------+----------+-------------+
IPv6 BGP status
No IPv6 peers found.
```

配置kubectl命令（任意节点）

```bash
#指定apiserver地址（ip替换为你自己的api-server地址）
kubectl config set-cluster kubernetes  --server=http://192.168.210.131:8080
#指定设置上下文，指定cluster
kubectl config set-context kubernetes --cluster=kubernetes
#选择默认的上下文
kubectl config use-context kubernetes
```

配置kubelet（工作节点）

```bash
#确保相关目录存在
$ mkdir -p /var/lib/kubelet
$ mkdir -p /etc/kubernetes
$ mkdir -p /etc/cni/net.d

#复制kubelet服务配置文件
$ cp target/worker-node/kubelet.service /lib/systemd/system/
#复制kubelet依赖的配置文件
$ cp target/worker-node/kubelet.kubeconfig /etc/kubernetes/
#复制kubelet用到的cni插件配置文件
$ cp target/worker-node/10-calico.conf /etc/cni/net.d/

$ systemctl enable kubelet.service
$ service kubelet start
$ journalctl -f -u kubelet
```

```bash
kubectl get nodes

NAME              STATUS    ROLES     AGE       VERSION
192.168.210.130   Ready     <none>    21h       v1.9.0
192.168.210.132   Ready     <none>    21h       v1.9.0
```

kube-proxy（工作节点）

```bash
#确保工作目录存在
$ mkdir -p /var/lib/kube-proxy
#复制kube-proxy服务配置文件
$ cp target/worker-node/kube-proxy.service /lib/systemd/system/
#复制kube-proxy依赖的配置文件
$ cp target/worker-node/kube-proxy.kubeconfig /etc/kubernetes/

$ systemctl enable kube-proxy.service
$ service kube-proxy start
$ journalctl -f -u kube-proxy
```

kube-dns（app）

```bash
#到kubernetes-starter目录执行命令
$ kubectl create -f target/services/kube-dns.yaml
```

```bash
root@ubuntu:/home/kubernetes/kubernetes-starter# kubectl -n kube-system get svc
NAME       TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)         AGE
kube-dns   ClusterIP   10.68.0.2    <none>        53/UDP,53/TCP   5s
```

```bash
root@ubuntu:/home/kubernetes/kubernetes-starter# kubectl -n kube-system get deploy
NAME       DESIRED   CURRENT   UP-TO-DATE   AVAILABLE   AGE
kube-dns   1         1         1            1           3m
```

## WITH CA

