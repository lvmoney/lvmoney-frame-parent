package com.lvmoney.frame.pool.common.exception;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center
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
public interface PoolException {


    enum Proxy implements ExceptionType {
        /**
         * disruptor注解不存在
         */
        DISRUPTOR_DYNAMIC_NOT_EXIST(ExceptionCodeLevel.POOL.getValue() + 1, "disruptor @MsgHandler bean not exist"),

        /**
         * disruptor 消息处理报错
         */
        DISRUPTOR_MSG_HANDLER_ERROR(ExceptionCodeLevel.POOL.getValue() + 1, "disruptor EventHandler error");
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
