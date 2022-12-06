import numpy as np
from matplotlib import pyplot as plt

import Const
import DetectorConst
from DataFrameUtil import map2DataFrame
from adtk.detector import ThresholdAD, InterQuartileRangeAD, GeneralizedESDTestAD, PersistAD, LevelShiftAD
import pandas as pd
from scipy.stats import norm


def handleTad(data, defValue, field, high, low):
    """
    判断数据指定字段是否在上下限范围内
    low：下限，小于此值，视为异常
    high：上限，大于此值，视为异常
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)
    input = inputDataDataFrame[field]
    input = input.astype(float)
    threshold_ad = ThresholdAD(high=high, low=low)
    input.index = pd.to_datetime(input.index)
    anomalies = threshold_ad.detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    return input


def handleIqrAd(data, defValue, field, c):
    """
    箱线图算法，可作为数据上下限使用，不适合突变使用
    当c为float时，通过历史数据计算出 Q3+c*IQR 作为上限值，大于上限值视为异常
    当c=(float1,float2)时，通过历史数据计算出 (Q1-c1*IQR, Q3+c2*IQR) 作为正常范围，不在正常范围视为异常
    Q1 = data.quantile(0.25)
    Q3 = data.quantile(0.75)
    IQR=Q3 - Q1
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)
    input = inputDataDataFrame[field]
    input = input.astype(float)
    iqr_ad = InterQuartileRangeAD(c=c)
    input.index = pd.to_datetime(input.index)
    anomalies = iqr_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    return input


def handleGESDTAd(data, defValue, field, c):
    """
    箱线图算法，可作为数据上下限使用，不适合突变使用
    alpha：显著性水平 (Significance level)，alpha越小，表示识别出的异常约有把握是真异常
    原理:将样本点的值与样本的均值作差后除以样本标准差，取最大值，通过t分布计算阈值，对比阈值确定异常点

    计算步骤简述：
    设置显著水平alpha，通常取0.05
    指定离群比例h，若h=5%，则表示50各样本中存在离群点数为2
    计算数据集的均值mu与标准差sigma，将所有样本与均值作差，取绝对值，再除以标准差，找出最大值，得到esd_1
    在剩下的样本点中，重复步骤3，可以得到h个esd值
    为每个esd值计算critical value: lambda_i (采用t分布计算)
    统计每个esd是否大于lambda_i，大于的认为你是异常
    :param data:
    :param defValue:
    :param field:
    :param c:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)
    input = inputDataDataFrame[field]
    input = input.astype(float)
    esd_ad = GeneralizedESDTestAD(alpha=c)
    input.index = pd.to_datetime(input.index)
    anomalies = esd_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    return input


def handlePAd(data, defValue, field, c, window):
    """


    参数：
    window：参考窗长度，可为int, str
    c：分位距倍数，用于确定上下限范围
    side：检测范围，为'positive'时检测突增，为'negative'时检测突降，为'both'时突增突降都检测
    min_periods：参考窗中最小个数，小于此个数将会报异常，默认为None，表示每个时间点都得有值
    agg：参考窗中的统计量计算方式，因为当前值是与参考窗中产生的统计量作比较，所以得将参考窗中的数据计算成统计量，默认'median'，表示去参考窗的中位值

    原理：
    用滑动窗口遍历历史数据，将窗口后的一位数据与参考窗中的统计量做差，得到一个新的时间序列s1;
    计算s1的(Q1-c*IQR, Q3+c*IQR) 作为正常范围；
    若当前值与它参考窗中的统计量之差，不在2中的正常范围内，视为异常。

    调参：
    window：越大，模型越不敏感，不容易被突刺干扰
    c：越大，对于波动大的数据，正常范围放大较大，对于波动较小的数据，正常范围放大较小
    min_periods：对缺失值的容忍程度，越大，越不允许有太多的缺失值
    agg：统计量的聚合方式，跟统计量的特性有关，如 'median'不容易受极端值影响
    总结：先计算一条新的时间序列，再用箱线图作异常检测


    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)
    input = inputDataDataFrame[field]
    input = input.astype(float)
    print(input.describe())
    persist_ad = PersistAD(window=window, c=c, side=DetectorConst.ADTK_SIDE_BOTH)
    input.index = pd.to_datetime(input.index)
    anomalies = persist_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    return input


