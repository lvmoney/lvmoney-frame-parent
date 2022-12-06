# -*- coding: utf-8 -*-
import json

import numpy as np
import pandas as pd
import taos
import pandas

import Const
import TaosConst
from JsonUtil import getKeyValue
from SqlUtil import list2Values
import datetime

from YamlUtil import read


class DateEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, datetime.datetime):
            return obj.strftime(TaosConst.RESULT_DATA_DATETIME_FORMAT)
        else:
            return json.JSONEncoder.default(self, obj)


class TaosNode(object):
    conn = None

    def __new__(cls, *args, **kwargs):
        if not cls.conn:
            cls.conn = object.__new__(cls)
        return cls.conn

    def insert(self, sql, insertData: list):
        '''
        数据插入td
        :param sql: sql语句的主体，例如；INSERT INTO filter_result (create_date, id, input_cod) VALUES
        :param insertData: 插入的数据，单条数据插入：单个list或者单个list嵌套成一个list，批量插入：多个list嵌套成一个list
        :return:
        '''
        values = list2Values(insertData)
        self.conn.execute(sql + values)

    def select(self, sql, values={}):
        """
        查询数据
        :param param:
        :return:
        """

        resultDb = {}
        if len(values):
            tup = tuple(values)
            resultDb = pandas.read_sql(sql % tup, self.conn)
        else:
            resultDb = pandas.read_sql(sql, self.conn)
        resultDb = resultDb.round(TaosConst.RESULT_DATA_DECIMAL)
        result = resultDb.to_dict(Const.DICT_CLASSIFY_RECORDS)
        return result

    def selectByPage(self, sql, page: int, pageSize: int, values={}):
        """
        分页查询数据
        :param param:
        :return:
        """

        start = (page - 1) * pageSize

        limit = TaosConst.TAOS_KEYWORD_LIMIT + Const.SPACE + str(start) + Const.CHARACTER_COMMA + str(pageSize)
        resultDb = {}
        if len(values):
            tup = tuple(values)
            resultDb = pandas.read_sql(sql % tup + Const.SPACE + limit, self.conn)
        else:
            resultDb = pandas.read_sql(sql + Const.SPACE + limit, self.conn)
        resultDb = resultDb.round(TaosConst.RESULT_DATA_DECIMAL)
        result = resultDb.to_dict(Const.DICT_CLASSIFY_RECORDS)
        return result

    def close(self):
        '''
        关闭
        :return:
        '''
        self.conn.close()

    def __init__(self):
        '''
        通过配置获得
        :return:
        '''
        yamlData = read(Const.SMART_CONFIG_FILE_NAME)
        taosConfig = getKeyValue(yamlData, Const.SMART_CONFIG_TAOS)
        taosHost = str(getKeyValue(taosConfig, Const.SMART_CONFIG_TAOS_HOST))
        taosUser = str(getKeyValue(taosConfig, Const.SMART_CONFIG_TAOS_USER))
        taosPassword = str(getKeyValue(taosConfig, Const.SMART_CONFIG_TAOS_PASSWORD))
        taosDb = str(getKeyValue(taosConfig, Const.SMART_CONFIG_TAOS_DB))
        taosConfig = str(getKeyValue(taosConfig, Const.SMART_CONFIG_TAOS_CONFIG))
        conn = taos.connect(host=taosHost, user=taosUser, password=taosPassword, database=taosDb, config=taosConfig)
        self.conn = conn


def filterResult2Taos(data: pd.DataFrame, predictId):
    '''
    数据过滤的结果保存到taos
    :param data:
    :param predictId:
    :return:
    '''
    # 同一批的结果附上相同predictId
    data[TaosConst.TABLE_FILTER_PREDICT_ID] = predictId
    createDate = getCreateDate(len(data))
    data[TaosConst.TABLE_FILTER_CREATE_DATE] = createDate
    # 将dataframe数据转换从list，作为sql 需要插入的数据
    array = np.array(data)
    insertDataList = array.tolist()
    columnName = data.columns.values.tolist()
    '''
    拼接insert 的字段，该字段和插入数据的dataframe 列名 一致
    根据sql的需要将dataframe 列名通过逗号隔开
    '''
    insertFiled = Const.CHARACTER_COMMA.join([str(i) for i in columnName])
    '''
    根据taossql的特性，我们通过占位符的方式来赋值，生成最终的sql
    '''
    sql = TaosConst.SQL_TAOS_INSERT.format(TaosConst.TABLE_NAME_FILTER_RESULT, insertFiled)
    bigDataInsert(sql, insertDataList)


