# -*- coding: utf-8 -*-

from PIL import Image
import math
import operator
from functools import reduce
from skimage import metrics
import cv2
import numpy as np

import Const


def similarityHash(hash1, hash2, shape=(10, 10)):
    '''
    Hash值对比
    对比两个图片矩阵的相似度，最后返回相似比值0~1
    值越接近1.0表示越相似
    :param hash1:
    :param hash2:
    :param shape:
    :return:
    '''
    n = 0
    # hash长度不同则返回-1代表传参出错
    if len(hash1) != len(hash2):
        return -1
    # 遍历判断
    for i in range(len(hash1)):
        # 相等则n计数+1，n最终为相似度
        if hash1[i] == hash2[i]:
            n = n + 1
    return n / (shape[0] * shape[1])


def avgHash(img, shape=(10, 10)):
    '''
    均值哈希算法
    将一张图片大小调整为10x10,然后转化为灰度图。
    求出平均灰度，大于平均灰度值更改为1，反之为0,生成哈希值。
    :param img:
    :param shape:
    :return:
    '''
    # 缩放为10*10
    img = cv2.resize(img, shape)
    # 转换为灰度图
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # s为像素和初值为0，result为hash值初值为''
    s = 0
    result = ''
    # 遍历累加求像素和
    for i in range(shape[0]):
        for j in range(shape[1]):
            s = s + gray[i, j]
    # 求平均灰度
    avg = s / Const.INT_100
    # 灰度大于平均值为1相反为0生成图片的hash值
    for i in range(shape[0]):
        for j in range(shape[1]):
            if gray[i, j] > avg:
                result = result + Const.CHAR_1
            else:
                result = result + Const.CHAR_0
    return result


def diffHash(img, shape=(10, 10)):
    '''
    差值感知算法
    首先，将一张图片大小调整为10x10,然后转化为灰度图。
    比较每行当前值与相邻的下一个值的大小。如果当前值比较大，灰度值更改为1，反之为0，生成哈希值。。
    :param img:
    :param shape:
    :return:
    '''
    # 缩放10*10
    img = cv2.resize(img, (shape[0] + 1, shape[1]))
    # 转换灰度图
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    result = ''
    # 每行前一个像素大于后一个像素为1，相反为0，生成哈希
    for i in range(shape[0]):
        for j in range(shape[1]):
            if gray[i, j] > gray[i, j + 1]:
                result = result + Const.CHAR_1
            else:
                result = result + Const.CHAR_0
    return result


def percHash(img, shape=(10, 10)):
    '''
    感知哈希算法
    将一张图片大小调整为10x10,然后转化为灰度图，进行离散余弦变换(dct)变换。
    opencv实现10x10掩码操作,并求出掩码区域均值，掩码区域像素值大于平均值掩码区域矩阵值设为1，反之为0。
    :param img:
    :param shape:
    :return:
    '''
    # 缩放10*10
    img = cv2.resize(img, shape)

    # 转换为灰度图
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
    # 将灰度图转为浮点型，再进行dct变换
    dct = cv2.dct(np.float32(gray))
    # opencv实现的掩码操作
    dct_roi = dct[0:10, 0:10]

    result = []
    acreage = np.mean(dct_roi)
    for i in range(dct_roi.shape[0]):
        for j in range(dct_roi.shape[1]):
            if dct_roi[i, j] > acreage:
                result.append(1)
            else:
                result.append(0)
    return result


def similarityRGBHist(image1, image2, size=(256, 256)):
    '''
    通过得到RGB每个通道的直方图来计算相似度
    将一张图片大小调整为256x256,并分离出rgb三个通道数组。
    使用图像直方图的函数，直方图均衡化，计算出0-255的数值
    对比两个图片直方图的重合度，最后返回相似比值0~1
    值越接近1.0表示越相似
    :param image1:
    :param image2:
    :param size:
    :return:
    '''
    # 将图像resize后，分离为RGB三个通道，再计算每个通道的相似值
    image1 = cv2.resize(image1, size)
    image2 = cv2.resize(image2, size)
    sub_image1 = cv2.split(image1)
    sub_image2 = cv2.split(image2)
    result = 0
    for im1, im2 in zip(sub_image1, sub_image2):
        result += similaritySingleHist(im1, im2)
    result = result / Const.COLOR_RGB_CLASSIFY
    return result


