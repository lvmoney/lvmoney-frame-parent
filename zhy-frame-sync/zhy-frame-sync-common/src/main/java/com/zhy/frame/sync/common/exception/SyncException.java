package com.zhy.frame.sync.common.exception;/**
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
public interface SyncException {


    enum Proxy implements ExceptionType {
        /**
         * canal服务参数不能为空
         */
        CANAL_PROP_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 1, "canal server properties not Required null"),
        /**
         * canal zk 地址报错
         */
        CANAL_ZK_ADDRESS_ERROR(ExceptionCodeLevel.CORE.getValue() + 2, "canal zk adress is error"),
        /**
         * canal 配置分析报错
         */
        CANAL_CONFIG_ANALYZE_ERROR(ExceptionCodeLevel.CORE.getValue() + 3, "canal config analyze error"),
        /**
         * canal 连接为空
         */
        CANAL_CONNECTOR_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 4, "canal connector not Required null"),
        /**
         * canal 配置不支持空
         */
        CANAL_CONFIG_IS_NULL(ExceptionCodeLevel.CORE.getValue() + 5, "canal config not Required null"),
        /**
         * canal 执行监听方法报错
         */
        CANAL_LISTENER_METHOD_INVOKE_ERROR(ExceptionCodeLevel.CORE.getValue() + 111, "canal invoke listener method error"),
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
