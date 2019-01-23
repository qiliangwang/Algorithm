---
title: Graph Paper
date: 2018-09-22 13:44:28
tags:
---

## 数据处理

第一步需要的是词性的提取，这里使用jieba分词来提取词性。

```shell
pip install -i https://pypi.doubanio.com/simple jieba
```

先读取文件，文件比较大一行一行读取。

```python
def read_data():
    file_dir = "./data/corpus.txt"
    data = []
    with open(file_dir, 'r', encoding='utf-8') as f:
        while True:
            line = f.readline()
            if line:
                content = line.strip('<content>').strip('</content>').strip()
                if len(content) > 0:
                    sentences = content.split('。')
                    data.extend(sentences)
            if not line:
                break
    return data
```

利用结巴分词获取每个词语的词性

```python
from jieba import posseg


def part_of_speech(sentence):
    return [flag for word, flag in posseg.cut(sentence)]
```

之前的数据序列化保存了所以需要一个函数来读取数据

```python
def load_data(file_name='part_of_speech_list.pkl'):
    file_dir = os.path.join('./data', file_name)
    return pickle.load(open(file_dir, 'rb'))
```

对每个词性的列表加上START和END

```python
def start_end(part_of_speech_list):
    return ['START'] + part_of_speech_list + ['END']
```

样本本身还比较多，计算量比较大，先把这部分的结果序列号保存起来

```python
from multiprocessing import Pool
import pickle
import os
from jieba import posseg


def read_data():
    file_dir = "./data/corpus.txt"
    data = []
    with open(file_dir, 'r', encoding='utf-8') as f:
        while True:
            line = f.readline()
            if line:
                content = line.strip('<content>').strip('</content>').strip()
                if len(content) > 0:
                    sentences = content.split('。')
                    data.extend(sentences)
            if not line:
                break
    return data


def part_of_speech(sentence):
    return [flag for word, flag in posseg.cut(sentence)]


def load_data(file_name='part_of_speech_list.pkl'):
    file_dir = os.path.join('./data', file_name)
    return pickle.load(open(file_dir, 'rb'))


def start_end(part_of_speech_list):
    return ['START'] + part_of_speech_list + ['END']


def main():
    data = read_data()

    pool = Pool(8)

    part_of_speech_result = pool.map(part_of_speech, data)
    
    part_of_speech_result = pool.map(start_end, part_of_speech_result)

    pickle.dump(part_of_speech_result, open('./data/part_of_speech_list.pkl', 'wb'))


if __name__ == '__main__':
    main()

```

```python
part_of_speech_result[:10]
```

['START', 'nz', 'nr', 'x', 'm', 'x', 'x', 'm', 'n', 'x', 'n', 'nr', 'x', 'nr', 'x', 'p', 'ns', 'u', 'q', 'x', 'n', 'tg', 'nr', 'x', 'v', 'x', 't', 'x', 'x', 'x', 'm', 'x', 't', 'x', 'n', 'x', 'x', 'x', 'x', 'n', 'v', 'n', 'vn', 'x', 'vn', 'v', 'b', 'n', 'v', 'n', 'vn', 'x', 'p', 'ns', 'nr', 'ad', 'vn', 'x', 'x', 'x', 'm', 'b', 'n', 'x', 'x', 'x', 'x', 'm', 'n', 'p', 'n', 'x', 'x', 'x', 'q', 'ns', 'p', 'vn', 'v', 'END']

 ['START', 'nr', 'x', 't', 'r', 'v', 'x', 'n', 'v', 'v', 'vn', 'v', 'vn', 'v', 'x', 'p', 'n', 'b', 'n', 'nr', 'l', 'x', 'n', 'v', 'k', 'n', 'c', 'n', 'x', 'n', 'x', 'n', 'p', 'ns', 'nr', 'n', 'p', 'v', 'nz', 'END']

 ['START', 'c', 'x', 'p', 'n', 'r', 'n', 'x', 'x', 'x', 'q', 'ns', 'x', 'v', 'v', 'c', 'n', 'b', 'v', 'uj', 'x', 'x', 'm', 'b', 'n', 'p', 'v', 'v', 'x', 'p', 'n', 'r', 'v', 'n', 'x', 'x', 'x', 'x', 'm', 'n', 'p', 'd', 'v', 'END']

 ['START', 'n', 'ns', 'n', 'zg', 'ns', 'v', 'x', 'r', 'v', 'uj', 'b', 'n', 'v', 'm', 'n', 'END'], ['START', 'zg', 'ns', 'x', 'v', 'v', 'v', 'v', 'x', 'v', 'uj', 'n', 'vn', 'x', 'n', 'vn', 'x', 'v', 'p', 's', 'b', 'n', 'uj', 'n', 'n', 'END']

