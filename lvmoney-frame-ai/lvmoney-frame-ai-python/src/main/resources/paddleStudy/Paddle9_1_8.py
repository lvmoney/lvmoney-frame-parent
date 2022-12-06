import paddle
import numpy as np

value0 = np.arange(26).reshape(2, 13).astype("float32")
value1 = np.arange(6).reshape(2, 3).astype("float32")
value2 = np.arange(10).reshape(2, 5).astype("float32")

# 将ndarray类型的数据转换为Tensor类型
a = paddle.to_tensor(value0)
b = paddle.to_tensor(value1)
c = paddle.to_tensor(value2)

# 构造fc、fc2层
fc = paddle.nn.Linear(13, 5)
fc2 = paddle.nn.Linear(3, 3)

# 对fc、fc2层执行前向计算
out1 = fc(a)
out2 = fc2(b)

# 相当于不会对out1这部分子图做反向计算
out1.stop_gradient = True

out = paddle.concat(x=[out1, out2, c], axis=1)
out.backward()

# 可以发现这里out1.gradient()的为None，同时使得fc.weight的grad为None
assert fc.weight.gradient() is None
assert out1.gradient() is None