def handleLSAd(data, defValue, field, c, window):
    """
    参数：
    window：支持(10,5)，表示使用两个相邻的滑动窗，左侧的窗中的中位值表示参考值，右侧窗中的中位值表示当前值
    c：越大，对于波动大的数据，正常范围放大较大，对于波动较小的数据，正常范围放大较小，默认6.0
    side：检测范围，为'positive'时检测突增，为'negative'时检测突降，为'both'时突增突降都检测
    min_periods：参考窗中最小个数，小于此个数将会报异常，默认为None，表示每个时间点都得有值

    原理：
    该模型用于检测突变情况，相比于PersistAD，其抗抖动能力较强，不容易出现误报
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)
    input = inputDataDataFrame[field]
    input = input.astype(float)
    persist_ad = LevelShiftAD(window=window, c=c, side=DetectorConst.ADTK_SIDE_BOTH)
    input.index = pd.to_datetime(input.index)
    anomalies = persist_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    return input


def Kendall_change_point_detection(inputdata):
    inputdata = np.array(inputdata)
    n = inputdata.shape[0]
    # 正序列计算---------------------------------
    # 定义累计量序列Sk，初始值=0
    Sk = [0]
    # 定义统计量UFk，初始值 =0
    UFk = [0]
    # 定义Sk序列元素s，初始值 =0
    s = 0
    Exp_value = [0]
    Var_value = [0]
    # i从1开始，因为根据统计量UFk公式，i=0时，Sk(0)、E(0)、Var(0)均为0
    # 此时UFk无意义，因此公式中，令UFk(0)=0
    for i in range(1, n):
        for j in range(i):
            if inputdata[i] > inputdata[j]:
                s = s + 1
            else:
                s = s + 0
        Sk.append(s)
        Exp_value.append((i + 1) * (i + 2) / 4)  # Sk[i]的均值
        Var_value.append((i + 1) * i * (2 * (i + 1) + 5) / 72)  # Sk[i]的方差
        UFk.append((Sk[i] - Exp_value[i]) / np.sqrt(Var_value[i]))
    # ------------------------------正序列计算
    # 逆序列计算---------------------------------
    # 定义逆序累计量序列Sk2，长度与inputdata一致，初始值=0
    Sk2 = [0]
    # 定义逆序统计量UBk，长度与inputdata一致，初始值=0
    UBk = [0]
    UBk2 = [0]
    # s归0
    s2 = 0
    Exp_value2 = [0]
    Var_value2 = [0]
    # 按时间序列逆转样本y
    inputdataT = list(reversed(inputdata))
    # i从2开始，因为根据统计量UBk公式，i=1时，Sk2(1)、E(1)、Var(1)均为0
    # 此时UBk无意义，因此公式中，令UBk(1)=0
    for i in range(1, n):
        for j in range(i):
            if inputdataT[i] > inputdataT[j]:
                s2 = s2 + 1
            else:
                s2 = s2 + 0
        Sk2.append(s2)
        Exp_value2.append((i + 1) * (i + 2) / 4)  # Sk[i]的均值
        Var_value2.append((i + 1) * i * (2 * (i + 1) + 5) / 72)  # Sk[i]的方差
        UBk.append((Sk2[i] - Exp_value2[i]) / np.sqrt(Var_value2[i]))
        UBk2.append(-UBk[i])
    # 由于对逆序序列的累计量Sk2的构建中，依然用的是累加法，即后者大于前者时s加1，
    # 则s的大小表征了一种上升的趋势的大小，而序列逆序以后，应当表现出与原序列相反
    # 的趋势表现，因此，用累加法统计Sk2序列，统计量公式(S(i)-E(i))/sqrt(Var(i))
    # 也不应改变，但统计量UBk应取相反数以表征正确的逆序序列的趋势
    #  UBk(i)=0-(Sk2(i)-E)/sqrt(Var)
    # ------------------------------逆序列计算
    # 此时上一步的到UBk表现的是逆序列在逆序时间上的趋势统计量
    # 与UFk做图寻找突变点时，2条曲线应具有同样的时间轴，因此
    # 再按时间序列逆转结果统计量UBk，得到时间正序的UBkT，
    UBkT = list(reversed(UBk2))
    diff = np.array(UFk) - np.array(UBkT)
    K = list()
    # 找出交叉点
    for k in range(1, n):
        if diff[k - 1] * diff[k] < 0:
            K.append(k)
    # 做突变检测图时，使用UFk和UBkT
    plt.figure(figsize=(10, 5))
    plt.plot(range(1, n + 1), UFk, label='UFk')  # UFk
    plt.plot(range(1, n + 1), UBkT, label='UBk')  # UBk
    plt.ylabel('UFk-UBk')
    x_lim = plt.xlim()
    plt.plot(x_lim, [-1.96, -1.96], 'm--', color='r')
    plt.plot(x_lim, [0, 0], 'm--')
    plt.plot(x_lim, [+1.96, +1.96], 'm--', color='r')
    plt.legend(loc=2)  # 图例
    plt.show()
    return K


def Pettitt_change_point_detection(inputdata):
    inputdata = np.array(inputdata)
    n = inputdata.shape[0]
    k = range(n)
    inputdataT = pd.Series(inputdata)
    r = inputdataT.rank()
    Uk = [2 * np.sum(r[0:x]) - x * (n + 1) for x in k]
    Uka = list(np.abs(Uk))
    U = np.max(Uka)
    K = Uka.index(U)
    pvalue = 2 * np.exp((-6 * (U ** 2)) / (n ** 3 + n ** 2))
    if pvalue <= 0.05:
        change_point_desc = '显著'
    else:
        change_point_desc = '不显著'
    # Pettitt_result = {'突变点位置':K,'突变程度':change_point_desc}
    return K  # ,Pettitt_result


def Buishand_U_change_point_detection(inputdata):
    inputdata = np.array(inputdata)
    inputdata_mean = np.mean(inputdata)
    n = inputdata.shape[0]
    k = range(n)
    Sk = [np.sum(inputdata[0:x + 1] - inputdata_mean) for x in k]
    sigma = np.sqrt(np.sum((inputdata - np.mean(inputdata)) ** 2) / (n - 1))
    U = np.sum((Sk[0:(n - 2)] / sigma) ** 2) / (n * (n + 1))
    Ska = np.abs(Sk)
    S = np.max(Ska)
    K = list(Ska).index(S) + 1
    Skk = (Sk / sigma)
    return K


def SNHT_change_point_detection(inputdata):
    inputdata = np.array(inputdata)
    inputdata_mean = np.mean(inputdata)
    n = inputdata.shape[0]
    k = range(1, n)
    sigma = np.sqrt(np.sum((inputdata - np.mean(inputdata)) ** 2) / (n - 1))
    Tk = [x * (np.sum((inputdata[0:x] - inputdata_mean) / sigma) / x) ** 2 + (n - x) * (
            np.sum((inputdata[x:n] - inputdata_mean) / sigma) / (n - x)) ** 2 for x in k]
    T = np.max(Tk)
    K = list(Tk).index(T) + 1
    return K


def MTT(data, step):
    n = len(data)
    v = step + step - 2  # 自由度
    t = np.zeros((n - step - step + 1))
    ss = np.sqrt(1 / step + 1 / step)

    ttest = 2.31  # step=5,alpha=0.05，这个需要根据需要查表做改动

    for i in range(len(t)):
        n1 = data[i:i + step]
        n2 = data[i + step:i + step + step]
        x1 = np.mean(n1)  # 平均值
        x2 = np.mean(n2)
        s1 = np.std(n1)  # 方差
        s2 = np.std(n2)
        s = np.sqrt((step * s1 * s1 + step * s2 * s2) / v)
        t[i] = (x1 - x2) / (s * ss)

    plt.plot(t, label="滑动T值(step=%s)" % step)
    plt.axhline(0, ls="--", c="k")
    plt.axhline(ttest, ls="--", c="r", label='95%显著区间')
    plt.axhline(-ttest, ls="--", c="r")
    plt.legend(loc='upper center', frameon=False, ncol=2, fontsize=14)  # 图例
    plt.show()
    return t


def sens_slope_trend_detection(inputdata, conf_level=0.95):
    inputdata = pd.Series(inputdata)
    n = inputdata.shape[0]
    t = inputdata.value_counts()
    tadjs = sum(t * (t - 1) * (2 * t + 5))
    varS = (n * (n - 1) * (2 * n + 5) - tadjs) / 18
    k = 0
    d = []
    for i in range(n - 1):
        for j in range(i + 1, n):
            k = k + 1
            d.append((inputdata[j] - inputdata[i]) / (j - i))
    b_sen = np.median(d)
    C = norm.ppf(1 - (1 - conf_level) / 2) * np.sqrt(varS)
    rank_up = int(round((k + C) / 2 + 1))
    rank_lo = int(round((k - C) / 2))
    rank_d = sorted(d)
    lo = rank_d[rank_lo - 1]
    up = rank_d[rank_up - 1]
    S = 0
    for m in range(n):
        S = S + np.sum(np.sign(inputdata[m] - inputdata[0:m + 1]))
    sg = np.sign(S)
    z = sg * (np.abs(S) - 1) / np.sqrt(varS)
    pval = 2 * min(0.5, 1 - norm.cdf(np.abs(z)))
    return b_sen, z, pval


def turningPoints(array):
    '''
    turning_points(array) -> min_indices, max_indices
    Finds the turning points within an 1D array and returns the indices of the minimum and
    maximum turning points in two separate lists.
    获得数据转折点
    '''
    idx_max, idx_min = [], []
    if (len(array) < 3):
        return idx_min, idx_max

    NEUTRAL, RISING, FALLING = range(3)

    def get_state(a, b):
        if a < b: return RISING
        if a > b: return FALLING
        return NEUTRAL

    ps = get_state(array[0], array[1])
    begin = 1
    for i in range(2, len(array)):
        s = get_state(array[i - 1], array[i])
        if s != NEUTRAL:
            if ps != NEUTRAL and ps != s:
                if s == FALLING:
                    idx_max.append((begin + i - 1) // 2)
                else:
                    idx_min.append((begin + i - 1) // 2)
            begin = i
            ps = s
    return idx_min, idx_max


def currencyHandleTad(data, defValue, field, high, low):
    """
    所有字段用同一个规则
    判断数据指定字段是否在上下限范围内
    low：下限，小于此值，视为异常
    high：上限，大于此值，视为异常
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = map2DataFrame(data, defValue)

    iField = field.copy()
    iField.append(Const.SMART_DATA_UNIQUE_ID)
    iField.append(Const.SMART_DATA_FILED_TIME)
    inputAll = inputDataDataFrame[iField]
    inputAll = inputAll.dropna(axis=0, how='any')
    inputAll = inputAll.reset_index(drop=True)
    input = inputAll[field]
    input = input.astype(float)
    threshold_ad = ThresholdAD(high=high, low=low)
    input.index = pd.to_datetime(input.index)
    anomalies = threshold_ad.detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    for colName in field:
        resultFiled = colName + Const.DETECTOR_RANGE_SUFFIX
        normal = input[input[resultFiled] == False]
        normal = normal.drop([resultFiled], axis=1)
        normal = normal.reset_index(drop=True)
    return normal


