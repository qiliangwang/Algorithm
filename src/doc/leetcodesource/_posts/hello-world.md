---
title: Hello World
date: 2019-03-21 16:12:15
tags:
---
Welcome to [Hexo](https://hexo.io/)! This is your very first post. Check [documentation](https://hexo.io/docs/) for more info. If you get any problems when using Hexo, you can find the answer in [troubleshooting](https://hexo.io/docs/troubleshooting.html) or you can ask me on [GitHub](https://github.com/hexojs/hexo/issues).

## Quick Start

### Create a new post

``` bash
$ hexo new "My New Post"
```

More info: [Writing](https://hexo.io/docs/writing.html)

### Run server

``` bash
$ hexo server
```

More info: [Server](https://hexo.io/docs/server.html)

### Generate static files

``` bash
$ hexo generate
```

More info: [Generating](https://hexo.io/docs/generating.html)

### Deploy to remote sites

``` bash
$ hexo deploy
```

More info: [Deployment](https://hexo.io/docs/deployment.html)



想要把本地的Hexo部署到GitHub上面需要几个步骤



.找到`Hexo`下的`_config.yml`里的`post_asset_folder`，把这个选项从`false`改成`true`。



在`Hexo`目录下打开Git Brsh，执行一个下载上传图片插件的命令`npm install hexo-asset-image --save`。



_config.yml里面root: /LeetcodeTutorial/

原文件的 root：/  指向的是根目录，在后面添加上 仓库名

即修改为 root：/LeetcodeTutorial

