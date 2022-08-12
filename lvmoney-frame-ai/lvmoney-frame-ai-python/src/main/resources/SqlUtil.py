def list2Values(data: list):
    '''
    将list数据转化成insert数据values
    :param data:
    :return:
    '''
    values = ''
    for col in data:
        if isinstance(col, list):
            item = str(tuple(col))
            values = values + item
        else:
            values = str(tuple(data))
            break
    return values
