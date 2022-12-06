import json

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
from adtk.data import validate_series
from adtk.transformer import ClassicSeasonalDecomposition
from keras import activations, losses
from keras.applications.densenet import layers
from matplotlib import lines
from mpl_toolkits.mplot3d import Axes3D
from sklearn import datasets, decomposition, preprocessing
import seaborn as sns
import keras.models as models
import tensorflow as tf

import sklearn.model_selection as model_selection
from mpl_toolkits.mplot3d import Axes3D
from DataFrameUtil import map2DataFrame
from RedisUtil import RedisNode
from sklearn import datasets, decomposition, preprocessing

###训练集，测试集数据分开
plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False
inputKey = 'smartData_IsolationForest:_input_demo-istio_pre'
fieldKey = 'smartData_IsolationForest:_field_section'
paramKey = 'smartData_IsolationForest:_param_section'
testKey = 'smartData_IsolationForest:_input_demo-istio_test'
predictId = '10000'
defValue = '0.0'
redisClient = RedisNode()

inputData = redisClient.getMap(inputKey)

testData = redisClient.getMap(testKey)
inputDataDataFrame = map2DataFrame(inputData, defValue)
testDataDataFrame = map2DataFrame(testData, defValue)
iforestField = redisClient.getMapKey(fieldKey, predictId)
iforestFieldJson = json.loads(iforestField)
iforestParam = redisClient.getMapKey(paramKey, predictId)
iforestParamJson = json.loads(iforestParam)

xField = iforestFieldJson['field']
resultFiled = iforestFieldJson['clazz']
input = inputDataDataFrame[xField]
test = testDataDataFrame[xField]
print("Mann-Kendall:", Kendall_change_point_detection(input['section']))
print("Pettitt:", Pettitt_change_point_detection(input['section']))
# print("Buishand U Test:",Buishand_U_change_point_detection(input['section']))
print("Standard Normal Homogeneity Test (SNHT):", SNHT_change_point_detection(input['section']))