def similaritySingleHist(image1, image2):
    '''
    计算单通道的直方图的相似值
    输入一张图片,使用rgb三个通道的某一个通道。
    使用图像直方图的函数，直方图均衡化，计算出0-255的数值
    对比两个图片直方图的重合度，最后返回相似比值0~1
    值越接近1.0表示越相似
    :param image1:
    :param image2:
    :return:
    '''
    hist1 = cv2.calcHist([image1], [0], None, [256], [0.0, 255.0])
    hist2 = cv2.calcHist([image2], [0], None, [256], [0.0, 255.0])
    # 计算直方图的重合度
    result = 0
    for i in range(len(hist1)):
        if hist1[i] != hist2[i]:
            result = result + (1 - abs(hist1[i] - hist2[i]) / max(hist1[i], hist2[i]))
        else:
            result = result + 1
    result = result / len(hist1)
    if isinstance(result, list):
        return result[0]
    else:
        return result


def similarityPillow(img1, img2):
    '''
    Pillow获得相似度
    返回值0~未知大
    如果两张图片完全相等，则返回结果为浮点类型“0.0”，如果不相同则返回结果值越大。
    :param img1:
    :param img2:
    :return:
    '''
    h1 = img1.histogram()
    h2 = img2.histogram()

    result = math.sqrt(reduce(operator.add, list(map(lambda a, b: (a - b) ** 2, h1, h2))) / len(h1))
    return result


def similaritySsim(img1, img2, shape=(10, 10)):
    '''
    该指标能够更好的反应出两张图片的相似度，
    该指标的范围是[-1,1]，
    当SSIM=-1时表示两张图片完全不相似，
    当SSIM=1时表示两张图片非常相似。即该值越接近1说明两张图片越相似
    :param img1:
    :param img2:
    :return:
    '''
    return metrics.structural_similarity(img1, img2, multichannel=True)


def similarityMse(imageA, imageB):
    '''
    MSE，即均方误差，它是一个用来计算两张图片相似度的一个指标值，该值越小表示两张图像越相似
    但是当使用它进行相似性判断时，我们可能会遇到一些问题。主要问题是，
    像素强度之间的差距比较大并不一定意味着图像的内容有很大的差异。
    需要注意的是，MSE值为0时表示两张图像完全相似，大于1时意味着两张图片相似度较低，并将随着像素强度之间的平均差异的增加而继续增长
    :param imageA:
    :param imageB:
    :return:
    '''
    # 计算两张图片的MSE指标
    err = np.sum((imageA.astype("float") - imageB.astype("float")) ** 2)
    err /= float(imageA.shape[0] * imageA.shape[1])

    # 返回结果，该值越小越好
    return err


def main():
    img1_path = r'D:/data/images/wiswater/1.jpg'  # 指定图片路径
    img2_path = r'D:/data/images/wiswater/4.jpg'
    img1 = cv2.imread(img1_path)
    img2 = cv2.imread(img2_path)
    n = similarityRGBHist(img1, img2)
    print('单通道的直方图算法相似度：', n)

    hash1 = percHash(img1)
    hash2 = percHash(img2)
    n = similarityHash(hash1, hash2)
    print('均值哈希算法相似度：', n)
    image1 = Image.open(img1_path)
    image2 = Image.open(img2_path)
    result = similarityPillow(image1, image2)
    print(result)

    result = similarityMse(img1, img2)

    print(result)


if __name__ == "__main__":
    main()

# def image_contrast(img1, img2):
#
#     image1 = Image.open(img1)
#     image2 = Image.open(img2)
#
#     h1 = image1.histogram()
#     h2 = image2.histogram()
#
#     result = math.sqrt(reduce(operator.add, list(map(lambda a,b: (a-b)**2, h1, h2)))/len(h1) )
#     return result
#
# if __name__ == '__main__':
