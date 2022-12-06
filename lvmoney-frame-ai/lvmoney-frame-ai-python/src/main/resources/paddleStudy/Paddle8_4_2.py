
import paddle
from paddle.jit import to_static
from paddle.static import InputSpec
from paddle.nn import Layer

# 定义线性回归网络，继承自paddle.nn.Layer
# 该网络仅包含一层fc
class SimpleNet(Layer):
    def __init__(self):
        super(SimpleNet, self).__init__()
        self.linear = paddle.nn.Linear(10, 3)

    # 在forward函数中定义该网络的具体前向计算
    def forward(self, x, y):
        out = self.linear(x)
        out = out + y
        return out

net = SimpleNet()

# 训练过程 (伪代码)
# for epoch_id in range(10):
#     train_step(net, train_reader)

# 直接调用to_static函数，paddle会根据input_spec信息对forward函数进行递归的动转静，得到完整的静态图模型
net = to_static(net, input_spec=[InputSpec(shape=[None, 10], name='x'), InputSpec(shape=[1], name='y')])

# 保存预测格式模型
paddle.jit.save(net, 'model/simple_net')