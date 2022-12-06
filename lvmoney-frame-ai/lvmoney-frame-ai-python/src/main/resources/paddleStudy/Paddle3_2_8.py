# 从paddle.vision.models 模块中import 残差网络，VGG网络，LeNet网络
import paddle
from paddle.vision.models import resnet50, vgg16, LeNet
from paddle.vision.datasets import Cifar10
from paddle.optimizer import Momentum
from paddle.regularizer import L2Decay
from paddle.nn import CrossEntropyLoss
from paddle.metric import Accuracy
from paddle.vision.transforms import Transpose

# 确保从paddle.vision.datasets.Cifar10中加载的图像数据是np.ndarray类型
paddle.vision.set_image_backend('cv2')
# 调用resnet50模型
model = paddle.Model(resnet50(pretrained=False, num_classes=10))

# 使用Cifar10数据集
train_dataset = Cifar10(mode='train', transform=Transpose())
val_dataset = Cifar10(mode='test', transform=Transpose())
# 定义优化器
optimizer = Momentum(learning_rate=0.01,
                     momentum=0.9,
                     weight_decay=L2Decay(1e-4),
                     parameters=model.parameters())
# 进行训练前准备
model.prepare(optimizer, CrossEntropyLoss(), Accuracy(topk=(1, 5)))
# 启动训练
model.fit(train_dataset,
          val_dataset,
          epochs=50,
          batch_size=64,
          save_dir="output",
          num_workers=8)
