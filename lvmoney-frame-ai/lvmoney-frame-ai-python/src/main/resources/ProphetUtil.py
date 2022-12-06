import json

import numpy as np
import pandas as pd
from prophet import Prophet

import Const
import ProphetConst
from DataFrameUtil import map2DataFrame
from ExcelUtil import data2Csv
from JsonUtil import turn_param_style, getDateByLength, timeFormat
from RedisUtil import RedisNode


def prophetPrediction(inputKey, fieldKey, paramKey, predictId):
    '''
    Facebook 提供的单行预测算法。
    :param inputKey:
    :param fieldKey:
    :param paramKey:
    :param predictId:
    :return:
    '''
    redisClient = RedisNode()

    inputData = redisClient.getMap(inputKey)
    field = redisClient.getMapKey(fieldKey, predictId)
    fieldJson = json.loads(field)
    param = redisClient.getMapKey(paramKey, predictId)
    redisClient.close()
    paramJson = json.loads(param)
    predField = fieldJson[Const.PARAM_FILED_PRED_FIELD]
    originalField = fieldJson[Const.PARAM_FILED_ORIGINAL_FIELD]
    lgbmParam = paramJson[Const.PARAM_FILED_PRED_PARAM]
    prophetParam = turn_param_style(lgbmParam)
    inputDataDataFrame = map2DataFrame(inputData, Const.DATA_FRAME_DEFAULT_VALUE)
    dLen = len(inputDataDataFrame)
    train = inputDataDataFrame[originalField]
    train[Const.SMART_DATA_FILED_TIME] = inputDataDataFrame[Const.SMART_DATA_FILED_TIME]
    train[ProphetConst.PROPHET_PARAM_NEW_TIME] = train.time.apply(lambda x: timeFormat(x))
    train = train.drop([Const.SMART_DATA_FILED_TIME], axis=1)
    '''
    将训练集最后的时间作为计算后续连续时间的起始时间
    训练数据集个数化后的最后一个时间即为起始时间
    '''
    testTimeValue = train[ProphetConst.PROPHET_PARAM_NEW_TIME].values.tolist()
    lastTime = testTimeValue[len(testTimeValue) - 1]
    nextLength = paramJson[Const.DATA_PREDICT_FILED_NEXT_LENGTH]
    timeInterval = paramJson[Const.DATA_PREDICT_FILED_TIME_INTERVAL]
    # 根据配置获得后面连续的时间
    addTime = getDateByLength(lastTime, nextLength, timeInterval)

    test = inputDataDataFrame[originalField]
    test[Const.SMART_DATA_FILED_TIME] = inputDataDataFrame[Const.SMART_DATA_FILED_TIME]

    test[ProphetConst.PROPHET_PARAM_NEW_TIME] = test.time.apply(lambda x: timeFormat(x))
    newTime = np.append(testTimeValue, addTime)
    test = test.drop([Const.SMART_DATA_FILED_TIME], axis=1)

    test = pd.DataFrame()
    test[ProphetConst.PROPHET_PARAM_NEW_TIME] = newTime
    model = Prophet(**prophetParam)
    if ProphetConst.PROPHET_PARAM_CAP in paramJson:
        cap = paramJson[ProphetConst.PROPHET_PARAM_CAP]
        train[ProphetConst.PROPHET_PARAM_CAP] = cap
        test[ProphetConst.PROPHET_PARAM_CAP] = cap
    if ProphetConst.PROPHET_PARAM_FLOOR in paramJson:
        floor = paramJson[ProphetConst.PROPHET_PARAM_FLOOR]
        train[ProphetConst.PROPHET_PARAM_FLOOR] = floor
        test[ProphetConst.PROPHET_PARAM_FLOOR] = floor
    model.fit(train.reset_index().rename(columns={ProphetConst.PROPHET_PARAM_NEW_TIME: ProphetConst.PROPHET_PARAM_DS,
                                                  predField: ProphetConst.PROPHET_PARAM_Y}))
    test_pred = model.predict(
        df=test.reset_index().rename(columns={ProphetConst.PROPHET_PARAM_NEW_TIME: ProphetConst.PROPHET_PARAM_DS}))
    data2Csv(test_pred, 'lgbmResults/数据预测(facebook).csv')
