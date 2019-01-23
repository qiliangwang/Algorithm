---
title: Flask Basic
date: 2018-10-07 00:38:51
tags:
---

## Flask

## 虚拟环境

虚拟环境是十分有用的，而pipenv可以替代virtualenv。

```shell
pip install -i https://pypi.doubanio.com/simple msgpack
```

```shell
pip install -i https://pypi.doubanio.com/simple pipenv
```

这样，pipenv就安装完成了。接下来创建一个虚拟环境

```shell
pipenv install
```

进入这个虚拟环境

```shell
pipenv shell
```

```shell
pipenv install flask

pipenv uninstall flask

pipenv graph
```

可惜呢，不知是windows的问题还是Anaconda的问题，总之，pipenv翻车了！改天ubuntu试试看

那就不pipenv了

```shell
pip install flask-restful
```

有这样一个json文件把它映射为restful的Api

![](FlaskZero\00.png)

```python
import json
from flask import Flask
import flask_restful as restful

app = Flask(__name__)
api = restful.Api(app)

with open('data.json', encoding='utf-8') as f:
    data = json.load(f)

seller = data['seller']
goods = data['goods']
ratings = data['ratings']


class Seller(restful.Resource):
    def get(self):
        return {'error': 0,
                'data': seller,
                }


class Goods(restful.Resource):
    def get(self):
        return {'error': 0,
                'data': goods,
                }


class Ratings(restful.Resource):
    def get(self):
        return {'error': 0,
                'data': seller,
                }


api.add_resource(Seller, '/api/seller')
api.add_resource(Goods, '/api/goods')
api.add_resource(Ratings, '/api/ratings')


if __name__ == '__main__':
    app.run(debug=True)

```

看着我一手python的代码，flask-restful我刚刚看，官方文档的demo还错了的情况下，我居然改对了，我真的怀疑是不是要辞去现在的JAVA开发。



http://127.0.0.1:5000/api/seller
![](FlaskZero\01.png)



http://127.0.0.1:5000/api/goods
![](FlaskZero\02.png)



http://127.0.0.1:5000/api/ratings

![](FlaskZero\03.png)

好了为Vue准备的api好了。
