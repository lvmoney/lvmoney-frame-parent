# td 分页关键字
TAOS_KEYWORD_LIMIT = 'limit'
# 返回值的小数保留四位有效数
RESULT_DATA_DECIMAL = 4
# 返回值datatime 精确到毫秒
RESULT_DATA_DATETIME_FORMAT = '%Y-%m-%d %H:%M:%S:%f'

RESULT_DATA_FIELD_PAGE = 'page'

RESULT_DATA_FIELD_PAGE = 'page_size'

RESULT_DATA_FIELD_DATA = 'data'

RESULT_DATA_FIELD_TOTAL = 'total'

# 批量新增到taos sql 占位
SQL_TAOS_INSERT = 'INSERT INTO {} ({}) VALUES'

# 过滤结果表
TABLE_NAME_FILTER_RESULT = 'filter_result'

# 算法执行日志
TABLE_NAME_ALGORITHM_LOG = 'algorithm_log'

# 预测结果表
TABLE_NAME_PREDICT_RESULT = 'predict_result'

# 数据库字段predict_id
TABLE_FILTER_PREDICT_ID = 'predict_id'
# 数据库字段create_date
TABLE_FILTER_CREATE_DATE = 'create_date'
# 数据库字段record_date
TABLE_FILTER_RECORD_DATE = 'record_date'

# 列名和taos字段对应关系
TABLE_FILTER_FILED_DIST = {'jsssll': 'input_sh2o', 'jsljll': 'input_th2o', 'id': 'id', 'time': 'record_date',
                           'df1': 'input_pump'}
# 批量最大条数
INSERT_MAX_SIZE = 2000

# 数据库algorithm_log所有字段
TABLE_ALGORITHM_LOG_FIELD_ALL = 'create_date, predict_id,classify,param, field,start_date, end_date'

# 数据库predict_result所有字段
TABLE_PREDICT_RESULT_FIELD_ALL = 'create_date, record_date,predict_id,field_name,field_value'
# 预测结果字段名称
TABLE_PREDICT_RESULT_FIELD_FILED_NAME = 'field_name'
# 预测记过字段值
TABLE_PREDICT_RESULT_FIELD_FILED_VALUE = 'field_value'
