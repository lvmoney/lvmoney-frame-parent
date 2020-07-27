package com.zhy.frame.route.gateway.exception;/**
 * 描述:
 * 包名:com.zhy.oauth2.center.feign
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
public interface GatewayException {


    enum Proxy implements ExceptionType {
        /**
         * 路由支持
         */
        GATEWAY_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 1, "frame.gateway.support value is 'true' or 'false'"),
        /**
         * 验证码校验
         */
        GATEWAY_TOKEN_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 2, "token校验没有通过"),
        /**
         * 请求地址授权
         */
        GATEWAY_SHIRO_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 3, "请求的地址未授权"),
        /**
         * 白名单
         */
        GATEWAY_WHITE_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 4, "服务白名单校验未通过"),


        /**
         * 白名单支持
         */
        WHITE_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 5, "frame.white.support value is 'true' or 'false'"),

        /**
         * 内部服务没有被允许调用
         */
        GATEWAY_INTERNAL_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 6, "该服务不允许被其他服务调用"),

        /**
         * 鉴权支持
         */
        ALLOWABLE_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 7, "frame.allowable.support value is 'true' or 'false'"),


        /**
         * 鉴权支持
         */
        JWT_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 8, "frame.jwt.support value is 'true' or 'false'"),
        /**
         * 鉴权支持
         */
        SHIRO_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 9, "frame.shiro.support value is 'true' or 'false'"),

        /**
         * 鉴权支持
         */
        TOKEN_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 10, "check token fail"),

        /**
         * 鉴权支持
         */
        SHIRO_CHECK_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 11, "check authority fail"),
        /**
         * 鉴权支持
         */
        ALLOWABLE_SYS_ID_NOT_EXIST(ExceptionCodeLevel.ROUTE.getValue() + 8, "sys id not exist"),

        /**
         * 简单路由支持
         */
        SIMPLE_ROUTE_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 9, "frame.simpleRoute.support value is 'true' or 'false'"),

        /**
         * 重复提交支持
         */
        REPEAT_SUBMIT_SUPPORT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 10, "frame.repeatSubmit.support value is 'true' or 'false'"),


        /**
         * 重复提交错误
         */
        REPEAT_SUBMIT_ERROR(ExceptionCodeLevel.ROUTE.getValue() + 12, "short time resubmission error"),
        /**
         * 其他
         */
        OTHER(ExceptionCodeLevel.ROUTE.getValue() + 99, "未知错误");
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
