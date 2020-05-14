package com.zhy.frame.base.core.exception;


import com.zhy.frame.base.core.enums.ExceptionCodeLevel;

import static com.zhy.frame.base.core.constant.BaseConstant.SUCCESS_DEFAULT_CODE;

/**
 * @describe：路由错误码定义接口
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:34:43
 */
public interface CommonException {
    /**
     * frame错误码对应
     */
    enum Proxy implements ExceptionType {
        /**
         * 参数校验错误
         */
        PARAM_ERROR(ExceptionCodeLevel.PARAM.getValue(), "request parameter error"),

        /**
         * 其他未知错误
         */
        OTHER_ERROR(ExceptionCodeLevel.OTHER.getValue(), "unkown error"),
        /**
         * 参数校验错误
         */
        PARAMETER_ERROR(ExceptionCodeLevel.CORE.getValue() + 1, "param Violation error"),


        /**
         * 集合转树报错
         */
        MODULE_ROOT_PATH_ERROR(ExceptionCodeLevel.CORE.getValue() + 2, "get module root path error"),
        /**
         * feign 远程连接拒绝
         */
        FEIGN_CONNECTION_REFUSED(ExceptionCodeLevel.CORE.getValue() + 3, "feign rpc feign connection refused"),

        /**
         * inputstream 转文件报错
         */
        FILE_INPUTSTREAM2FILE_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 4, "inputstream 2 file error"),

        /**
         * base64 请求参数是需要的
         */
        BASE64_PARAM_IS_REQUIRED(ExceptionCodeLevel.CORE.getValue() + 5, "base64 paramter is required"),

        /**
         * base64 编码不支持
         */
        BASE64_ENCODING_ERROR(ExceptionCodeLevel.CORE.getValue() + 6, "base64 encoding Unsupported"),

        /**
         * list 复制报错
         */
        BEAN_LIST_COPY_ERROR(ExceptionCodeLevel.CORE.getValue() + 7, "list bean copy error"),

        /**
         * map转bean报错
         */
        BEAN_MAP_2_BEAN_ERROR(ExceptionCodeLevel.CORE.getValue() + 8, "map transform to bean error"),

        /**
         * 集合转树报错
         */
        TREE_CONVERSION_ERROR(ExceptionCodeLevel.CORE.getValue() + 9, "collection 2 tree error"),
        /**
         * 成功
         */
        SUCCESS(SUCCESS_DEFAULT_CODE, "success");

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
