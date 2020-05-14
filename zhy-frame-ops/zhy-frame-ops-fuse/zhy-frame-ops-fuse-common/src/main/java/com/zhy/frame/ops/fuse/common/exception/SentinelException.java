package com.zhy.frame.ops.fuse.common.exception;/**
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
public interface SentinelException {


    enum Proxy implements ExceptionType {
        /**
         * 接口限流了
         */
        SENTINEL_FLOW_ERROR(ExceptionCodeLevel.OSS.getValue() + 1, "接口限流了!!!"),
        /**
         * 服务降级了
         */
        SENTINEL_DEGRADED_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 2, "服务降级了!!!"),
        /**
         * 热点参数限流了
         */
        SENTINEL_PARAM_FLOW_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 3, "热点参数限流了!!!"),
        /**
         * 没有找到对应的客户端详细信息
         */
        SENTINEL_SYSTEM_CLOCK_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 4, "触发系统保护规则!!!"),

        /**
         * 用户已经存在
         */
        SENTINEL_AUTHORITY_ERROR(ExceptionCodeLevel.AUTHENTICATION.getValue() + 5, "授权规则不通过!!!"),
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
