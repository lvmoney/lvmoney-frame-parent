package com.lvmoney.frame.dispatch.common.exception;


import com.lvmoney.frame.base.core.enums.ExceptionCodeLevel;
import com.lvmoney.frame.base.core.exception.ExceptionType;

/**
 * @describe：路由错误码定义接口
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:34:43
 */
public interface DispatchException {
    /**
     * frame错误码对应
     */
    enum Proxy implements ExceptionType {
        /**
         * httpclient json请求报错
         */
        HTTPCLIENT_JSON_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 1, "httpclient json request error"),
        /**
         * httpclient file 请求报错
         */
        HTTPCLIENT_FILE_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 2, "httpclient file request error"),
        /**
         * httpclient file 请求报错
         */
        HTTPCLIENT_FILE2_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 3, "httpclient file request error"),
        /**
         * httpclient 获得连接信息报错
         */
        HTTPCLIENT_CONNECTION_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 4, "httpclient request connection msg error"),
        /**
         * feign 错误获得body报错
         */
        FEIGN_BODY_ERROR(ExceptionCodeLevel.DISPATCH.getValue() + 5, "get feign response body error"),

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
    }
}
