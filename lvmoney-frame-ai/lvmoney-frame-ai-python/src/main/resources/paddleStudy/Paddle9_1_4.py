import numpy as np
import paddle

# 定义批大小
BATCH_SIZE = 64


# 由于paddle.io.DataLoader只接受numpy ndarray或者paddle Tensor作为数据输入
# 该自定义类将MNIST数据reshape并转化为numpy ndarray类型，并且将数据从[0, 255] 转化到 [-1, 1]
class MnistDataset(paddle.vision.datasets.MNIST):
    def __init__(self, mode, return_label=True):
        super(MnistDataset, self).__init__(mode=mode)
        self.return_label = return_label

    def __getitem__(self, idx):
        img = np.reshape(self.images[idx], [1, 28, 28])
        img = img / 255.0 * 2.0 - 1.0
        if self.return_label:
            return img, np.array(self.labels[idx]).astype('int64')
        return img,

    def __len__(self):
        return len(self.images)


# 通过调用paddle.io.DataLoader来构造reader
train_reader = paddle.io.DataLoader(
    MnistDataset(mode='train'), batch_size=BATCH_SIZE, drop_last=True)
test_reader = paddle.io.DataLoader(
    MnistDataset(mode='test'), batch_size=BATCH_SIZE, drop_last=True)

import paddle
from paddle.nn import Conv2D, MaxPool2D, ReLU


# 定义SimpleImgConvPool网络，必须继承自paddle.nn.Layer
# 该网络由一个卷积层和一个池化层组成
class SimpleImgConvPool(paddle.nn.Layer):
    # 在__init__构造函数中会执行变量的初始化、参数初始化、子网络初始化的操作
    # 本例中执行了Conv2D和MaxPool2D网络的初始化操作
    def __init__(self,
                 in_channels,
                 out_channels,
                 filter_size,
                 pool_size,
                 pool_stride,
                 pool_padding=0,
                 conv_stride=1,
                 conv_padding=0,
                 conv_dilation=1,
                 conv_groups=1,
                 weight_attr=None,
                 bias_attr=None):
        super(SimpleImgConvPool, self).__init__()

        # Conv2D网络的初始化
        self._conv2d = Conv2D(
            in_channels=in_channels,
            out_channels=out_channels,
            kernel_size=filter_size,
            stride=conv_stride,
            padding=conv_padding,
            dilation=conv_dilation,
            groups=conv_groups,
            weight_attr=weight_attr,
            bias_attr=bias_attr)

        # ReLU激活的初始化
        self._relu = ReLU()

        # Pool2D网络的初始化
        self._pool2d = MaxPool2D(
            kernel_size=pool_size,
            stride=pool_stride,
            padding=pool_padding)

    # forward函数实现了SimpleImgConvPool网络的执行逻辑
    def forward(self, inputs):
        x = self._conv2d(inputs)
        x = self._relu(x)
        x = self._pool2d(x)
        return x


# 定义MNIST网络，必须继承自paddle.nn.Layer
# 该网络由两个SimpleImgConvPool子网络、reshape层、matmul层、softmax层、accuracy层组成
class MNIST(paddle.nn.Layer):
    # 在__init__构造函数中会执行变量的初始化、参数初始化、子网络初始化的操作
    # 本例中执行了self.pool_2_shape变量、matmul层中参数self.output_weight、SimpleImgConvPool子网络的初始化操作
    # 并且定义了衡量输出准确率的accuracy的paddle.metric.Accuracy
    def __init__(self):
        super(MNIST, self).__init__()
        self._simple_img_conv_pool_1 = SimpleImgConvPool(
            1, 20, 5, 2, 2)
        self._simple_img_conv_pool_2 = SimpleImgConvPool(
            20, 50, 5, 2, 2)

        # self.pool_2_shape变量定义了经过self._simple_img_conv_pool_2层之后的数据
        # 除了batch_size维度之外其他维度的乘积
        self.pool_2_shape = 50 * 4 * 4
        # self.pool_2_shape、self.size定义了self.output_weight参数的维度
        self.size = 10
        # 定义全连接层的参数
        self.output_weight = self.create_parameter(
            [self.pool_2_shape, self.size])

        # 定义计算accuracy的层
        self.accuracy = paddle.metric.Accuracy()

    # forward函数实现了MNIST网络的执行逻辑
    def forward(self, inputs, label=None):
        x = self._simple_img_conv_pool_1(inputs)
        x = self._simple_img_conv_pool_2(x)
        x = paddle.reshape(x, shape=[-1, self.pool_2_shape])
        x = paddle.matmul(x, self.output_weight)
        x = paddle.nn.functional.softmax(x)
        if label is not None:
            # Reset只返回当前batch的准确率
            self.accuracy.reset()
            correct = self.accuracy.compute(x, label)
            self.accuracy.update(correct)
            acc = self.accuracy.accumulate()
            return x, acc
        else:
            return x


import numpy as np
import paddle
import paddle.distributed as dist
from paddle.optimizer import Adam

# 准备多卡环境
dist.init_parallel_env()

epoch_num = 5
BATCH_SIZE = 64
mnist = MNIST()
adam = Adam(learning_rate=0.001, parameters=mnist.parameters())
# 数据并行模块
mnist = paddle.DataParallel(mnist)

# 通过调用paddle.io.DataLoader来构造reader，这里需要使用DistributedBatchSampler为多张卡拆分数据
train_sampler = paddle.io.DistributedBatchSampler(MnistDataset(mode='train'), batch_size=BATCH_SIZE, drop_last=True)
train_reader = paddle.io.DataLoader(
    MnistDataset(mode='train'), batch_sampler=train_sampler)

for epoch in range(epoch_num):
    for batch_id, data in enumerate(train_reader()):
        img = data[0]
        label = data[1]
        label.stop_gradient = True

        # 网络正向执行
        pred, acc = mnist(img, label)

        # 计算损失值
        loss = paddle.nn.functional.cross_entropy(pred, label)
        avg_loss = paddle.mean(loss)

        avg_loss.backward()

        # 参数更新
        adam.step()
        # 将本次计算的梯度值清零，以便进行下一次迭代和梯度更新
        adam.clear_grad()

        # 输出对应epoch、batch_id下的损失值
        if batch_id % 100 == 0 and batch_id is not 0:
            print("Epoch {} step {}, Loss = {:}, Accuracy = {:}".format(
                epoch, batch_id, avg_loss.numpy(), acc))