def setCurrencyDefault(data, defValue, field, high, low):
    inputDataDataFrame = pd.DataFrame()
    if isinstance(data, pd.DataFrame):
        inputDataDataFrame = data
    else:
        inputDataDataFrame = map2DataFrame(data, defValue)
        # 将空值和nap转化成默认值
        inputDataDataFrame = inputDataDataFrame.replace(
            ['', np.nan],
            [Const.REPLACE_NAN_VALUE, Const.REPLACE_NAN_VALUE]
        )
    # inputDataDataFrame = map2DataFrame(data, defValue)
    iField = field.copy()
    iField.append(Const.SMART_DATA_UNIQUE_ID)
    iField.append(Const.SMART_DATA_FILED_TIME)
    inputAll = inputDataDataFrame[iField]
    inputAll = inputAll.reset_index(drop=True)
    input = inputAll[field]
    input = input.astype(float)
    input = input.where(input.ge(low) & input.le(high)).ffill().fillna(0).astype(float)
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    return input


def currencyHandleLSAd(data, field, c, window):
    """
    参数：
    window：支持(10,5)，表示使用两个相邻的滑动窗，左侧的窗中的中位值表示参考值，右侧窗中的中位值表示当前值
    c：越大，对于波动大的数据，正常范围放大较大，对于波动较小的数据，正常范围放大较小，默认6.0
    side：检测范围，为'positive'时检测突增，为'negative'时检测突降，为'both'时突增突降都检测
    min_periods：参考窗中最小个数，小于此个数将会报异常，默认为None，表示每个时间点都得有值

    原理：
    该模型用于检测突变情况，相比于PersistAD，其抗抖动能力较强，不容易出现误报
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputAll = data.copy()
    input = data[field]
    input = input.astype(float)
    persist_ad = LevelShiftAD(window=window, c=c, side=DetectorConst.ADTK_SIDE_BOTH)
    input.index = pd.to_datetime(input.index)
    anomalies = persist_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    for colName in field:
        resultFiled = colName + Const.DETECTOR_RANGE_SUFFIX
        normal = input[input[resultFiled] == False]
        normal = normal.drop([resultFiled], axis=1)
        normal = normal.reset_index(drop=True)
    return normal


def configHandleTad(data, defValue, field, otherField, high, low):
    """
    每个字段用不同配置策略
    判断数据指定字段是否在上下限范围内
    low：下限，小于此值，视为异常
    high：上限，大于此值，视为异常
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputDataDataFrame = pd.DataFrame()
    if isinstance(data, pd.DataFrame):
        inputDataDataFrame = data
    else:
        inputDataDataFrame = map2DataFrame(data, defValue)
        # 将空值和nap转化成默认值
        inputDataDataFrame = inputDataDataFrame.replace(
            ['', np.nan],
            [Const.REPLACE_NAN_VALUE, Const.REPLACE_NAN_VALUE]
        )
    iField = field.copy()
    iField.append(Const.SMART_DATA_UNIQUE_ID)
    iField.append(Const.SMART_DATA_FILED_TIME)
    inputAll = inputDataDataFrame[iField]
    inputAll = inputAll.reset_index(drop=True)
    input = inputAll[field]
    input = input.astype(float)
    threshold_ad = ThresholdAD(high=high, low=low)
    input.index = pd.to_datetime(input.index)
    anomalies = threshold_ad.detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    input[otherField] = inputDataDataFrame[otherField]
    normal = pd.DataFrame()
    for colName in field:
        resultFiled = colName + Const.DETECTOR_RANGE_SUFFIX
        normal = input[input[resultFiled] == False]
        normal = normal.drop([resultFiled], axis=1)
        normal = normal.reset_index(drop=True)
    return normal


