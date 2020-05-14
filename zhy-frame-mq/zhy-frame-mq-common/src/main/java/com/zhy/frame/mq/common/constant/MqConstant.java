package com.zhy.frame.mq.common.constant;/**
 * 描述:
 * 包名:com.zhy.frame.mq.common.constant
 * 版本信息: 版本1.0
 * 日期:2019/11/20
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/20 17:01
 */
public class MqConstant {
    /**
     * kafka simple
     */
    public static final String KAFKA_TYPE_SIMPLE = "simple";
    /**
     * kafka synchronous
     */
    public static final String KAFKA_TYPE_SYN = "synchronous";
    /**
     * rabbitmq ranout
     */
    public static final String RABBIT_TYPE_RANOUT = "ranout";
    /**
     * rabbitmq simple
     */
    public static final String RABBIT_TYPE_SIMPLE = "simple";
    /**
     * rabbitmq topic
     */
    public static final String RABBIT_TYPE_TOPIC = "topic";

    /**
     * rabbitmq topic 通配符
     */
    public static final String RABBIT_TYPE_TOPIC_RICE = "topicRich";


    /**
     * 错误key
     */
    public static final String MQ_ERROR_RECORD_REDIS_KEY = "mq_error_record";


    /**
     * 错误处理kafka
     */
    public static final String MQ_ERROR_TYPE_KAFKA = "mq_error_record_kafka";

    /**
     * 错误处理rabbitmq
     */
    public static final String MQ_ERROR_TYPE_RABBIT = "mq_error_record_rabbit";
}
