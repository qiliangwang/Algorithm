---
title: Git
date: 2019-01-03 22:35:40
tags:
---

## Git安装



## Git配置

安装好git的第一步就是设置用户的基本信息

```bash
git config -- global user.name 'your_name'
git config -- global user.email 'your_email'
```

除了global我们还有其他的作用域

```bash
git config -- local   	只对某个仓库有效
git config -- global    对当前用户所有仓库有效
git config -- system	对系统所有登陆的用户有效
```

配置好了之后，想要显示Git的配置

```bash
git config --list --local
git config --list --global
git config --list --system
```

建立Git仓库

```bash
git init git_project
cd git_project

```

列出全部的Git分支

```bash
git branch -av
```

