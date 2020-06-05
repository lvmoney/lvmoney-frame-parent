package com.zhy.db.mysql.common.exception;/**
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
public interface MysqlException {


    enum Proxy implements ExceptionType {
        /**
         * 多租户支持与否
         */
        SAAS_SUPPORT_ERROR(ExceptionCodeLevel.DB.getValue() + 1, "frame.saas.support value is 'true' or 'false'"),
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
