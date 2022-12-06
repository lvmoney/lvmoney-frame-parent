# 导入Paddle相关包
import paddle
from paddle.jit import to_static
from paddle.static import InputSpec
from paddle.nn import Layer


# 定义线性回归网络，继承自paddle.nn.Layer
# 该网络仅包含一层fc
class SimpleNet(Layer):
    # 在__init__函数中仅初始化linear层
    def __init__(self):
        super(SimpleNet, self).__init__()
        self.linear = paddle.nn.Linear(10, 3)

    # 在forward函数中定义该网络的具体前向计算；@to_static装饰器用于依次指定参数x和y对应Tensor签名信息
    # 下述案例是输入为10个特征，输出为1维的数字
    @to_static(input_spec=[InputSpec(shape=[None, 10], name='x'), InputSpec(shape=[1], name='y')])
    def forward(self, x, y):
        out = self.linear(x)
        out = out + y
        return out


net = SimpleNet()

# 保存预测格式模型
paddle.jit.save(net, 'model/simple_net')
