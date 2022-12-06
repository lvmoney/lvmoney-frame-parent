# ReLU和Sigmoid激活函数示意图
import numpy as np
import matplotlib.pyplot as plt
import matplotlib.patches as patches

plt.figure(figsize=(10, 5))

# 创建数据x
x = np.arange(-10, 10, 0.1)

# 计算Sigmoid函数
s = 1.0 / (1 + np.exp(0. - x))

# 计算ReLU函数
y = np.clip(x, a_min=0., a_max=None)

#####################################
# 以下部分为画图代码
f = plt.subplot(121)
plt.plot(x, s, color='r')
currentAxis = plt.gca()
plt.text(-9.0, 0.9, r'$y=Sigmoid(x)$', fontsize=13)
currentAxis.xaxis.set_label_text('x', fontsize=15)
currentAxis.yaxis.set_label_text('y', fontsize=15)

f = plt.subplot(122)
plt.plot(x, y, color='g')
plt.text(-3.0, 9, r'$y=ReLU(x)$', fontsize=13)
currentAxis = plt.gca()
currentAxis.xaxis.set_label_text('x', fontsize=15)
currentAxis.yaxis.set_label_text('y', fontsize=15)

plt.show()
