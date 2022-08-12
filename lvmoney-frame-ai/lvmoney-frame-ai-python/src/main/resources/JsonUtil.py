# -*- coding: utf-8 -*-
import json


def getKeyValue(data, key):
    """
    解析json获得指定key的值
    :param data:
    :param key:
    :return:
    """
    dataJson = json.dumps(data)
    cData = json.loads(dataJson)
    return cData[key]


if __name__ == '__main__':
    test = {'host': '192.168.3.162', 'port': 6379, 'password': 'boao2022', 'db': 5, 'decode_responses': True}
    test_json = json.dumps(test)
    jData = json.loads(test_json)
    print(jData['host'])