['START', 'p', 'v', 'n', 'x', 'n', 'v', 'ul', 'p', 'v', 'c', 'v', 'uj', 'l', 'uj', 'n', 'END']

['START', 'zg', 'ns', 'x', 'd', 'v', 'n', 'n', 'x', 'l', 'n', 'n', 'x', 'v', 'uj', 'n', 'v', 'x', 'v', 'n', 'x', 'n', 'uj', 'n', 'u', 'END']

['START', 'p', 'r', 'uj', 'n', 'n', 'x', 'r', 'd', 'v', 'ad', 'v', 'n', 'b', 'v', 'uj', 'END']

['START', 'ns', 'v', 'n', 'f', 'n', 'vn', 'uj', 'n', 'a', 'uj', 'n', 'r', 'END']

['START', 'f', 'ns', 'v', 'v', 'a', 'n', 'n', 'n', 'n', 't', 'x', 'f', 'ns', 'v', 'n', 'v', 'a', 'v', 'a', 'n', 'n', 'n', 'n', 'x', 'p', 'ns', 'v', 'vn', 'n', 'x', 'x', 'q', 'x', 'v', 'r', 'n', 'x', 'x', 'n', 'x', 'n', 'x', 'm', 'v', 'n', 'n', 'n', 'END']

这样我们有了1000+W条数据，如果这些词性之间关系是一张图的话，首先要找出全部的节点，然后再计算边以及边对应的权重。

求出全部的节点：

```python
def unique_node(part_of_speech_result):
    nodes = set()
    for part_of_speech_list in part_of_speech_result:
        for node in part_of_speech_list:
            if node in nodes:
                pass
            else:
                nodes.add(node)
    return nodes
```

求出边的权重：

```python
def weight_of_edge(part_of_speech_result):
    weights = {}
    for part_of_speech_list in part_of_speech_result:
        for i in range(len(part_of_speech_list) - 1):
            edge = part_of_speech_list[i] + '->' + part_of_speech_list[i + 1]
            if edge in weights:
                weights[edge] = weights[edge] + 1
            else:
                weights[edge] = 1
    return weights
```

序列化保存数据

```python
part_of_speech_result = load_data('part_of_speech_result.pkl')
```

```python
nodes = unique_node(part_of_speech_result)
pickle.dump(nodes, open('./data/nodes.pkl', 'wb'))
```

Nodes：

{'dg', 'mq', 'mg', 'a', 'ad', 'vi', 'ag', 'nt', 'zg', 'c', 'eng', 'z', 'd', 'nrfg', 'r', 'm', 'q', 'uj', 'df', 'ul', 'yg', 'i', 'vn', 'rg', 'k', 'nr', 'tg', 'uv', 'ug', 'h', 'vq', 'ud', 'g', 'n', 'l', 'START', 'f', 'END', 'rz', 'vd', 'v', 'bg', 'nz', 'ns', 'p', 'b', 's', 'y', 'vg', 'rr', 'an', 'ng', 'e', 'j', 'u', 'nrt', 'x', 'uz', 'in', 't', 'o'}

```python
weights = weight_of_edge(part_of_speech_result)
pickle.dump(weights, open('./data/weights.pkl', 'wb'))
```

Weights：

{'START->nz': 253973, 'nz->nr': 51328, 'nr->x': 2280292, 'x->m': 6453301, 'm->x': 3677139, 'x->x': 29829019, 'm->n': 3306224, 'n->x': 17149134, 'x->n': 10645168, 'n->nr': 1537901, 'x->nr': 2852514, 'x->p': 2716179, 'p->ns': 1238000, 'ns->u': 100464......}

接下来把数据存到Neo4J中

```shell
pip install -i https://pypi.doubanio.com/simple py2neo
```

可能出现版本问题

```shell
pip install -i https://pypi.doubanio.com/simple neotime==1.0.0
```

接下来先把节点存入数据库

```python
def insert_node():
    graph = Graph()
    nodes = load_data('nodes.pkl')
    for node in nodes:
        graph.create(Node('PartOfSpeech', value=node))
```

现在是边

```python
def insert_weight():
    graph = Graph()
    weights = load_data('weights.pkl')
    for key, value in weights.items():
        node_from, node_to = str(key).split('->')
        node_from = graph.nodes.match("PartOfSpeech", value=node_from).first()
        node_to = graph.nodes.match("PartOfSpeech", value=node_to).first()
        graph.create(Relationship(node_from, 'CONNECT', node_to, weight=value))
```

