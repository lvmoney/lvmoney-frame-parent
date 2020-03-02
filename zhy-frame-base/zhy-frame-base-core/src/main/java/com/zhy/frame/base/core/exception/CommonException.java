package com.zhy.frame.base.core.exception;


import com.zhy.frame.base.core.enums.ExceptionCodeLevel;

import static com.zhy.frame.base.core.constant.BaseConstant.SUCCESS_DEFAULT_CODE;

/**
 * @describe：路由错误码定义接口
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:34:43
 */
public interface CommonException {
    /**
     * frame错误码对应
     */
    enum Proxy implements ExceptionType {
        /**
         * 参数校验错误
         */
        PARAM_ERROR(ExceptionCodeLevel.PARAM.getValue(), "request parameter error"),

        /**
         * 其他未知错误
         */
        OTHER_ERROR(ExceptionCodeLevel.OTHER.getValue(), "unkown error"),

        /**
         * 方法获取失败
         */
        METHOD_NOT_MAP(ExceptionCodeLevel.CORE.getValue() + 1, "uri not mapping"),
        /**
         * json错误
         */
        JSON_ERROR(ExceptionCodeLevel.CORE.getValue() + 2, "json invalid"),
        /**
         * 参数校验错误
         */
        PARAMETER_ERROR(ExceptionCodeLevel.CORE.getValue() + 3, "param Violation error"),
        /**
         * 登录校验失败
         */
        AUTHENTICATION_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 4, "authentication Violation error"),
        /**
         * 授权失败
         */
        PERMISSION_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 5, "permission Violation error"),
        /**
         * 方法执行错误
         */
        METHOD_INVOKE_ERROR(ExceptionCodeLevel.CORE.getValue() + 6, "method invoke error"),
        /**
         * token是需要的
         */
        TOKEN_IS_REQUIRED(ExceptionCodeLevel.AUTHENTICATION.getValue() + 7, "token is Required"),
        /**
         * token不存在
         */
        TOKEN_NOT_EXSIT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 8, "token not exist"),
        /**
         * token 校验失败
         */
        TOKEN_CHECK_FAILED(ExceptionCodeLevel.AUTHENTICATION.getValue() + 9, "token check failed"),
        /**
         * 用户不存在
         */
        TOKEN_USER_NOT_EXSIT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 10, "token,user not exist"),
        /**
         * 用户id不存在
         */
        TOKEN_USER_ID_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 11, "token,get userid error"),
        /**
         * token 校验报错
         */
        TOKEN_VERIFY_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 12, "token verify error"),
        /**
         * shiro 校验报错
         */
        SHIRO_VERIFY_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 13, "shiro verify error"),
        /**
         * shiro 登出报错
         */
        SHIRO_KICKOUT_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 14, "shiro kickout error"),
        /**
         * shiro 权限报错
         */
        SHIRO_UNAUTHORIZED_EXCEPTIONT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 15, "shiro UnauthorizedException"),
        /**
         * shiro url匹配为空
         */
        SHIRO_URI_EXCEPTIONT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 30, "redis shiro URI is null"),
        /**
         * shiro权限报错
         */
        SHIRO_AUTHORIZATION_EXCEPTIONT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 16, "shiro AuthorizationException"),
        /**
         * shiro redis数据不正确
         */
        SHIRO_REDIS_NOT_EXSIT(ExceptionCodeLevel.AUTHENTICATION.getValue() + 17, "shiro,redis data error"),
        /**
         * 从redis获取数据报错
         */
        REDIS_NOT_EXSIT(ExceptionCodeLevel.CACHE.getValue() + 18, "redis data error"),
        /**
         * redis key是需要的
         */
        REDIS_KEY_IS_REQUIRED(ExceptionCodeLevel.CACHE.getValue() + 19, "redis key is Required"),
        /**
         * mongo排序类型是需要的
         */
        MONGO_SORT_TYPE_IS_REQUIRED(ExceptionCodeLevel.OSS.getValue() + 20, "mongo sortType is Required"),
        /**
         * mongo排序类型是指定的
         */
        MONGO_SORT_TYPE_IS_ERROR(ExceptionCodeLevel.OSS.getValue() + 21, "mongo sortType value is desc or asc"),
        /**
         * gridfs存储报错
         */
        GRIDFS_SAVE_ERROR(ExceptionCodeLevel.OSS.getValue() + 22, "gridFs save error"),
        /**
         * gridfs文件存储太大
         */
        GRIDFS_FILE_SIZE(ExceptionCodeLevel.OSS.getValue() + 23, "gridFs fileSize too long"),
        /**
         * gridfs获取文件报错
         */
        GRIDFS_QUERY_FILE_ERROR(ExceptionCodeLevel.OSS.getValue() + 24, "gridFs get file error"),
        /**
         * rabbitmq注解不存在
         */
        RABBITMQ_DYNAMIC_NOT_EXSIT(ExceptionCodeLevel.MQ.getValue() + 25, "rabbitmq @DyCustomerService bean not exist"),
        /**
         * grifs 获取的文件不存在
         */
        GRIDFS_QUERY_FILE_NOT_EXSIT(ExceptionCodeLevel.CORE.getValue() + 26, "gridFs get file not exsit"),
        /**
         * es 查询的内容是需要的
         */
        ES_QUERY_CONTENT_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 40, "elasticsearch query content is required"),
        /**
         * es 查询的bean是需要的
         */
        ES_QUERY_BEAN_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 41, "elasticsearch query bean is required"),
        /**
         * es 分页是需要的
         */
        ES_QUERY_PAGEABLE_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 42, "elasticsearch query pageable is required"), ES_QUERY_PERCENT_IS_ERROR(ExceptionCodeLevel.CORE.getValue() + 43, "elasticsearch query percent need Less Than One"),
        /**
         * es 删除报错
         */
        ES_DELETE_IS_ERROR(ExceptionCodeLevel.CORE.getValue() + 44, "elasticsearch delete error"),
        /**
         * es fieles 是需要的
         */
        ES_DELETE_FIELDS_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 45, "elasticsearch FIELDS error is Required"),
        /**
         * rabbitmq 获得 simple数据报错
         */
        RABBIT_MESSAGE_RECEIVER_SIMPLE_ERROR(ExceptionCodeLevel.MQ.getValue() + 63, "rabbitmq receiver simple message error"),
        /**
         * rabbitmq 获得 topic数据报错
         */
        RABBIT_MESSAGE_RECEIVER_TOPIC_ERROR(ExceptionCodeLevel.MQ.getValue() + 64, "rabbitmq receiver topic message error"),
        /**
         * rabbitmq 获得 fanout数据报错
         */
        RABBIT_MESSAGE_RECEIVER_FANOUT_ERROR(ExceptionCodeLevel.MQ.getValue() + 66, "rabbitmq receiver fanout message error"),
        /**
         * kafka异步发送报错
         */
        KAFKA_SEND_SYN_INTERRUPTED_ERROR(ExceptionCodeLevel.MQ.getValue() + 67, "kafka Synchronous send  interrupted error"),
        /**
         * kafka异步发送报错
         */
        KAFKA_SEND_SYN_EXE_ERROR(ExceptionCodeLevel.MQ.getValue() + 67, "kafka Synchronous send  Execution error"),
        /**
         * kafka异步发送超时
         */
        KAFKA_SEND_SYN_TIME_ERROR(ExceptionCodeLevel.MQ.getValue() + 67, "kafka Synchronous send  timeout error"),
        /**
         * kafka注解不对
         */
        KAFKA_DYNAMIC_NOT_EXSIT(ExceptionCodeLevel.MQ.getValue() + 68, "kafka @DyCustomerService bean not exist"),
        /**
         * word 转pdf报错
         */
        WORD_2_OFFICE_ERROR(ExceptionCodeLevel.CORE.getValue() + 69, "office word 2 pdf error"),
        /**
         * office模板转word报错
         */
        TEMPLATE_2_WORD_ERROR(ExceptionCodeLevel.CORE.getValue() + 70, "office template 2 word error"),
        /**
         * 模板不存在
         */
        TEMPLATE_NOT_EXSIT(ExceptionCodeLevel.CORE.getValue() + 71, "office template not exsit"),
        /**
         * 模板的类型不支持
         */
        TEMPLATE_ELEMENT_NOT_SUPPORT(ExceptionCodeLevel.CORE.getValue() + 72, "office template element not support"),
        /**
         * shiro支持默认值
         */
        SHIRO_SUPPORT_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 81, "frame.shiro.support value is 'true' or 'false'"),
        /**
         * base64 请求参数是需要的
         */
        BASE64_PARAM_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 82, "base64 paramter is required"),
        /**
         * base64 编码不支持
         */
        BASE64_ENCODING_ERROR(ExceptionCodeLevel.CORE.getValue() + 83, "base64 encoding Unsupported"),
        /**
         * inputstream 转文件报错
         */
        FILE_INPUTSTREAM2FILE_ERROR(ExceptionCodeLevel.CORE.getValue() + 84, "inputstream 2 file error"),
        /**
         * jwt 支持与否
         */
        TOKEN_SUPPORT_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 81, "frame.jwt.support value is 'true' or 'false'"),
        /**
         * excel下载报错
         */
        EXCEL_DOWNLOAD_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 82, "excel download error"),
        /**
         * excel 模板报错
         */
        EXCEL_TEMPLATE_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 83, "excel template error"),
        /**
         * 获得分布式锁数据报错
         */
        LOCK_SOURCE_ERROR(ExceptionCodeLevel.CACHE.getValue() + 88, "get lock data error"),
        /**
         * 初始化分布式数据报错
         */
        LOCK_SOURCE_INIT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 90, "lock data init error"),
        /**
         * 分布式锁数据不存在
         */
        LOCK_SOURCE_NOT_EXIST(ExceptionCodeLevel.CACHE.getValue() + 88, "lock data not exist"),
        /**
         * list 复制报错
         */
        BEAN_LIST_COPY_ERROR(ExceptionCodeLevel.CORE.getValue() + 89, "list bean copy error"),
        /**
         * excel导入报错
         */
        EXCEL_IMPORT_ERROR(ExceptionCodeLevel.CORE.getValue() + 84, "excel import error"),
        /**
         * 记录日志需要用户数据
         */
        USERNMAE_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 85, "log record need token or username"),
        /**
         * 日志支持与否
         */
        LOG_SUPPORT_ERROR(ExceptionCodeLevel.CORE.getValue() + 86, "frame.log.support value is 'true' or 'false'"),
        /**
         * 验证码校验报错
         */
        VALIDCOE_ERROR(ExceptionCodeLevel.CAPTCHA.getValue() + 87, "create Verification Code error"),
        /**
         * 定时任务初始化错误
         */
        QUARTZ_INIT_ERROR(ExceptionCodeLevel.CORE.getValue() + 95, "quartz init error"),
        /**
         * 定时任务的时间格式报错
         */
        QUARTZ_CRON_ERROR(ExceptionCodeLevel.CORE.getValue() + 96, "quartz cron error"),
        /**
         * 定时任务添加job报错
         */
        QUARTZ_JOB_ADD_ERROR(ExceptionCodeLevel.CORE.getValue() + 97, "quartz add job error"),
        /**
         * 定时任务更新报错
         */
        QUARTZ_JOB_UPDATE_ERROR(ExceptionCodeLevel.CORE.getValue() + 98, "quartz update job error"),
        /**
         * 定时任务删除报错
         */
        QUARTZ_JOB_DELETE_ERROR(ExceptionCodeLevel.CORE.getValue() + 99, "quartz delete job error"),
        /**
         * 关闭所有定时任务报错
         */
        QUARTZ_JOB_CLOSE_ALL_ERROR(ExceptionCodeLevel.CORE.getValue() + 100, "quartz close core job error"),
        /**
         * 定时任务执行报错
         */
        QUARTZ_JOB_EXE_ERROR(ExceptionCodeLevel.CORE.getValue() + 101, "quartz  job execute error"),
        /**
         * httpclient json请求报错
         */
        HTTPCLIENT_JSON_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 102, "httpclient json request error"),
        /**
         * httpclient file 请求报错
         */
        HTTPCLIENT_FILE_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 103, "httpclient file request error"),
        /**
         * httpclient file 请求报错
         */
        HTTPCLIENT_FILE2_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 105, "httpclient file request error"),
        /**
         * httpclient 获得连接信息报错
         */
        HTTPCLIENT_CONNECTION_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 104, "httpclient request connection msg error"),
        /**
         * 服务崩了
         */
        SERVER_IS_DOWNGRADE(ExceptionCodeLevel.CORE.getValue() + 105, "server is downgrade"),
        /**
         * canal服务参数不能为空
         */
        CANAL_PROP_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 106, "canal server properties not Required null"),
        /**
         * canal zk 地址报错
         */
        CANAL_ZK_ADDRESS_ERROR(ExceptionCodeLevel.CORE.getValue() + 107, "canal zk adress is error"),
        /**
         * canal 配置分析报错
         */
        CANAL_CONFIG_ANALYZE_ERROR(ExceptionCodeLevel.CORE.getValue() + 108, "canal config analyze error"),
        /**
         * canal 连接为空
         */
        CANAL_CONNECTOR_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 109, "canal connector not Required null"),
        /**
         * canal 配置不支持空
         */
        CANAL_CONFIG_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 110, "canal config not Required null"),
        /**
         * canal 执行监听方法报错
         */
        CANAL_LISTENER_METHOD_INVOKE_ERROR(ExceptionCodeLevel.CORE.getValue() + 111, "canal invoke listener method error"),
        /**
         * map转bean报错
         */
        BEAN_MAP_2_BEAN_ERROR(ExceptionCodeLevel.CORE.getValue() + 112, "map transform to bean error"),

        /**
         * 线程池执行报错
         */
        THREAD_POOL_EXE_ERROR(ExceptionCodeLevel.CORE.getValue() + 113, "thread pool execute error"),
        /**
         * 集合转树报错
         */
        TREE_CONVERSION_ERROR(ExceptionCodeLevel.CORE.getValue() + 114, "collection 2 tree error"),
        /**
         * 集合转树报错
         */
        MODULE_ROOT_PATH_ERROR(ExceptionCodeLevel.OPS.getValue() + 115, "get module root path error"),

        /**
         * 重复提交支持与否
         */
        REPEATSUBMIT_SUPPORT_ERROR(ExceptionCodeLevel.CORE.getValue() + 116, "frame.repeactsubmit.support value is 'true' or 'false'"),

        /**
         * 重复提交支持与否
         */
        REPEATSUBMIT_REQUEST_ERROR(ExceptionCodeLevel.CORE.getValue() + 117, "short time resubmission error"),

        /**
         * rabbitmq 队列类型不正确
         */
        RABBIT_SENDER_TYPE_NOT_SUPPORT(ExceptionCodeLevel.MQ.getValue() + 118, "rabbitmq sender type not support"),

        /**
         * rabbitmq 队列类型不正确
         */
        RABBIT_MSG_TYPE_NOT_NULL(ExceptionCodeLevel.MQ.getValue() + 119, "rabbitmq msgType is null"),


        /**
         * rabbitmq注解不存在
         */
        RABBITMQ_PROVIDER_DYNAMIC_NOT_EXSIT(ExceptionCodeLevel.CORE.getValue() + 120, "rabbitmq @DyProviderService bean not exist"),

        /**
         * kafka注解不对
         */
        KAFKA_PROVIDER_DYNAMIC_NOT_EXSIT(ExceptionCodeLevel.MQ.getValue() + 121, "kafka @DyProviderService bean not exist"),

        /**
         * rabbitmq 队列类型不正确
         */
        KAFKA_MSG_TYPE_NOT_NULL(ExceptionCodeLevel.MQ.getValue() + 122, "kafka msgType is null"),


        /**
         * kafka 队列类型不正确
         */
        KAFKA_SENDER_TYPE_NOT_SUPPORT(ExceptionCodeLevel.MQ.getValue() + 123, "kafka sender type not support"),
        /**
         * 成功
         */
        SUCCESS(SUCCESS_DEFAULT_CODE, "success");

        private int code;
        private String description;

        Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return code;
        }

        @Override
        public String getDescription() {
            return description;
        }

//                public int getCode(String description){
//                        Proxy.
//                }
    }
}