def configHandleLSAd(data, field, otherField, c, window):
    """
    参数：
    window：支持(10,5)，表示使用两个相邻的滑动窗，左侧的窗中的中位值表示参考值，右侧窗中的中位值表示当前值
    c：越大，对于波动大的数据，正常范围放大较大，对于波动较小的数据，正常范围放大较小，默认6.0
    side：检测范围，为'positive'时检测突增，为'negative'时检测突降，为'both'时突增突降都检测
    min_periods：参考窗中最小个数，小于此个数将会报异常，默认为None，表示每个时间点都得有值

    原理：
    该模型用于检测突变情况，相比于PersistAD，其抗抖动能力较强，不容易出现误报
    :param data:
    :param defValue:
    :param field:
    :param high:
    :param low:
    :return:
    """
    inputAll = data.copy()
    input = data[field]
    input = input.astype(float)
    persist_ad = LevelShiftAD(window=window, c=c, side=DetectorConst.ADTK_SIDE_BOTH)
    input.index = pd.to_datetime(input.index)
    anomalies = persist_ad.fit_detect(input)
    input = input.reset_index(drop=True)
    for colName in field:
        input[colName + Const.DETECTOR_RANGE_SUFFIX] = anomalies[colName].values
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    input[otherField] = inputAll[otherField]
    for colName in field:
        resultFiled = colName + Const.DETECTOR_RANGE_SUFFIX
        normal = input[input[resultFiled] == False]
        normal = normal.drop([resultFiled], axis=1)
        normal = normal.reset_index(drop=True)
    return normal


