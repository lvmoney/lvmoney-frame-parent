import numpy as np
import paddle

# 利用np.ones函数构造出[2*2]的二维数组，值为1
data = np.ones([2, 2], np.float32)

# 默认动态图模式下，将numpy的ndarray类型的数据转换为Tensor类型
x = paddle.to_tensor(data)
print('In DyGraph mode, after calling paddle.to_tensor, x = ', x)
# 动态图模式下，对Tensor类型的数据执行x=x+10操作
x += 10
# 动态图模式下，调用Tensor的numpy函数将Tensor类型的数据转换为numpy的ndarray类型的数据
print('In DyGraph mode, data after run:', x.numpy())

# 启动静态图模式
paddle.enable_static()
# 静态图定义组网网络结构的程序描述Program
main_program = paddle.static.Program()
startup_program = paddle.static.Program()
with paddle.static.program_guard(main_program=main_program, startup_program=startup_program):
    # 静态图模式下，使用paddle.static.data构建占位符用于数据输入
    x = paddle.static.data(name='x', shape=[2, 2], dtype='float32')
    print('In static mode, after calling paddle.static.data, x = ', x)
    # 静态图模式下，对占位符Variable类型的数据执行x=x+10操作
    x += 10
    # 在静态图模式下，需要用户显示指定运行设备
    # 此处调用paddle.CPUPlace() API来指定在CPU设备上运行程序
    place = paddle.CPUPlace()
    # 创建“执行器”来运行组网的Program，并用place参数指明需要在何种设备上运行
    exe = paddle.static.Executor(place=place)
    # 初始化操作，在这个简单例子中没有初始化操作，但复杂网络中会有诸如参数随机初始化的操作
    # exe.run(startup_program)
    # 使用执行器“执行”已经记录的所有操作，在本例中即执行paddle.static.data、x += 10操作
    # 在调用执行器的run接口时，可以通过fetch_list参数来指定要获取哪些变量的计算结果，这里我们要获取‘x += 10’执行完成后‘x’的结果；
    # 同时也可以通过feed参数来传入数据，这里我们将data数据传递给‘paddle.static.data’指定的‘x’。
    data_after_run = exe.run(fetch_list=[x], feed={'x': data})
    # 此时我们打印执行器返回的结果，可以看到“执行”后，Tensor中的数据已经被赋值并进行了运算，每个元素的值都是11
    print('In static mode, data after run:', data_after_run)
# 关闭静态图模式，此时飞桨回归动态图模式
paddle.disable_static()