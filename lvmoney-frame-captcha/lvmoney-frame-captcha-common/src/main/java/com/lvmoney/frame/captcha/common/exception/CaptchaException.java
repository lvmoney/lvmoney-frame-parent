package com.lvmoney.frame.captcha.common.exception;/**
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
public interface CaptchaException {


    enum Proxy implements ExceptionType {
        /**
         * 验证码校验报错
         */
        VALID_CODE_ERROR(ExceptionCodeLevel.CAPTCHA.getValue() + 1, "create Verification Code error"),
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
