# -*- coding: utf-8 -*-
import redis


class Node(object):
    client = None

    def __new__(cls, *args, **kwargs):
        if not cls.client:
            cls.client = object.__new__(cls)
        return cls.client

    def __init__(self, host, port, password, db, decode_responses=True):
        self.client = redis_pool = redis.ConnectionPool(host=host, port=port, password=password, db=db,
                                                        decode_responses=decode_responses)
        self.client = redis.Redis(connection_pool=redis_pool)

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

if __name__ == '__main__':
    minio_obj = Node(host='192.168.3.162', port=6379, password='boao2022', db=5, decode_responses=True)
    data = minio_obj.getMap('smartData_IsolationForest:_input_demo-istio_train')
    print(data)