def predictResult2Taos(data: pd.DataFrame, predField, predictId):
    '''
    将预测结果保存到taos
    预测字段名称保存到field_name
    预测字段值保存到field_value
    :param data:
    :param predField:
    :param predictId:
    :return:
    '''
    insertData = pd.DataFrame()
    createDate = getCreateDate(len(data))
    sql = TaosConst.SQL_TAOS_INSERT.format(TaosConst.TABLE_NAME_PREDICT_RESULT,
                                           TaosConst.TABLE_PREDICT_RESULT_FIELD_ALL)
    # 这里要特别注意，插入数据的顺序要和sql语句insert字段的顺序一致
    insertData[TaosConst.TABLE_FILTER_CREATE_DATE] = createDate
    insertData[Const.SMART_DATA_FILED_TIME] = data[Const.SMART_DATA_FILED_TIME]
    insertData[TaosConst.TABLE_FILTER_PREDICT_ID] = predictId
    insertData[TaosConst.TABLE_PREDICT_RESULT_FIELD_FILED_NAME] = predField
    insertData[TaosConst.TABLE_PREDICT_RESULT_FIELD_FILED_VALUE] = data[predField]

    array = np.array(insertData)
    insertDataList = array.tolist()

    bigDataInsert(sql, insertDataList)


def bigDataInsert(sql, insertData):
    '''
    大量数据批量插入taos
    :param sql:
    :param insertData:
    :return:
    '''
    insertDataLen = len(insertData)
    taosClient = TaosNode()
    if (insertDataLen > TaosConst.INSERT_MAX_SIZE):
        '''
        解决maxSQLLenght限制
        当数据总条数超过2000将数据分每次2000条数据插入
        '''
        startIndex = 0
        while (startIndex < insertDataLen):
            endIndex = startIndex + TaosConst.INSERT_MAX_SIZE
            itemData = insertData[startIndex:endIndex]
            taosClient.insert(sql, itemData)
            startIndex = endIndex
    else:
        '''
        如果数据条数小于2000，直接插入
        '''
        taosClient.insert(sql, insertData)
    taosClient.close()


def getCreateDate(length: int):
    '''
    获得指定长度的时间作为插入时间
    :param length:
    :return:
    '''
    createDate = []
    '''
    taos批量插入：
    1、如果时间一样，那么只会插入一条数据
    2、需要通过代码给每条数据附上不同的创建时间
    '''
    now = datetime.datetime.now()
    for i in range(length):
        '''
        代码效率太高，就算用循环生成的时间基本一样
        通过给最开始的时间循环加上一定的时间，得到不同的时间
        加的时间需要是毫秒，如果是微秒，taos识别不明显数据会因为时间丢失
        1毫秒的差距
        '''
        time_interval = datetime.timedelta(milliseconds=1)
        now = now + time_interval
        createDate.append(str(now))
    return createDate


def algorithmLog2Taos(predictId, classify, param, field, inputDataDataFrame: pd.DataFrame):
    '''
    记录日志
    :param predictId:
    :param classify:
    :param param:
    :param field:
    :param inputDataDataFrame:
    :return:
    '''

    timeDataFrame = inputDataDataFrame[Const.SMART_DATA_FILED_TIME]
    # 开始时间以dataframe 中time的第一个时间
    startDate = timeDataFrame.values[0]
    # 开始时间以dataframe 中time的最后一个时间
    endDate = timeDataFrame.values[len(timeDataFrame) - 1]
    sql = TaosConst.SQL_TAOS_INSERT.format(TaosConst.TABLE_NAME_ALGORITHM_LOG, TaosConst.TABLE_ALGORITHM_LOG_FIELD_ALL)
    now = datetime.datetime.now()
    insertData = [str(now), predictId, classify, json.dumps(param, indent=2), json.dumps(field, indent=2), startDate,
                  endDate]
    taosClient = TaosNode()
    taosClient.insert(sql, insertData)
    taosClient.close()


if __name__ == '__main__':
    taos_obj = TaosNode(host='192.168.3.171', user='root', password='taosdata', db='platform_smart_filter',
                        config="C:\TDengine\cfg")
    list2 = ['2021-07-13 14:06:33']
    sql1 = 'select * from filter_result where create_date>\'%s\' order by create_date'
    sql2 = 'select * from filter_result  order by create_date'
    print(json.dumps(taos_obj.selectByPage(sql2, 1, 4), cls=DateEncoder))
    # list1 = ['2021-07-21 14:06:33.196', 100000, 0.31]
    #
    # list2 = [1, 2, 3, 4]
    # list3 = ["a", "b", "c", "d"]
    #
    # listAll = []
    # listAll.append(list1)
    # listAll.append(list2)
    # listAll.append(list3)
    #
    # taos_obj.insert('INSERT INTO filter_result (create_date, id, input_cod) VALUES', list1)
