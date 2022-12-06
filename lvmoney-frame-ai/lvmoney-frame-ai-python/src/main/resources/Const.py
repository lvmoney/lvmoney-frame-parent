# -*- coding: utf-8 -*-

# 数据智能统一前缀
SMART_DATA_PREFIX = 'smartData'
# 逗号
CHARACTER_COMMA = ","

SPACE = ' '
# 下划线
CONNECTOR_UNDERLINE = '_'
# 冒号
COLON = ':'
# 点
POINT = '.'

# lstm相关统一前缀
SMART_DATA_LSTM_PREFIX = SMART_DATA_PREFIX + CONNECTOR_UNDERLINE + 'Lstm:'

# lstm 返回值连续的原始数据json key
SMART_DATA_LSTM_RESULT_KEY_ORIGINAL = 'original'

# lstm 返回值连续的预测数据json key
SMART_DATA_LSTM_RESULT_KEY_FUTURE = 'future'

# isolationForest 返回值所有值json key
SMART_DATA_ISOLATION_FOREST_RESULT_KEY_ALL = 'all'

# isolationForest 返回值异常值json key
SMART_DATA_ISOLATION_FOREST_RESULT_KEY_ABNORMAL = 'abnormal'

# isolationForest 返回值正常值json key
SMART_DATA_ISOLATION_FOREST_RESULT_KEY_NORMAL = 'normal'

# 配置文件名称
SMART_CONFIG_FILE_NAME = 'Config.yaml'

SMART_CONFIG_REDIS = 'redis'

SMART_CONFIG_REDIS_HOST = 'host'

SMART_CONFIG_REDIS_PORT = 'port'

SMART_CONFIG_REDIS_PASSWORD = 'password'

SMART_CONFIG_REDIS_DB = 'db'

SMART_CONFIG_REDIS_DECODE_RESPONSE = 'decode_responses'

SMART_CONFIG_SERVER = 'server'

SMART_CONFIG_SERVER_HOST = 'host'

SMART_CONFIG_SERVER_PORT = 'port'

SMART_CONFIG_SERVER_WORKERS = 'workers'

SMART_CONFIG_SERVER_NAME = 'name'

SMART_CONFIG_SERVER_MODEL_PATH = 'modelPath'

SMART_CONFIG_SERVER_RESULT_LSTM_PATH = 'lstmResult'

SMART_CONFIG_SERVER_RESULT_IF_PATH = 'ifResult'

SMART_CONFIG_SERVER_RESULT_PYOD_PATH = 'pyodResult'

# lstm 分类 多变量
SMART_LSTM_CLASSIFY_MULT = 'multivariable'

# lstm 分类 单变量
SMART_LSTM_CLASSIFY_UNIV = 'univariate'

# result 文件后缀
RESULT_FILE_SUFFIX = '.png'

RESULT_FILE_CLASSIFY = 'png'

# dict records
DICT_CLASSIFY_RECORDS = 'records'

# dict list
DICT_CLASSIFY_LIST = 'list'

# python lstm model 最优参数
SMART_DATA_LSTM_BEST_MODEL_PARAM_MULT = SMART_DATA_LSTM_PREFIX + CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_MULT + CONNECTOR_UNDERLINE + 'modelParam'

# python lstm model 最优参数
SMART_DATA_LSTM_BEST_MODEL_PARAM_UNIV = SMART_DATA_LSTM_PREFIX + CONNECTOR_UNDERLINE + SMART_LSTM_CLASSIFY_UNIV + CONNECTOR_UNDERLINE + 'modelParam'

SMART_CONFIG_MINIO = 'minio'

SMART_CONFIG_MINIO_HOST = 'host'

SMART_CONFIG_MINIO_PORT = 'port'

SMART_CONFIG_REDIS_ACCESS_KEY = 'accessKey'

SMART_CONFIG_MINIO_SECRET_KEY = 'secretKey'

SMART_CONFIG_MINIO_LSTM_RESULT_BUCKET = 'lstmBucket'

SMART_CONFIG_MINIO_IF_RESULT_BUCKET = 'ifBucket'

SMART_CONFIG_MINIO_PYOD_RESULT_BUCKET = 'pyodBucket'

# 计算数据在上下限范围内的 结果字段后缀
DETECTOR_RANGE_SUFFIX = CONNECTOR_UNDERLINE + 'result'
# 默认数据唯一标识字段
SMART_DATA_UNIQUE_ID = 'id'

# 默认数据time字段
SMART_DATA_FILED_TIME = 'time'

SMART_CONFIG_TAOS = 'taos'

SMART_CONFIG_TAOS_HOST = 'host'

SMART_CONFIG_TAOS_USER = 'user'

SMART_CONFIG_TAOS_PASSWORD = 'password'

SMART_CONFIG_TAOS_DB = 'db'

SMART_CONFIG_TAOS_CONFIG = 'config'
# jsonkey field
PARAM_FIELD_KEY = 'field'
# jsonkey clazz
PARAM_RESULT_KEY = 'clazz'
# jsonkey min
PARAM_FILED_MIN = 'min'
# jsonkey c
PARAM_FILED_C = 'c'
# jsonkey errorFraction
PARAM_FILED_ERROR_FRACTION = 'errorFraction'

PARAM_FILED_ERROR_FRACTION_PERCENT = 10000
# jsonkey max
PARAM_FILED_MAX = 'max'
# jsonkey window
PARAM_FILED_WINDOW = 'window'
# jsonkey defValue
PARAM_FILED_DEF_VALUE = 'defValue'
# jsonkey 是否通用字段
PARAM_FILED_CURRENCY = 'currency'
# jsonkey 通用配置
PARAM_FILED_CURRENCY_CONFIG = 'currencyConfig'
# 数据预测最大值
PARAM_FILED_PARAM_CAP = 'cap'
# 数据预测最小值
PARAM_FILED_PARAM_FLOOR = 'floor'

