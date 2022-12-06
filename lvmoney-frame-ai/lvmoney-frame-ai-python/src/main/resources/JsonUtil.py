# -*- coding: utf-8 -*-
import json
import datetime
import Const


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


def turn_param_style(params: dict):
    '''
    将参数名的驼峰形式转为下划线形式
    key值小写
    :param params:
    :return:
    '''
    temp_dict = {}
    for name, value in params.items():
        new_name = ""
        name += Const.SPACE  # 为了防止数据溢出
        for i in range(len(name) - 1):
            if i == 0:
                new_name += name[i]
            elif name[i].isupper() and name[i - 1].islower():
                new_name += Const.CONNECTOR_UNDERLINE + name[i]
            # 如果不在前面加上name += " "，这里会索引越界
            elif name[i].isupper() and name[i - 1].isupper() and name[i + 1].islower():
                new_name += Const.CONNECTOR_UNDERLINE + name[i]
            else:
                new_name += name[i]
        temp_dict.update({new_name.lower(): value})

    return temp_dict


def get_dict_key(dic, value):
    '''
    根据字典的值value获得该值对应的key
    :param dic:
    :param value:
    :return:
    '''
    key = list(dic.keys())[list(dic.values()).index(value)]
    return key


def getDateByLength(startTime: str, dateLenth: int, sec: int):
    '''
    获得从开始时间指定连续的多个时间
    时间相加单位位秒
    :param startTime: 开始时间
    :param dateLenth: 多少个时间
    :param sec: 每个时间加多少秒的时间
    :return:
    '''
    startTime = timeFormat(startTime)
    d = datetime.datetime.strptime(startTime, Const.DATE_FORMAT_SEC)
    time_interval = datetime.timedelta(seconds=sec)
    resultDate = []
    for item in range(0, dateLenth):
        date = d + time_interval
        resultDate.append(str(date))
        d = date
    return resultDate


def timeFormat(time):
    '''
    由于Prophet算法的限制，将原有时间精确到毫秒转换成精确到秒
    :param time:
    :return:
    '''
    sIndex = time.rfind(Const.POINT)
    if sIndex > 0:
        return time[:sIndex]
    else:
        return time


if __name__ == '__main__':
    time = '2022-8-14 23:59:00'
    num = 30
    print(getDateByLength(time, 30, 60))
