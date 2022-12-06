# 数据处理部分之前的代码，保持不变
import os
import random
import paddle
import numpy as np
import matplotlib.pyplot as plt
from PIL import Image

import gzip
import json
import warnings

warnings.filterwarnings('ignore')

import paddle.nn as nn
from paddle.nn import Conv2D, MaxPool2D, Linear
import paddle.nn.functional as F


# 创建一个类MnistDataset，继承paddle.io.Dataset 这个类
# MnistDataset的作用和上面load_data()函数的作用相同，均是构建一个迭代器
class MnistDataset(paddle.io.Dataset):
    def __init__(self, mode):
        datafile = '/Users/xumanglin/data/python/mnist.json.gz'
        data = json.load(gzip.open(datafile))
        # 读取到的数据区分训练集，验证集，测试集
        train_set, val_set, eval_set = data

        # 数据集相关参数，图片高度IMG_ROWS, 图片宽度IMG_COLS
        self.IMG_ROWS = 28
        self.IMG_COLS = 28

        if mode == 'train':
            # 获得训练数据集
            imgs, labels = train_set[0], train_set[1]
        elif mode == 'valid':
            # 获得验证数据集
            imgs, labels = val_set[0], val_set[1]
        elif mode == 'eval':
            # 获得测试数据集
            imgs, labels = eval_set[0], eval_set[1]
        else:
            raise Exception("mode can only be one of ['train', 'valid', 'eval']")

        # 校验数据
        imgs_length = len(imgs)
        assert len(imgs) == len(labels), \
            "length of train_imgs({}) should be the same as train_labels({})".format(len(imgs), len(labels))

        self.imgs = imgs
        self.labels = labels

    def __getitem__(self, idx):
        # MLP
        # img = np.array(self.imgs[idx]).astype('float32')
        # label = np.array(self.labels[idx]).astype('int64')
        # CNN
        img = np.reshape(self.imgs[idx], [1, self.IMG_ROWS, self.IMG_COLS]).astype('float32')
        label = np.reshape(self.labels[idx], [1]).astype('int64')

        return img, label

    def __len__(self):
        return len(self.imgs)


# 定义模型结构
class MNIST(paddle.nn.Layer):
    def __init__(self):
        super(MNIST, self).__init__()
        nn.initializer.set_global_initializer(nn.initializer.Uniform(), nn.initializer.Constant())
        # 定义卷积层，输出特征通道out_channels设置为20，卷积核的大小kernel_size为5，卷积步长stride=1，padding=2
        self.conv1 = Conv2D(in_channels=1, out_channels=20, kernel_size=5, stride=1, padding=2)
        # 定义池化层，池化层卷积核kernel_size为2，池化步长为2
        self.max_pool1 = MaxPool2D(kernel_size=2, stride=2)
        # 定义卷积层，输出特征通道out_channels设置为20，卷积核的大小kernel_size为5，卷积步长stride=1，padding=2
        self.conv2 = Conv2D(in_channels=20, out_channels=20, kernel_size=5, stride=1, padding=2)
        # 定义池化层，池化层卷积核kernel_size为2，池化步长为2
        self.max_pool2 = MaxPool2D(kernel_size=2, stride=2)
        # 定义一层全连接层，输出维度是10
        self.fc = Linear(in_features=980, out_features=10)

    # 定义网络前向计算过程，卷积后紧接着使用池化层，最后使用全连接层计算最终输出
    def forward(self, inputs):
        x = self.conv1(inputs)
        x = F.relu(x)
        x = self.max_pool1(x)
        x = self.conv2(x)
        x = F.relu(x)
        x = self.max_pool2(x)
        x = paddle.reshape(x, [x.shape[0], 980])
        x = self.fc(x)
        #  x = F.softmax(x)
        return x


class Trainer(object):
    def __init__(self, model_path, model, optimizer):
        self.model_path = model_path  # 模型存放路径
        self.model = model  # 定义的模型
        self.optimizer = optimizer  # 优化器

    def save(self):
        # 保存模型
        paddle.save(self.model.state_dict(), self.model_path)

    def train_step(self, data):
        images, labels = data
        # 前向计算的过程
        predicts = self.model(images)
        # 计算损失
        loss = F.cross_entropy(predicts, labels)
        avg_loss = paddle.mean(loss)
        # 后向传播，更新参数的过程
        avg_loss.backward()
        self.optimizer.step()
        self.optimizer.clear_grad()
        return avg_loss

    def train_epoch(self, datasets, epoch):
        self.model.train()
        for batch_id, data in enumerate(datasets()):
            loss = self.train_step(data)
            # 每训练了1000批次的数据，打印下当前Loss的情况
            if batch_id % 500 == 0:
                print("epoch_id: {}, batch_id: {}, loss is: {}".format(epoch, batch_id, loss.numpy()))

    def train(self, train_datasets, start_epoch, end_epoch, save_path):
        if not os.path.exists(save_path):
            os.makedirs(save_path)
        for i in range(start_epoch, end_epoch):
            self.train_epoch(train_datasets, i)
            paddle.save(opt.state_dict(), './{}/mnist_epoch{}'.format(save_path, i) + '.pdopt')
            paddle.save(model.state_dict(), './{}/mnist_epoch{}'.format(save_path, i) + '.pdparams')
        self.save()


use_gpu = False
paddle.set_device('gpu:0') if use_gpu else paddle.set_device('cpu')

import warnings

warnings.filterwarnings('ignore')
paddle.seed(1024)

epochs = 3
BATCH_SIZE = 32
model_path = 'model/mnist.pdparams'

train_dataset = MnistDataset(mode='train')
# 这里为了使每次的训练精度都保持一致，因此先选择了shuffle=False，真正训练时应改为shuffle=True
train_loader = paddle.io.DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=False, num_workers=0)

model = MNIST()
# lr = 0.01
total_steps = (int(50000 // BATCH_SIZE) + 1) * epochs
lr = paddle.optimizer.lr.PolynomialDecay(learning_rate=0.01, decay_steps=total_steps, end_lr=0.001)
opt = paddle.optimizer.Momentum(learning_rate=lr, parameters=model.parameters())

trainer = Trainer(
    model_path=model_path,
    model=model,
    optimizer=opt
)

trainer.train(train_datasets=train_loader, start_epoch=0, end_epoch=epochs, save_path='checkpoint')
