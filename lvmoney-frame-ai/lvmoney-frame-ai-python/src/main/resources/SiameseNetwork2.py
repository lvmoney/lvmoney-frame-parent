import os

import torch
import torch.nn as nn
from torch import optim
import torch.nn.functional as F
import torchvision.transforms as transforms
from torch.utils.data import DataLoader, Dataset
import matplotlib.pyplot as plt
import torchvision.utils
import numpy as np
import random
from PIL import Image
import PIL.ImageOps
from torch.autograd import Variable

import Const
from YamlUtil import getModelPath

# 定义一些超参
train_batch_size = 32  # 训练时batch_size
train_number_epochs = 20  # 训练的epoch


def imshow(img, text=None, should_save=False):
    # 展示一幅tensor图像，输入是(C,H,W)
    npimg = img.numpy()  # 将tensor转为ndarray
    plt.axis("off")
    if text:
        plt.text(75, 8, text, style='italic', fontweight='bold',
                 bbox={'facecolor': 'white', 'alpha': 0.8, 'pad': 10})
    plt.imshow(np.transpose(npimg, (1, 2, 0)))  # 转换为(H,W,C)
    plt.show()


def show_plot(iteration, loss):
    # 绘制损失变化图
    plt.plot(iteration, loss)
    plt.show()


class SiameseNetworkDataset(Dataset):
    '''
    自定义Dataset类，__getitem__(self,index)每次返回(img1, img2, 0/1)
    '''

    def __init__(self, imageFolderDataset, transform=None, should_invert=True):
        self.imageFolderDataset = imageFolderDataset
        self.transform = transform
        self.should_invert = should_invert

    def __getitem__(self, index):
        img0_tuple = random.choice(self.imageFolderDataset.imgs)  # 37个类别中任选一个
        should_get_same_class = random.randint(0, 1)  # 保证同类样本约占一半
        if should_get_same_class:
            while True:
                # 直到找到同一类别
                img1_tuple = random.choice(self.imageFolderDataset.imgs)
                if img0_tuple[1] == img1_tuple[1]:
                    break
        else:
            while True:
                # 直到找到非同一类别
                img1_tuple = random.choice(self.imageFolderDataset.imgs)
                if img0_tuple[1] != img1_tuple[1]:
                    break

        img0 = Image.open(img0_tuple[0])
        img1 = Image.open(img1_tuple[0])
        img0 = img0.convert(Const.SIAMESE_NETWORK_CONVERT)
        img1 = img1.convert(Const.SIAMESE_NETWORK_CONVERT)

        if self.should_invert:
            img0 = PIL.ImageOps.invert(img0)
            img1 = PIL.ImageOps.invert(img1)

        if self.transform is not None:
            img0 = self.transform(img0)
            img1 = self.transform(img1)

        return img0, img1, torch.from_numpy(np.array([int(img1_tuple[1] != img0_tuple[1])], dtype=np.float32))

    def __len__(self):
        return len(self.imageFolderDataset.imgs)


class SiameseNetwork(nn.Module):
    '''
    搭建模型
    '''

    def __init__(self):
        super().__init__()
        self.cnn1 = nn.Sequential(
            nn.ReflectionPad2d(1),
            nn.Conv2d(1, 4, kernel_size=3),  # pgm是灰度图的格式，所以第一层卷积输入层是1
            nn.ReLU(inplace=True),
            nn.BatchNorm2d(4),

            nn.ReflectionPad2d(1),
            nn.Conv2d(4, 8, kernel_size=3),
            nn.ReLU(inplace=True),
            nn.BatchNorm2d(8),

            nn.ReflectionPad2d(1),
            nn.Conv2d(8, 8, kernel_size=3),
            nn.ReLU(inplace=True),
            nn.BatchNorm2d(8),
        )

        self.fc1 = nn.Sequential(
            nn.Linear(8 * 100 * 100, 500),
            nn.ReLU(inplace=True),

            nn.Linear(500, 500),
            nn.ReLU(inplace=True),

            nn.Linear(500, 5))

    def forward_once(self, x):
        output = self.cnn1(x)
        output = output.view(output.size()[0], -1)
        output = self.fc1(output)
        return output

    def forward(self, input1, input2):
        output1 = self.forward_once(input1)
        output2 = self.forward_once(input2)
        return output1, output2


class ContrastiveLoss(torch.nn.Module):
    """
    自定义ContrastiveLoss
    Contrastive loss function.
    Based on: http://yann.lecun.com/exdb/publis/pdf/hadsell-chopra-lecun-06.pdf
    """

    def __init__(self, margin=2.0):
        super(ContrastiveLoss, self).__init__()
        self.margin = margin

    def forward(self, output1, output2, label):
        euclidean_distance = F.pairwise_distance(output1, output2, keepdim=True)
        loss_contrastive = torch.mean((1 - label) * torch.pow(euclidean_distance, 2) +
                                      (label) * torch.pow(torch.clamp(self.margin - euclidean_distance, min=0.0), 2))

        return loss_contrastive


