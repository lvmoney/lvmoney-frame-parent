# 引用 paddle inference 预测库
import paddle.inference as paddle_infer
# 引用 numpy argparse库
import numpy as np
import argparse

# 创建配置对象，并根据需求配置
config = paddle_infer.Config("D:/data/paddle/simple_net.pdmodel", "D:/data/paddle/simple_net.pdiparams")

# 配置config，不启用gpu
config.disable_gpu()

# 根据Config创建预测对象
predictor = paddle_infer.create_predictor(config)

# 获取输入的名称
input_names = 	predictor.get_input_names()
# 获取输入handle
x_handle = predictor.get_input_handle(input_names[0])
y_handle = predictor.get_input_handle(input_names[1])

# 设置输入，此处以随机输入为例，用户可自行输入真实数据
fake_x = np.random.randn(1, 10).astype('float32')
fake_y = np.random.randn(1).astype('float32')
x_handle.reshape([1, 10])
x_handle.copy_from_cpu(fake_x)
y_handle.reshape([1])
y_handle.copy_from_cpu(fake_y)

# 运行预测引擎
predictor.run()

# 获得输出名称
output_names = predictor.get_output_names()
# 获得输出handle
output_handle = predictor.get_output_handle(output_names[0])
output_data = output_handle.copy_to_cpu() # return numpy.ndarray

# 打印输出结果
print(output_data)