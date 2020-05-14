package com.zhy.frame.mq.common.exception;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.enums.ExceptionCodeLevel;
import com.zhy.frame.base.core.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface MqException {


    enum Proxy implements ExceptionType {
        /**
         * rabbitmq 获得 simple数据报错
         */
        RABBIT_MESSAGE_RECEIVER_SIMPLE_ERROR(ExceptionCodeLevel.MQ.getValue() + 1, "rabbitmq receiver simple message error"),
        /**
         * rabbitmq 获得 topic数据报错
         */
        RABBIT_MESSAGE_RECEIVER_TOPIC_ERROR(ExceptionCodeLevel.MQ.getValue() + 2, "rabbitmq receiver topic message error"),
        /**
         * rabbitmq 获得 fanout数据报错
         */
        RABBIT_MESSAGE_RECEIVER_FANOUT_ERROR(ExceptionCodeLevel.MQ.getValue() + 3, "rabbitmq receiver fanout message error"),
        /**
         * kafka异步发送报错
         */
        KAFKA_SEND_SYN_INTERRUPTED_ERROR(ExceptionCodeLevel.MQ.getValue() + 4, "kafka Synchronous send  interrupted error"),
        /**
         * kafka异步发送报错
         */
        KAFKA_SEND_SYN_EXE_ERROR(ExceptionCodeLevel.MQ.getValue() + 5, "kafka Synchronous send  Execution error"),
        /**
         * kafka异步发送超时
         */
        KAFKA_SEND_SYN_TIME_ERROR(ExceptionCodeLevel.MQ.getValue() + 6, "kafka Synchronous send  timeout error"),
        /**
         * kafka注解不对
         */
        KAFKA_DYNAMIC_NOT_EXIST(ExceptionCodeLevel.MQ.getValue() + 7, "kafka @CustomerService bean not exist"),

        /**
         * rabbitmq 队列类型不正确
         */
        RABBIT_SENDER_TYPE_NOT_SUPPORT(ExceptionCodeLevel.MQ.getValue() + 8, "rabbitmq sender type not support"),

        /**
         * rabbitmq 队列类型不正确
         */
        RABBIT_MSG_TYPE_NOT_NULL(ExceptionCodeLevel.MQ.getValue() + 9, "rabbitmq msgType is null"),


        /**
         * rabbitmq注解不存在
         */
        RABBITMQ_PROVIDER_DYNAMIC_NOT_EXIST(ExceptionCodeLevel.MQ.getValue() + 10, "rabbitmq @DyProviderService bean not exist"),

        /**
         * kafka注解不对
         */
        KAFKA_PROVIDER_DYNAMIC_NOT_EXIST(ExceptionCodeLevel.MQ.getValue() + 11, "kafka @DyProviderService bean not exist"),

        /**
         * rabbitmq 队列类型不正确
         */
        KAFKA_MSG_TYPE_NOT_NULL(ExceptionCodeLevel.MQ.getValue() + 12, "kafka msgType is null"),


        /**
         * kafka 队列类型不正确
         */
        KAFKA_SENDER_TYPE_NOT_SUPPORT(ExceptionCodeLevel.MQ.getValue() + 13, "kafka sender type not support"),

        /**
         * rabbitmq注解不存在
         */
        RABBITMQ_DYNAMIC_NOT_EXIST(ExceptionCodeLevel.MQ.getValue() + 14, "rabbitmq @CustomerService bean not exist"),
        ;
        private int code;
        private String description;

        private Proxy(int code, String description) {
            this.code = code;
            this.description = description;
        }

        @Override
        public int getCode() {
            return this.code;
        }

        @Override
        public String getDescription() {
            return this.description;
        }
    }

}
