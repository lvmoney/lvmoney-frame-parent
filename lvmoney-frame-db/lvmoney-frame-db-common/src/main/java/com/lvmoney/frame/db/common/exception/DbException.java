package com.lvmoney.frame.db.common.exception;/**
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
public interface DbException {


    enum Proxy implements ExceptionType {
        /**
         * 多租户支持与否
         */
        SAAS_SUPPORT_ERROR(ExceptionCodeLevel.DB.getValue() + 1, "frame.saas.support value is 'true' or 'false'"),

        /**
         * sharding 获得库表报错
         */
        SHARDING_DB_TABLE_ERROR(ExceptionCodeLevel.DB.getValue() + 2, "获得指定类型数据库分库分表的库表关系报错"),
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
