# -*- coding: utf-8 -*-
import json

import pandas as pd
import redis

import Const
from JsonUtil import getKeyValue, get_dict_key
from YamlUtil import read


class RedisNode(object):
    client = None

    def __new__(cls, *args, **kwargs):
        if not cls.client:
            cls.client = object.__new__(cls)
        return cls.client

    def getMap(self, param):
        """
        获得redis hash key的值
        :param param:
        :return:
        """
        return self.client.hgetall(param)

    def getMapKey(self, param, key):
        """
        获得redis hash key的值
        :param param:
        :param key:
        :return:
        """
        return self.client.hget(param, key)

    def addString(self, data, key):
        """
        新增string数据
        :param param:
        :param key:
        :return:
        """
        return self.client.set(key, data)

    def putMap(self, data, key):
        """
        新增map数据
        :param data:
        :param key:
        :return:
        """
        return self.client.hmset(key, data)

    def close(self):
        '''
        关闭
        :return:
        '''
        self.client.close()

    def __init__(self):
        yamlData = read(Const.SMART_CONFIG_FILE_NAME)
        redisConfig = getKeyValue(yamlData, Const.SMART_CONFIG_REDIS)
        redisHost = getKeyValue(redisConfig, Const.SMART_CONFIG_REDIS_HOST)
        redisPort = getKeyValue(redisConfig, Const.SMART_CONFIG_REDIS_PORT)
        redisPassword = getKeyValue(redisConfig, Const.SMART_CONFIG_REDIS_PASSWORD)
        redisDb = getKeyValue(redisConfig, Const.SMART_CONFIG_REDIS_DB)
        redisDecodeResponses = getKeyValue(redisConfig, Const.SMART_CONFIG_REDIS_DECODE_RESPONSE)
        self.client = redis_pool = redis.ConnectionPool(host=redisHost, port=redisPort, password=redisPassword,
                                                        db=redisDb,
                                                        decode_responses=redisDecodeResponses)
        self.client = redis.Redis(connection_pool=redis_pool)


def filterResult2Redis(data: pd.DataFrame, inputKey, predictId):
    '''
    数据过滤的结果保存到redis
    :param data:
    :param inputKey:
    :param predictId:
    :return:
    '''
    # 同一批的结果附上相同predictId
    redisClient = RedisNode()
    result = data.to_dict(Const.DICT_CLASSIFY_LIST)
    resultKey = inputKey.replace(Const.FILTER_RESULT_REPLACE_INPUT, Const.FILTER_RESULT_FILTER_REPLACE_RESULT)
    resultKey = resultKey + Const.CONNECTOR_UNDERLINE + predictId
    for k in result:
        v = result[k]
        redisClient.putMap({k: json.dumps(v)}, resultKey)
    redisClient.close()


def predictResult2Redis(data: pd.DataFrame, inputKey, predictId, predField):
    '''
    数据预测的结果保存到redis
    :param data:
    :param inputKey:
    :param predictId:
    :param predField:
    :return:
    '''
    # 同一批的结果附上相同predictId
    redisClient = RedisNode()
    result = data.to_dict(Const.DICT_CLASSIFY_LIST)
    resultKey = inputKey.replace(Const.PREDICT_RESULT_REPLACE_INPUT, Const.PREDICT_RESULT_REPLACE_RESULT)
    resultKey = resultKey + Const.CONNECTOR_UNDERLINE + predictId + Const.CONNECTOR_UNDERLINE + predField
    for k in result:
        v = result[k]
        redisClient.putMap({k: json.dumps(v)}, resultKey)
    redisClient.close()


if __name__ == '__main__':
    minio_obj = RedisNode()
    data = minio_obj.getMap('smartData_IsolationForest:_input_demo-istio_train')
    print(data)
