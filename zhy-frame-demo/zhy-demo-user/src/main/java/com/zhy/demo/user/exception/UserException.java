package com.zhy.demo.user.exception;/**
 * 描述:
 * 包名:com.zhy.tmc.user.exception
 * 版本信息: 版本1.0
 * 日期:2019/11/25
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.common.enums.TmcExceptionCodeLevel;
import com.zhy.frame.base.core.exception.ExceptionType;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/25 9:13
 */
public interface UserException {
    /**
     * frame错误码对应
     */
    enum Proxy implements ExceptionType {
        /**
         * httpclient 获得连接信息报错
         */
        USER_LOGIN_CHECK_ERROR(TmcExceptionCodeLevel.USER.getValue() + 1, "用户的用户名和密码不匹配"),
        ;

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
