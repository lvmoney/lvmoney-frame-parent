import paddle

paddle.enable_static()
# 定义输入变量，即“占位符”，输入变量的第一维是batch大小，在运行期读入数据时才会确定，因此在这里以None表示
a = paddle.static.data(name="a", shape=[None, 1], dtype='int64')
b = paddle.static.data(name="b", shape=[None, 1], dtype='int64')

# 组建网络（此处网络仅由一个操作构成，即paddle.add）
# 飞桨对四则运算符进行了重载，以下操作也可以简写为“result = a + b”,此处仅为展示飞桨对OP的调用，所以显示使用了paddle.add
result = paddle.add(a, b)

# 准备运行网络
place = paddle.CPUPlace()  # 定义运算设备，这里选择在CPU下训练，如果不显式设置运行设备，飞桨会设置默认运行设备
exe = paddle.static.Executor(place=place)  # 创建执行器
exe.run(paddle.static.default_startup_program())  # 网络参数初始化

# 读取输入数据
import numpy
data_1 = int(input("Please enter an integer: a="))
data_2 = int(input("Please enter an integer: b="))
print('---------------------------------')
x = numpy.array([[data_1]]).astype('int64')
y = numpy.array([[data_2]]).astype('int64')

# 运行网络
outs = exe.run(
    feed={'a': x, 'b': y},  # 将输入数据x, y分别赋值给变量a，b
    fetch_list=[result]  # 通过fetch_list参数指定需要获取的变量结果
)

# 输出计算结果
print("%d+%d=%d" % (data_1, data_2, outs[0][0]))