import numpy as np
import paddle
from paddle.nn import Conv2D, MaxPool2D, ReLU
from paddle.optimizer import Adam

# 开启静态图模式
paddle.enable_static()

# 设置全部样本训练次数（epoch_num）、批大小（BATCH_SIZE）。
epoch_num = 1
BATCH_SIZE = 64

# SimpleImgConvPool class 可以与动态图代码相同
class SimpleImgConvPool(paddle.nn.Layer):
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

        self._relu = ReLU()

        self._pool2d = MaxPool2D(
            kernel_size=pool_size,
            stride=pool_stride,
            padding=pool_padding)

    def forward(self, inputs):
        x = self._conv2d(inputs)
        x = self._relu(x)
        x = self._pool2d(x)
        return x

# MNIST class 可以与动态图代码相同
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

    def forward(self, inputs):
        x = self._simple_img_conv_pool_1(inputs)
        x = self._simple_img_conv_pool_2(x)
        x = paddle.reshape(x, shape=[-1, self.pool_2_shape])
        x = paddle.matmul(x, self.output_weight)
        x = paddle.nn.functional.softmax(x)
        return x

# 由于paddle.io.DataLoader只接受numpy ndarray或者paddle Tensor作为数据输入
# 该自定义类将MNIST数据reshape并转化为numpy ndarray类型
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

paddle.set_device("cpu")

# 通过调用paddle.vision.dataset.mnist的train模式和test模式来构造reader
train_reader = paddle.io.DataLoader(
    MnistDataset(mode='train'), batch_size=BATCH_SIZE, drop_last=True)

main_program = paddle.static.Program()
startup_program = paddle.static.Program()

with paddle.static.program_guard(main_program=main_program, startup_program=startup_program):
    # 静态图中需要使用执行器执行之前已经定义好的网络
    exe = paddle.static.Executor()

    # 定义MNIST类的对象，可以使用动态图定义好的网络结构，这得益于Paddle动态图静态图组网接口一致
    mnist_static = MNIST()
    # 定义优化器对象，静态图模式下不需要传入parameter_list参数
    sgd_static = paddle.optimizer.SGD(learning_rate=1e-3)

    # 静态图需要明确定义输入变量，即“占位符”，在静态图组网阶段并没有读入数据，所以需要使用占位符指明输入数据的类型，shape等信息
    img_static = paddle.static.data(
        name='pixel', shape=[None, 1, 28, 28], dtype='float32')
    label_static = paddle.static.data(name='label', shape=[None, 1], dtype='int64')

    # 调用网络，执行前向计算
    cost_static = mnist_static(img_static)
    # 计算损失值
    loss_static = paddle.nn.functional.cross_entropy(cost_static, label_static)
    avg_loss_static = paddle.mean(loss_static)

    # 调用优化器的minimize接口计算和更新梯度
    sgd_static.minimize(avg_loss_static)

    # 静态图中需要显示对网络进行初始化操作
    exe.run(startup_program)

    for epoch in range(epoch_num):
        for batch_id, data in enumerate(train_reader()):
            x_data_static = data[0]
            y_data_static = data[1]

            fetch_list = [avg_loss_static.name]
            # 静态图中需要调用执行器的run方法执行计算过程，需要获取的计算结果（如avg_loss）需要通过fetch_list指定
            out = exe.run(
                main_program,
                feed={"pixel": x_data_static,
                      "label": y_data_static},
                fetch_list=fetch_list)

            static_out = out[0]

            if batch_id % 100 == 0 and batch_id is not 0:
                print("epoch: {}, batch_id: {}, loss: {}".format(epoch, batch_id, static_out))

# 关闭静态图模式
paddle.disable_static()