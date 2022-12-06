import pandas as pd

import Const


def data2Csv(data: pd.DataFrame, path: str):
    '''
    将数据写入csv，字段time格式化
    :param data:
    :param path:
    :return:
    '''
    columnName = data.columns.values.tolist()
    if Const.SMART_DATA_FILED_TIME in columnName:
        data[Const.SMART_DATA_FILED_TIME] = data[Const.SMART_DATA_FILED_TIME].astype('datetime64[ns]')
    data.to_csv(path, index=None, date_format=Const.DATE_FORMAT_SEC)
