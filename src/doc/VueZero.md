---
title: Vue Zero
date: 2018-10-07 00:40:23
tags:
---



其实，一开始我应聘的是Spark，Hadoop这样的，然后后面呢，发现只有Java的业务给我写了，再后面我发现，公司招不到前端，所以前端要由后端来写，刺激！！！

## 数据驱动

首先要明白一个概念，那就是DOM其实是数据的一种映射，在之前jQuery的时代，当数据发生改变时，我们需要手动改变DOM，而Vue的目的就是省去手动操作修改DOM。在Vue中，需要改变的仅仅是数据。当数据改变时，Vue通过directives指令对DOM进行修改。Vue还会对视图进行监听，当视图改变的时候，数据也会跟着一起变化。

![](VueZero\00.PNG)

## 组件化

组件化是指把前端的页面拆分为一个一个的组件，这样易于代码的复用与维护。

![](VueZero\01.png)

## Vue-cli

Vue-cli是Vue的脚手架工具，它帮助我们写好Vue的基础代码。Vue-cli是一个node包，所以需要使用npm来安装它。

```shell
npm config set registry https://registry.npm.taobao.org
```

注意：webpack 4.X 开始，需要安装 webpack-cli 依赖 

```shell
npm install -g webpack webpack-cli
```

```shell
npm install -g vue-cli
```

```shell
vue init webpack sell
```

```shell
cd sell
```

```shell
npm run dev
```

http://localhost:8080/#/

这样就成功运行了一个Vue的项目。

![](VueZero\02.png)

```shell
npm install stylus stylus-loader --save
```