def train(training_dir, serialNo):
    # 定义文件dataset
    folder_dataset = torchvision.datasets.ImageFolder(root=training_dir)

    # 定义图像dataset
    transform = transforms.Compose([transforms.Resize(Const.SIAMESE_NETWORK_TRANS_RESIZE),  # 有坑，传入int和tuple有区别
                                    transforms.ToTensor()])
    siamese_dataset = SiameseNetworkDataset(imageFolderDataset=folder_dataset,
                                            transform=transform,
                                            should_invert=False)

    # 定义图像dataloader
    train_dataloader = DataLoader(siamese_dataset,
                                  shuffle=True,
                                  batch_size=train_batch_size)

    model = SiameseNetwork().cpu()  # 定义模型且移至GPU
    criterion = ContrastiveLoss()  # 定义损失函数
    optimizer = optim.Adam(model.parameters(), lr=0.0005)  # 定义优化器

    counter = []
    loss_history = []
    iteration_number = 0

    # 开始训练
    for epoch in range(0, train_number_epochs):
        for i, data in enumerate(train_dataloader, 0):
            img0, img1, label = data
            # img0维度为torch.Size([32, 1, 100, 100])，32是batch，label为torch.Size([32, 1])
            img0, img1, label = img0.cpu(), img1.cpu(), label.cpu()  # 数据移至GPU
            optimizer.zero_grad()
            output1, output2 = model(img0, img1)
            loss_contrastive = criterion(output1, output2, label)
            loss_contrastive.backward()
            optimizer.step()
            if i % 10 == 0:
                iteration_number += 10
                counter.append(iteration_number)
                loss_history.append(loss_contrastive.item())
        print("Epoch number: {} , Current loss: {:.4f}\n".format(epoch, loss_contrastive.item()))
    modelPath = getModelPath()
    modelPath = os.path.join(modelPath,
                             Const.SMART_CLASSIFY_SIAMESE_NETWORK)
    modelSavePath = modelPath + Const.CONNECTOR_UNDERLINE + serialNo + Const.SMART_SIAMESE_NETWORK_MODEL_SUFFIX
    torch.save(model, modelSavePath)
    show_plot(counter, loss_history)


def test():
    modelPath = getModelPath()
    modelPath = os.path.join(modelPath,
                             Const.SMART_CLASSIFY_SIAMESE_NETWORK)
    modelSavePath = modelPath + Const.CONNECTOR_UNDERLINE + 'model' + Const.SMART_SIAMESE_NETWORK_MODEL_SUFFIX
    model = torch.load(modelSavePath).cpu()
    # model.eval()

    # 定义文件dataset
    testing_dir = "D:/data/images/data/faces/testing/"  # 测试集地址
    folder_dataset_test = torchvision.datasets.ImageFolder(root=testing_dir)

    # 定义图像dataset
    transform_test = transforms.Compose([transforms.Resize(Const.SIAMESE_NETWORK_TRANS_RESIZE),
                                         transforms.ToTensor()])
    siamese_dataset_test = SiameseNetworkDataset(imageFolderDataset=folder_dataset_test,
                                                 transform=transform_test,
                                                 should_invert=False)

    # 定义图像dataloader
    test_dataloader = DataLoader(siamese_dataset_test,
                                 shuffle=True,
                                 batch_size=1)

    # 生成对比图像
    dataiter = iter(test_dataloader)
    x0, _, _ = next(dataiter)

    for i in range(10):
        _, x1, label2 = next(dataiter)
        concatenated = torch.cat((x0, x1), 0)
        output1, output2 = model(x0.cpu(), x1.cpu())
        euclidean_distance = F.pairwise_distance(output1, output2)
        imshow(torchvision.utils.make_grid(concatenated), 'Dissimilarity: {:.2f}'.format(euclidean_distance.item()))


def buildVariable(path):
    img = PIL.Image.open(path)
    img = img.convert(Const.SIAMESE_NETWORK_CONVERT)
    transform = transforms.Compose(
        [transforms.Resize(Const.SIAMESE_NETWORK_TRANS_RESIZE), transforms.ToTensor()])
    img1 = transform(img)
    result = img1.unsqueeze(0)
    return Variable(result)


def one2one(res, target, serialNo):
    modelPath = getModelPath()
    modelPath = os.path.join(modelPath,
                             Const.SMART_CLASSIFY_SIAMESE_NETWORK)
    modelSavePath = modelPath + Const.CONNECTOR_UNDERLINE + serialNo + Const.SMART_SIAMESE_NETWORK_MODEL_SUFFIX
    model = torch.load(modelSavePath).cpu()
    input1 = buildVariable(res)
    input2 = buildVariable(target)
    output1, output2 = model(Variable(input1).cpu(), Variable(input2).cpu())
    euclidean_distance = F.pairwise_distance(output1, output2)
    diff = euclidean_distance.cpu().detach().numpy()[0]
    return diff


def test3():
    modelPath = getModelPath()
    modelPath = os.path.join(modelPath,
                             Const.SMART_CLASSIFY_SIAMESE_NETWORK)
    modelSavePath = modelPath + Const.CONNECTOR_UNDERLINE + 'model' + Const.SMART_SIAMESE_NETWORK_MODEL_SUFFIX
    model = torch.load(modelSavePath).cpu()


if __name__ == '__main__':
    training_dir = "D:/data/images/data/faces/other"  # 训练集地址
    serialNo = '10000'
    train(training_dir, serialNo)
    res = r'D:/data/images/data/faces/other/liuyifei/2.jpeg'
    target = r'D:/data/images/data/faces/other/liuyifei/5.jpeg'
    print(one2one(res, target, serialNo))
