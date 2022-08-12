# -*- coding: utf-8 -*-
import numpy as np

import pandas as pd

import Const


def firstNonNan(listfloats):
    """
    获得 np.apply 单维数组第一个非空index
    :param listfloats:
    :return:
    """
    for k, v in np.ndenumerate(listfloats):
        if np.isnan(v) == False:
            return k[0]


def strArray2IntArray(strArray):
    """
    将字符串数组转化成整型数组
    :param strArray:
    :return:
    """
    return list(map(int, strArray))


def strArray2FloatArray(strArray):
    """
    将字符串数组转化成整型数组
    :param strArray:
    :return:
    """
    return list(map(float, strArray))


def str2Bool(strVal):
    """
    将 string 的 True,Flase 转化成bool类型
    :param strVal:
    :return:
    """
    return strVal == str(True)


def listTranAccuracy(data, decimals):
    """
    数组数据保留几位小数
    :param data:
    :param decimals:
    :return:
    """
    data = [round(i, decimals) for i in data]
    return data


def dataFrameTranAccuracy(data, decimals, field):
    """
    将dataframe 转出bean 并格式化精度
    :param data:
    :param decimals:
    :return:
    """
    data = data.to_dict(Const.DICT_CLASSIFY_LIST)
    for item in field:
        temp = data[item]
        tData = [round(i, decimals) for i in temp]
        data[item] = tData
    data = pd.DataFrame(data)
    data = data.to_dict(Const.DICT_CLASSIFY_RECORDS)
    return data
