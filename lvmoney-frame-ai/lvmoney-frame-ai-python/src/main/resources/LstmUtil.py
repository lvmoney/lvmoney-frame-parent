# _*_ coding:UTF-8 _*_
import numpy as np
from keras.models import Sequential
from keras.layers import Dense, Bidirectional, Dropout
from keras.layers import LSTM

import Const
from JsonUtil import getKeyValue
from YamlUtil import read


def createDataset(dataset, look_back, colIndex):
    """
    构造数据
    :param dataset:
    :param look_back:
    :return:
    """
    # 这里的look_back与timestep相同
    dataX = []
    dataY = []
    for i in range(look_back, len(dataset)):
        dataX.append(dataset[i - look_back:i, 0:dataset.shape[1]])
        dataY.append(dataset[i, colIndex])
    return np.array(dataX), np.array(dataY)


def buildModel(look_back, loss, optimizer, listUnits, denseUnits, dimension, dropout):
    """
    optimizer: SGD，Adagrad，Adadelta，Adam，Adamax，Nadam
    loss:mean_squared_error或mse
    mean_absolute_error或mae
    mean_absolute_percentage_error或mape
    mean_squared_logarithmic_error或msle
    squared_hinge
    hinge
    binary_crossentropy（亦称作对数损失，logloss）
    categorical_crossentropy：亦称作多类的对数损失，注意使用该目标函数时，需要将标签转化为形如(nb_samples, nb_classes)的二值序列
    sparse_categorical_crossentrop：如上，但接受稀疏标签。注意，使用该函数时仍然需要你的标签与输出值的维度相同，你可能需要在标签数据上增加一个维度：np.expand_dims(y,-1)
    kullback_leibler_divergence:从预测值概率分布Q到真值概率分布P的信息增益,用以度量两个分布的差异.
    cosine_proximity：即预测值与真实标签的余弦距离平均值的相反数

    mean_squared_error,adam
    """
    model = Sequential()
    model.add(Bidirectional(LSTM(units=listUnits, input_shape=(look_back, dimension))))
    model.add(Dense(units=denseUnits))  # 输出层采用全连接层;
    model.add(Dropout(dropout))
    model.compile(loss=loss, optimizer=optimizer)  # 损失函数是均方差，优化器是采用adam;
    return model