def setConfigDefault(data, defValue, field, otherField, high, low):
    inputDataDataFrame = pd.DataFrame()
    if isinstance(data, pd.DataFrame):
        inputDataDataFrame = data
    else:
        inputDataDataFrame = map2DataFrame(data, defValue)
        # 将空值和nap转化成默认值
        inputDataDataFrame = inputDataDataFrame.replace(
            ['', np.nan],
            [Const.REPLACE_NAN_VALUE, Const.REPLACE_NAN_VALUE]
        )
    iField = field.copy()
    iField.append(Const.SMART_DATA_UNIQUE_ID)
    iField.append(Const.SMART_DATA_FILED_TIME)
    inputAll = inputDataDataFrame[iField]
    inputAll = inputAll.reset_index(drop=True)
    input = inputAll[field]
    input = input.astype(float)
    input = input.where(input.ge(low) & input.le(high)).ffill().fillna(0).astype(float)
    input[Const.SMART_DATA_UNIQUE_ID] = inputAll[Const.SMART_DATA_UNIQUE_ID]
    input[Const.SMART_DATA_FILED_TIME] = inputAll[Const.SMART_DATA_FILED_TIME]
    input[otherField] = inputDataDataFrame[otherField]

    return input


if __name__ == '__main__':
    big = pd.DataFrame()
    big['tet'] = [-1.9, 3, 4, 0, 4, 5, 4, 7, 8]

    big['t'] = ['3', '-2', '6', '0', '3', '500', '600', '7000', '8000']
    big['id'] = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    big['time'] = ['2022-09-10', '2022-09-10', '2022-09-10', '2022-09-10', '2022-09-10', '2022-09-10', '2022-09-10',
                   '2022-09-10', '2022-09-10']

    out = setCurrencyDefault(big, '0.0', ['tet', 't'], 6, 0)
    print(out)
