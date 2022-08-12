# -*- coding: utf-8 -*-
import yaml

import Const
from JsonUtil import getKeyValue


def read(path):
    """
读取yaml的值
"""
    file = open(path, 'r', encoding="utf-8")
    # 读取文件中的所有数据
    file_data = file.read()
    file.close()

    # 指定Loader
    data = yaml.load(file_data, Loader=yaml.FullLoader)
    return data;


def getServerName():
    """
    通过配置文件获得应用名称
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    serverConfig = getKeyValue(yamlData, Const.SMART_CONFIG_SERVER)
    serverName = getKeyValue(serverConfig, Const.SMART_CONFIG_SERVER_NAME)
    return serverName


def getModelPath():
    """
    通过配置文件获得lstm model存放目录
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    serverConfig = getKeyValue(yamlData, Const.SMART_CONFIG_SERVER)
    serverModelName = getKeyValue(serverConfig, Const.SMART_CONFIG_SERVER_MODEL_PATH)
    return serverModelName


def getLstmResultPath():
    """
    通过配置文件获得lstm result存放目录
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    serverConfig = getKeyValue(yamlData, Const.SMART_CONFIG_SERVER)
    serverModelName = getKeyValue(serverConfig, Const.SMART_CONFIG_SERVER_RESULT_LSTM_PATH)
    return serverModelName


def getIfResultPath():
    """
    通过配置文件获得if result存放目录
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    serverConfig = getKeyValue(yamlData, Const.SMART_CONFIG_SERVER)
    serverModelName = getKeyValue(serverConfig, Const.SMART_CONFIG_SERVER_RESULT_IF_PATH)
    return serverModelName


def getPyodResultPath():
    """
    通过配置文件获得if result存放目录
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    serverConfig = getKeyValue(yamlData, Const.SMART_CONFIG_SERVER)
    serverModelName = getKeyValue(serverConfig, Const.SMART_CONFIG_SERVER_RESULT_PYOD_PATH)
    return serverModelName


def getLstmResultBucket():
    """
    通过配置文件获得lstm bucket
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    minioConfig = getKeyValue(yamlData, Const.SMART_CONFIG_MINIO)
    lstmBucket = getKeyValue(minioConfig, Const.SMART_CONFIG_MINIO_LSTM_RESULT_BUCKET)
    return lstmBucket


def getIfResultBucket():
    """
    通过配置文件获得if bucket
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    minioConfig = getKeyValue(yamlData, Const.SMART_CONFIG_MINIO)
    ifBucket = getKeyValue(minioConfig, Const.SMART_CONFIG_MINIO_IF_RESULT_BUCKET)
    return ifBucket


def getPyodResultBucket():
    """
    通过配置文件获得if bucket
    :return:
    """
    yamlData = read(Const.SMART_CONFIG_FILE_NAME)
    minioConfig = getKeyValue(yamlData, Const.SMART_CONFIG_MINIO)
    ifBucket = getKeyValue(minioConfig, Const.SMART_CONFIG_MINIO_PYOD_RESULT_BUCKET)
    return ifBucket
