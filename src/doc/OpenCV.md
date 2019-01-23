---
title: OpenCV
date: 2018-12-03 23:05:26
tags:
---

## OpenCV基础

```shell
pip install -i https://pypi.doubanio.com/simple opencv-python
```



## OpenCV+Tensorflow实战

首先安装Tensorflow，由于要配合cuda的版本，所以使用了比较老的tf版本。

一年前写的东西现在看看有点小怀念，一年前生日前一天我居然在写代码！！！

https://zhuanlan.zhihu.com/p/31574549

## **CPU版本安装**

cpu不需要安装cuda所以版本就默认最新就好了

```text
pip install -i https://pypi.doubanio.com/simple tensorflow
```

## **GPU版本安装**

```text
pip install -i https://http://pypi.tuna.tsinghua.edu.cn/simple tensorflow-gpu==1.4
```

如果之前安装过cpu版本的就先

```text
pip uninstall tensorflow
```

下载cuda和cudnn(注意版本)cuda_8和cudnn_v6.0

链接：https://pan.baidu.com/s/1Oiin3QsFAdT95P1RMdHQQA 
提取码：8mv0 

cuda默认安装目录

![img](https://pic2.zhimg.com/80/v2-0375d302130679a2032a9d133f21ef09_hd.jpg)

把下面目录加入环境变量

C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v8.0\bin

C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v8.0\lib\x64

解压cudnn

把cudnn解压后

![img](https://pic2.zhimg.com/80/v2-8fa46b8a0a5152c0bba3cd7f29e8b079_hd.jpg)

把文件复制到cuda安装的目录（C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v8.0）

把 C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v8.0\extras\CUPTI\libx64 下的 cupti64_80.dll 复制到C:\ProgramFiles\NVIDIA GPU ComputingToolkit\CUDA\v8.0\bin 里面

TensorflowGPU安装成功

## 卷积神经网络去噪

数据是经典的MNIST数据集

## 猫狗大战

数据来源与kaggle

## AI医疗

数据来源于kaggle

https://www.kaggle.com/paultimothymooney/chest-xray-pneumonia

下载好后我们就有了数据集

```python

```

## 超分辨率算法

训练的图片

链接：https://pan.baidu.com/s/1vGvsLx8AlBG9PuL1Pg_9hg 
提取码：dq5e 

对于超分辨率算法我们要想到输入和输出，输入是一张低分辨率的图片，输出是一张高分辨率的图片，所以我们先要使用高分辨率的图片创制低分辨率的图片然后用低分辨率的作为输入，高分辨率作为输出，这样就可以得到我们想要的结果。

第一步是准备数据

对于我们而言数据就是图片，第一步是要把图片处理为分辨率相同的放在一个文件夹里面作为数据集合

```python
import os
import skimage
import skimage.io
import skimage.transform


def prepare_image(folder_dir='D:\image'):
    image_list = []
    find_image(folder_dir, image_list)
    return image_list


def find_image(file_dir, file_list):
    results = [os.path.join(file_dir, result) for result in os.listdir(file_dir)]
    for result in results:
        if os.path.isdir(result):
            find_image(result, file_list)
        else:
            file_list.append(result)
    pass


def save_image(image, image_dir, image_number):
    base_dir = '../images'
    (file_path, complete_filename) = os.path.split(image_dir)
    (filename, extension) = os.path.splitext(complete_filename)
    new_filename = str(image_number) + str(extension)
    data_dir = os.path.join(base_dir, 'data', new_filename)
    print('save' + data_dir)
    label_dir = os.path.join(base_dir, 'label', new_filename)
    print('save' + label_dir)
    image_resize = resize_image(image, label_dir, edge=512)
    resize_data(image_resize, data_dir)
    pass


def resize_data(image,  image_dir):
    image_resize = skimage.transform.resize(image, (128, 128), mode='constant')
    new_image_resize = skimage.transform.resize(image_resize, (512, 512), mode='constant')
    skimage.io.imsave(image_dir, new_image_resize)
    pass


def resize_image(image,  image_dir, edge=512):
    short_edge = min(image.shape[:2])
    start_x = int((image.shape[1] - short_edge) / 2)
    start_y = int((image.shape[0] - short_edge) / 2)
    crop_image = image[start_y: start_y + short_edge, start_x: start_x + short_edge]
    image_resize = skimage.transform.resize(crop_image, (edge, edge), mode='constant')
    skimage.io.imsave(image_dir, image_resize)
    return image_resize
    pass


def convert_image(image_list):
    image_number = 0
    for image_dir in image_list:
        try:
            image = skimage.io.imread(image_dir)
            image_number += 1
            save_image(image, image_dir, image_number)
        except:
            print(image_dir + " not an image")
    pass


def main():
    image_list = prepare_image()
    convert_image(image_list)
    pass


if __name__ == '__main__':
    main()

```

这样在项目的images文件夹里面就有了我们需要的数据，我这里一共是7k多张图片勉强够用作训练的数据。

数据准备好了之后，就是标准的机器学习步骤了。

第一：要把数据变成机器学习可以解析的格式，第二：选择合适的机器学习，第三：训练模型。

处理数据的类

```python
import os
import cv2 as cv
import numpy as np


class SuperResolutionData:
    def __init__(self, base_dir='../images'):

        self.data_dir = os.path.join(base_dir, 'data')
        self.label_dir = os.path.join(base_dir, 'label')

        self.data = [os.path.join(self.data_dir, img_dir) for img_dir in os.listdir(self.data_dir)]
        self.label = [os.path.join(self.label_dir, img_dir) for img_dir in os.listdir(self.label_dir)]
        pass

    @staticmethod
    def parse_img(path, num, dim):
        img = cv.imread(path)
        img = img / 255.0

        images = []
        len_x = img.shape[0] // num
        for height in range(num):
            for width in range(num):
                temp_img = img[height * len_x:height * len_x + len_x, width * len_x:width * len_x + len_x, :]
                temp_img = cv.resize(temp_img, (dim, dim))
                temp_img = temp_img.reshape(-1, dim, dim, 3)
                images.append(temp_img)
        return images

    def convert_paths_to_nd_array(self, paths, num):
        images = []
        for path in paths:
            img = self.parse_img(path, num, 128)
            images.extend(img)
        images = np.concatenate(images)
        return images

    def build_data(self, epochs, batch_size, shuffle=True):
        for epoch in range(epochs):
            for batch_x, batch_y in self.batch_iter(self.data, self.label, batch_size, shuffle):
                yield self.convert_paths_to_nd_array(batch_x, 4), self.convert_paths_to_nd_array(batch_y, 4)
        pass

    @staticmethod
    def convert_img(img_dirs):

        def parse_img(img_dir):
            img = cv.imread(img_dir) / 255.0
            img = cv.resize(img, (128, 128))
            img = img.reshape(-1, 128, 128, 3)
            return img

        images = [parse_img(img_dir) for img_dir in img_dirs]
        return np.concatenate(images)

    @staticmethod
    def batch_iter(data, labels, batch_size, shuffle=True):
        data_size = len(data)
        data = np.array(data)
        labels = np.array(labels)
        num_batches = ((data_size - 1) // batch_size) + 1
        if shuffle:
            shuffle_indices = np.random.permutation(np.arange(data_size))
            shuffled_x = data[shuffle_indices]
            shuffled_y = labels[shuffle_indices]
        else:
            shuffled_x = data
            shuffled_y = labels
        for batch_num in range(num_batches):
            start_index = batch_num * batch_size
            end_index = min((batch_num + 1) * batch_size, data_size)
            yield shuffled_x[start_index:end_index], shuffled_y[start_index:end_index]

    @staticmethod
    def plot_img(images):
        image = np.zeros([512, 512, 3])
        for height in range(4):
            for width in range(4):
                image[height * 128:height * 128 + 128, width * 128:width * 128 + 128, :] = images[4 * height + width]
        return image


def main():
    data = SuperResolutionData()
    for batch_x, batch_y in data.build_data(20, 2):
        print(batch_x.shape, batch_y.shape)


if __name__ == '__main__':
    main()

```

深度学习模型

```python
import tensorflow as tf


class SuperResolutionNet(object):
    def __init__(self, input_dim, out_dim, lr, drop_out):
        self.inputs = tf.placeholder(tf.float32, [None, *input_dim], name='inputs')
        self.outputs = tf.placeholder(tf.float32, [None, *out_dim], name='outputs')
        self.lr = lr
        self.drop = drop_out
        self.compressed = self.decoder()
        self.logits, self.decode = self.encoder()
        self.loss = self.create_loss()
        self.opt = self.optimizer()
        pass

    def decoder(self):
        with tf.variable_scope('decoder'):
            print(self.inputs)
            x1 = tf.layers.conv2d(self.inputs, 64, kernel_size=[7, 7], strides=[1, 1], padding='same')
            print(x1)

            x2 = tf.layers.conv2d(x1, 128, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x2)

            x3 = tf.layers.conv2d(x2, 256, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x3)

            x4 = tf.layers.conv2d(x3, 512, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x4)

            x5 = tf.layers.conv2d(x4, 1024, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x5)
            
            return x5
        pass

    def encoder(self):
        with tf.variable_scope('encoder'):

            x4 = tf.layers.conv2d_transpose(self.compressed, 512, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x4)

            x3 = tf.layers.conv2d_transpose(x4, 256, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x3)

            x2 = tf.layers.conv2d_transpose(x3, 128, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x2)

            x1 = tf.layers.conv2d_transpose(x2, 64, kernel_size=[7, 7], strides=[1, 1], padding='same', activation=tf.nn.leaky_relu)
            print(x1)

            logits = tf.layers.conv2d_transpose(x1, 3, kernel_size=[7, 7], strides=[1, 1], padding='same')

            decode = tf.nn.sigmoid(logits, name='decode')

            return logits, decode

    def create_loss(self):
        cost = tf.nn.sigmoid_cross_entropy_with_logits(labels=self.outputs, logits=self.logits)
        loss = tf.reduce_mean(cost)
        return loss

    def optimizer(self):
        return tf.train.AdamOptimizer(0.0002).minimize(self.loss)


def main():
    model = SuperResolutionNet((128, 128, 3), (128, 128, 3), 0.1, 0.7)
    pass


if __name__ == '__main__':
    main()

```

训练模型

```python

```



