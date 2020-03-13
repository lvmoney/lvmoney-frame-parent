package com.zhy.frame.dispatch.feign.execption;/**
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
public interface FrameHystrixException {


    enum Proxy implements ExceptionType {
        /**
         * 验证码报错
         */
        FEIGN_HYSTRIX_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 1, "服务访问失败，请联系管理人员!!!"),
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
