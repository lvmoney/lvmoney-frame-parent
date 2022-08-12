package com.lvmoney.frame.cache.common.exception;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.feign
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.enums.ExceptionCodeLevel;
import com.lvmoney.frame.base.core.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface CacheException {


    enum Proxy implements ExceptionType {
        /**
         * 重复提交支持与否
         */
        REPEATSUBMIT_SUPPORT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 1, "frame.repeactsubmit.support value is 'true' or 'false'"),

        /**
         * 重复提交支持与否
         */
        REPEATSUBMIT_REQUEST_ERROR(ExceptionCodeLevel.CACHE.getValue() + 2, "short time resubmission error"),

        /**
         * 获得分布式锁数据报错
         */
        LOCK_SOURCE_ERROR(ExceptionCodeLevel.CACHE.getValue() + 3, "get lock data error"),
        /**
         * 初始化分布式数据报错
         */
        LOCK_SOURCE_INIT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 4, "lock data init error"),
        /**
         * 分布式锁数据不存在
         */
        LOCK_SOURCE_NOT_EXIST(ExceptionCodeLevel.CACHE.getValue() + 5, "lock data not exist"),
        /**
         * 从redis获取数据报错
         */
        REDIS_NOT_EXIST(ExceptionCodeLevel.CACHE.getValue() + 6, "redis data error"),
        /**
         * redis key是需要的
         */
        REDIS_KEY_IS_REQUIRED(ExceptionCodeLevel.CACHE.getValue() + 7, "redis key is Required"),
        /**
         * 秒杀商品不存在
         */
        SECKILL_PRODUCT_NOT_EXIST(ExceptionCodeLevel.CACHE.getValue() + 8, "seckill product not exist"),


        /**
         * redis 监听支持与否
         */
        REDIS_LISTENER_SUPPORT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 9, "frame.redis.handler.support value is 'true' or 'false'"),
        /**
         * scan error
         */
        SCAN_COMMAND_ERROR(ExceptionCodeLevel.CACHE.getValue() + 10, "redis scan key error"),
        /**
         * redis 监听支持与否
         */
        REDIS_HOT_REQUEST_SUPPORT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 11, "frame.redis.hotRequest.support value is 'true' or 'false'"),
        /**
         * redis 监听支持与否
         */
        REDIS_DELAY_QUEUE_DYNAMIC_ERROR(ExceptionCodeLevel.CACHE.getValue() + 12, "rabbitmq @QueueListener bean not exist"),

        /**
         * redis 监听支持与否
         */
        REDIS_DELAYED_QUEUE_SUPPORT_ERROR(ExceptionCodeLevel.CACHE.getValue() + 13, "frame.redis.delayedQueue.support value is 'true' or 'false'"),
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
