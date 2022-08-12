# -*- coding: utf-8 -*-
import json

import taos
import pandas

import Const
import TaosConst
from SqlUtil import list2Values
import datetime


class DateEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, datetime.datetime):
            return obj.strftime(TaosConst.RESULT_DATA_DATETIME_FORMAT)
        else:
            return json.JSONEncoder.default(self, obj)


class Node(object):
    conn = None

    def __new__(cls, *args, **kwargs):
        if not cls.conn:
            cls.conn = object.__new__(cls)
        return cls.conn

    def __init__(self, host, user, password, db, config):
        conn = taos.connect(host=host, user=user, password=password, database=db, config=config)
        self.conn = conn

    def insert(self, sql, insertData: list):
        '''
        数据插入td
        :param sql: sql语句的主体，例如；INSERT INTO filter_result (create_date, id, input_cod) VALUES
        :param insertData: 插入的数据，单条数据插入：单个list或者单个list嵌套成一个list，批量插入：多个list嵌套成一个list
        :return:
        '''
        values = list2Values(insertData)
        self.conn.execute(sql + values)
        self.close()

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
        self.close()
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
        self.close()
        return result

    def close(self):
        self.conn.close()


if __name__ == '__main__':
    taos_obj = Node(host='192.168.3.171', user='root', password='taosdata', db='platform_smart_filter',
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
