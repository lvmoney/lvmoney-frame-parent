import paddle
import matplotlib.pyplot as plt
from PIL import Image
import numpy as np
from paddle.nn import Conv2D
from paddle.nn.initializer import Assign

# 读入图片并转成numpy.ndarray
# 换成灰度图
img = Image.open('./work/images/section1/000000355610.jpg').convert('L')
img = np.array(img)

# 创建初始化参数
w = np.ones([1, 1, 5, 5], dtype='float32') / 25
conv = Conv2D(in_channels=1, out_channels=1, kernel_size=[5, 5],
              weight_attr=paddle.ParamAttr(
                  initializer=Assign(value=w)))
x = img.astype('float32')
x = x.reshape(1, 1, img.shape[0], img.shape[1])
x = paddle.to_tensor(x)
y = conv(x)
out = y.numpy()

plt.figure(figsize=(20, 12))
f = plt.subplot(121)
f.set_title('input image')
plt.imshow(img, cmap='gray')

f = plt.subplot(122)
f.set_title('output feature map')
out = out.squeeze()
plt.imshow(out, cmap='gray')

plt.show()
