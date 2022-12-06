import numpy as np
import paddle
from paddle.optimizer import Adam
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

# 与上面定义的 MNIST 类似，注意不同处是在 forward 函数添加
# @paddle.jit.to_static 装饰器
class MNIST(paddle.nn.Layer):
    def __init__(self):
        super(MNIST, self).__init__()
        self._simple_img_conv_pool_1 = SimpleImgConvPool(
            1, 20, 5, 2, 2)
        self._simple_img_conv_pool_2 = SimpleImgConvPool(
            20, 50, 5, 2, 2)

        self.pool_2_shape = 50 * 4 * 4
        self.size = 10
        self.output_weight = self.create_parameter(
            [self.pool_2_shape, self.size])

    @paddle.jit.to_static
    def forward(self, inputs):
        x = self._simple_img_conv_pool_1(inputs)
        x = self._simple_img_conv_pool_2(x)
        x = paddle.reshape(x, shape=[-1, self.pool_2_shape])
        x = paddle.matmul(x, self.output_weight)
        x = paddle.nn.functional.softmax(x)
        return x

# 训练部分代码与上文一致
epoch_num = 5
BATCH_SIZE = 64
mnist = MNIST()
adam = Adam(learning_rate=0.001, parameters=mnist.parameters())
for epoch in range(epoch_num):
    for batch_id, data in enumerate(train_reader()):
        img = data[0]
        label = data[1]
        pred = mnist(img)

        loss = paddle.nn.functional.cross_entropy(pred, label)
        avg_loss = paddle.mean(loss)
        avg_loss.backward()
        adam.step()
        adam.clear_grad()

        if batch_id % 100 == 0:
            print("Epoch {} step {}, Loss = {:}".format(
                epoch, batch_id, avg_loss.numpy()))

# 此处的 path 参数为前缀，而非文件名
paddle.jit.save(mnist, "inference/mnist")

load_mnist = paddle.jit.load("inference/mnist")

load_mnist.eval()
x = paddle.randn([1, 1, 28, 28], 'float32')
pred = load_mnist(x)

print("Load MNIST predict: {:} ".format(pred.numpy()))