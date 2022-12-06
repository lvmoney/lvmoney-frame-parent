# -*- coding: utf-8 -*-
import numpy as np
import pandas as pd
import json

import Const
import TaosConst


def toJson(df, orient='split'):
    """
    将dataframe转换成json
    :param df:
    :param orient:
    :return:
    """
    df_json = df.to_json(orient=orient, force_ascii=False)
    return json.loads(df_json)


def map2DataFrame(map, defValue):
    """
    遍历redis map数据构造成为DataFrame,并并设置空字符串的默认值。如果默认值是np.nan则删除表中含有任何NaN的行
    :param map:
    :return:
    """
    df = pd.DataFrame()
    for key, value in map.items():
        listData = json.loads(value)
        df.insert(loc=len(df.columns), column=key, value=listData)
    df.replace(to_replace=r'^\s*$', value=defValue, regex=True, inplace=True)
    if ('nan' == str(defValue)):
        df = df.dropna(axis=0, how='any')
    return df


def map2DataFrameNon(map):
    '''
    json转dataframe
    :param map:
    :return:
    '''
    df = pd.DataFrame()
    for key, value in map.items():
        listData = json.loads(value)
        df.insert(loc=len(df.columns), column=key, value=listData)
    return df


def columnTransform(data: pd.DataFrame):
    '''
    dataframe字段（influxdb中的原始字段）映射成taos的字段
    方便用来后面获得需要插入的数据库表字段对应拼接生成
    '''
    columnName = data.columns.values.tolist()
    result = pd.DataFrame()
    for k in columnName:
        v = TaosConst.TABLE_FILTER_FILED_DIST[k]
        result[v] = data[k]
    return result


def difference(big: pd.DataFrame, small: pd.DataFrame):
    '''
    根据两个dataframe的id值获得差异id，再根据差异id获得big中的值
    :param big:
    :param small:
    :return:
    '''
    bigId = big[Const.SMART_DATA_UNIQUE_ID]
    smallId = small[Const.SMART_DATA_UNIQUE_ID]
    normal = pd.concat([bigId, smallId, smallId]).drop_duplicates(keep=False)
    q = normal.values
    result = big[big[Const.SMART_DATA_UNIQUE_ID].isin(q)]
    result = result.reset_index(drop=True)
    return result


if __name__ == '__main__':
    big = pd.DataFrame()
    big['tet'] = [-1.9, -2, -3, 0, 4, 5, 6, 7, 8]

    big['11'] = ['-1.9', '-2', '-3,0', '0', '4', '5', '6', '7', '8']

    # big = big.mask(big.lt(1)).ffill().fillna(0).convert_dtypes()
    # big = big.mask(big.gt(5)).ffill().fillna(0).convert_dtypes()

    big = big.where(big.ge(0) & big.le(6)).ffill().fillna(0).astype(float)

    print(big)
