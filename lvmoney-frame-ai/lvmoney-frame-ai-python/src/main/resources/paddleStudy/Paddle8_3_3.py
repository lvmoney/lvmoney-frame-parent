import paddle
import paddle.nn as nn
import paddle.vision.transforms as T
import os
import wget
from paddle.vision.models import resnet
from paddle.vision.datasets import Flowers

import paddle
from paddle.autograd import PyLayer

# 通过创建`PyLayer`子类的方式实现动态图Python Op
class cus_tanh(PyLayer):
    @staticmethod
    def forward(ctx, x):
        y = paddle.tanh(x)
        # ctx 为PyLayerContext对象，可以把y从forward传递到backward。
        ctx.save_for_backward(y)
        return y

    @staticmethod
    # 因为forward只有一个输出，因此除了ctx外，backward只有一个输入。
    def backward(ctx, dy):
        # ctx 为PyLayerContext对象，saved_tensor获取在forward时暂存的y。
        y, = ctx.saved_tensor()
        # 调用Paddle API自定义反向计算
        grad = dy * (1 - paddle.square(y))
        # forward只有一个Tensor输入，因此，backward只有一个输出。
        return grad

data = paddle.randn([2, 3], dtype="float32")
data.stop_gradient = False
# 通过 apply运行这个Python算子
z = cus_tanh.apply(data)
z.mean().backward()

print(data.grad)