# jsonkey 特性配置
PARAM_FILED_CONFIG = 'config'

# jsonkey 数据用于预测的字段 originalField
PARAM_FILED_ORIGINAL_FIELD = 'originalField'

# jsonkey 预测的字段 predField
PARAM_FILED_PRED_FIELD = 'predField'

# jsonkey 预测lgbm模型 的param
PARAM_FILED_PRED_PARAM = 'params'
# jsonkey 预测lgbm模型fit 的eval_metric
PARAM_FILED_PRED_EVAL_METRIC = 'evalMetric'

# jsonkey 预测lgbm模型fit 的verbose
PARAM_FILED_PRED_EVAL_VERBOSE = 'verbose'

# jsonkey 预测lgbm模型fit 的early_stopping_rounds
PARAM_FILED_PRED_EVAL_EARLY_STOPPING_ROUNDS = 'earlyStoppingRounds'

# lgbm算法将数据集划分成多少份，其实就是执行几次选择每次最优model
PARAM_FILED_N_SPLITS = 'nSplits'

# 空值默认替换成-1
REPLACE_NAN_VALUE = -1
# http 服务统一前缀
HTTP_SERVER_ALGORITHM_PREFIX = '/algorithm/intelligent/'

# datafram 控制默认值
DATA_FRAME_DEFAULT_VALUE = '0.0'

ALGORITHM_FILTER_CLASSIFY_PYOD = 'pyod'
ALGORITHM_FILTER_CLASSIFY_ADTKLSAD = 'adtkLsad'
ALGORITHM_FILTER_CLASSIFY_ADTKTAD = 'adtkTad'
ALGORITHM_FILTER_CLASSIFY_ADTKTAD_PYOD = 'lgbm'
ALGORITHM_FILTER_CLASSIFY_ADTKLSAD_PYOD = 'adtkTad&pyod'
ALGORITHM_FILTER_CLASSIFY_ADTK_PYOD = 'adtkLsad&pyod'
ALGORITHM_FILTER_CLASSIFY_ADTK_PYOD = 'adtk&pyod'
ALGORITHM_FILTER_CLASSIFY_ADTK = 'adtk'
# 过滤结果redis key 替换值
FILTER_RESULT_REPLACE_RESULT = 'filterResult'

# 过滤结果redis key 替换值
FILTER_RESULT_FILTER_REPLACE_RESULT = 'filterResult'

# 预测结果redis key 替换值
PREDICT_RESULT_REPLACE_RESULT = 'predictResult'

# 过滤结果redis key 需要的替换值
FILTER_RESULT_REPLACE_INPUT = 'input'

# 预测结果redis key 需要的替换值
PREDICT_RESULT_REPLACE_INPUT = 'filterResult'

# 时间格式精确到秒
DATE_FORMAT_SEC = '%Y-%m-%d %H:%M:%S'
# 时间个数 key
DATA_PREDICT_FILED_NEXT_LENGTH = 'nextLength'
# 时间间隔 key
DATA_PREDICT_FILED_TIME_INTERVAL = 'timeInterval'
# lgbm
SMART_CLASSIFY_LGBM = 'lgbm'

# siameseNetwork
SMART_CLASSIFY_SIAMESE_NETWORK = 'siameseNetwork'

# siameseNetwork 模型保存后缀名
SMART_SIAMESE_NETWORK_MODEL_SUFFIX = '.pth'

# lgbm 模型保存后缀名
SMART_LGBM_MODEL_SUFFIX = '.pkl'
# 警告字段
SMART_DATA_FILED_WARN = 'warn'

# 时间格式精确到微秒
DATE_FORMAT_MIC = '%Y-%m-%d %H:%M:%S.%f'
# 正常
FILTER_RESULT_CLASSIFY_NORMAL = 'normal'
# 异常
FILTER_RESULT_CLASSIFY_ABNORMAL = 'abnormal'
# 原始
FILTER_RESULT_CLASSIFY_ORIGINAL = 'original'
# 处理后
FILTER_RESULT_CLASSIFY_HANDLE = 'handle'
# 智慧数据中台系统名称
SMART_DATA_PLATFORM_SYNC_SYSTEM_NAME = 'boao-platform-smart-sync'
# key前缀
SMART_DATA_PREDICT_PREFIX = 'smartData_SYNC:_predictResult'
# 数据合并
SMART_DATA_KEY_ALGORITHM_MERGER = 'merger'

# 数据过滤
SMART_DATA_KEY_ALGORITHM_FILTER = 'filter'
# 数据合并分类
SMART_DATA_MERGER_CLASSIFY_HANDLE = 'handle'
# 数据合并分类
SMART_DATA_MERGER_CLASSIFY_NORMAL = 'normal'
# 百度开源图文识别支持中文
PADDLE_OCR_LANG = 'ch'
# 百度开源图文CLS支持
PADDLE_OCR_CLS = True
# 字符o
CHAR_0 = '0'
# 字符1
CHAR_1 = '1'
# 数字100
INT_100 = 100
# 颜色rgb种类=3
COLOR_RGB_CLASSIFY = 3

SIAMESE_NETWORK_CONVERT = 'L'
# 转换size 默认
SIAMESE_NETWORK_TRANS_RESIZE = (100, 100)
