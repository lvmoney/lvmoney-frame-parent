# -*- coding: utf-8 -*-
import numpy as np
import pandas as pd
import json


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
