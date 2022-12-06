import paddle


# 定义手写数字识别模型
class MNIST(paddle.nn.Layer):
    def __init__(self):
        super(MNIST, self).__init__()

        # 定义一层全连接层，输出维度是1
        self.fc = paddle.nn.Linear(in_features=784, out_features=10)

    # 定义网络结构的前向计算过程
    @paddle.jit.to_static  # 添加装饰器，使动态图网络结构在静态图模式下运行
    def forward(self, inputs):
        outputs = self.fc(inputs)
        return outputs


import paddle
import paddle.nn.functional as F

# 确保从paddle.vision.datasets.MNIST中加载的图像数据是np.ndarray类型
paddle.vision.set_image_backend('cv2')


# 图像归一化函数，将数据范围为[0, 255]的图像归一化到[-1, 1]
def norm_img(img):
    batch_size = img.shape[0]
    # 归一化图像数据
    img = img / 127.5 - 1
    # 将图像形式reshape为[batch_size, 784]
    img = paddle.reshape(img, [batch_size, 784])

    return img


def train(model):
    model.train()
    # 加载训练集 batch_size 设为 16
    train_loader = paddle.io.DataLoader(paddle.vision.datasets.MNIST(mode='train'),
                                        batch_size=16,
                                        shuffle=True)
    opt = paddle.optimizer.SGD(learning_rate=0.001, parameters=model.parameters())
    EPOCH_NUM = 10
    for epoch in range(EPOCH_NUM):
        for batch_id, data in enumerate(train_loader()):
            images = norm_img(data[0]).astype('float32')
            labels = data[1].astype('int64')

            # 前向计算的过程
            predicts = model(images)

            # 计算损失
            loss = F.cross_entropy(predicts, labels)
            avg_loss = paddle.mean(loss)

            # 每训练了1000批次的数据，打印下当前Loss的情况
            if batch_id % 1000 == 0:
                print("epoch_id: {}, batch_id: {}, loss is: {}".format(epoch, batch_id, avg_loss.numpy()))

            # 后向传播，更新参数的过程
            avg_loss.backward()
            opt.step()
            opt.clear_grad()


model = MNIST()

train(model)

paddle.save(model.state_dict(), 'model/mnist.pdparams')
print("==>Trained model saved in model/mnist.pdparams")

# save inference model
from paddle.static import InputSpec

# 加载训练好的模型参数
state_dict = paddle.load("model/mnist.pdparams")
# 将训练好的参数读取到网络中
model.set_state_dict(state_dict)
# 设置模型为评估模式
model.eval()

# 保存inference模型
paddle.jit.save(
    layer=model,
    path="inference/mnist",
    input_spec=[InputSpec(shape=[None, 784], dtype='float32')])

print("==>Inference model saved in inference/mnist.")

import numpy as np
import paddle
import paddle.nn.functional as F

# 确保从paddle.vision.datasets.MNIST中加载的图像数据是np.ndarray类型
paddle.vision.set_image_backend('cv2')

# 读取mnist测试数据，获取第一个数据
mnist_test = paddle.vision.datasets.MNIST(mode='test')
test_image, label = mnist_test[0]
# 获取读取到的图像的数字标签
print("The label of readed image is : ", label)

# 将测试图像数据转换为tensor，并reshape为[1, 784]
test_image = paddle.reshape(paddle.to_tensor(test_image), [1, 784])
# 然后执行图像归一化
test_image = norm_img(test_image)
# 加载保存的模型
loaded_model = paddle.jit.load("inference/mnist")
# 利用加载的模型执行预测
preds = loaded_model(test_image)
pred_label = paddle.argmax(preds)
# 打印预测结果
print("The predicted label is : ", pred_label.numpy())
