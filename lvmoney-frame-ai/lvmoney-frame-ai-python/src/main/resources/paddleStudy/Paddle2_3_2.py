import numpy as np
from paddle.io import Dataset
import paddle


# 构建一个类，继承paddle.io.Dataset，创建数据读取器
class RandomDataset(Dataset):
    def __init__(self, num_samples):
        # 样本数量
        self.num_samples = num_samples

    def __getitem__(self, idx):
        # 随机产生数据和label
        image = np.random.random([784]).astype('float32')
        label = np.random.randint(0, 9, (1,)).astype('float32')
        return image, label

    def __len__(self):
        # 返回样本总数量
        return self.num_samples


# 测试数据读取器
dataset = RandomDataset(10)
for i in range(len(dataset)):
    print(dataset[i])

loader = paddle.io.DataLoader(dataset, batch_size=3, shuffle=True, drop_last=True, num_workers=2)
for i, data in enumerate(loader()):
    images, labels = data[0], data[1]
    print("batch_id: {}, 训练数据shape: {}, 标签数据shape: {}".format(i, images.shape, labels.shape))
