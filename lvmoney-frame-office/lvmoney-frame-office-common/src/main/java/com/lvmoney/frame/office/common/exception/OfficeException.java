package com.lvmoney.frame.office.common.exception;/**
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
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public interface OfficeException {


    enum Proxy implements ExceptionType {
        /**
         * word 转pdf报错
         */
        WORD_2_OFFICE_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 1, "office word 2 pdf error"),
        /**
         * office模板转word报错
         */
        TEMPLATE_2_WORD_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 2, "office template 2 word error"),
        /**
         * 模板不存在
         */
        TEMPLATE_NOT_EXIST(ExceptionCodeLevel.OFFICE.getValue() + 3, "office template not exsit"),
        /**
         * 模板的类型不支持
         */
        TEMPLATE_ELEMENT_NOT_SUPPORT(ExceptionCodeLevel.OFFICE.getValue() + 4, "office template element not support"),

        /**
         * base64 请求参数是需要的
         */
        BASE64_PARAM_IS_REQUIRED(ExceptionCodeLevel.OFFICE.getValue() + 5, "base64 paramter is required"),
        /**
         * base64 编码不支持
         */
        BASE64_ENCODING_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 6, "base64 encoding Unsupported"),

        /**
         * excel下载报错
         */
        EXCEL_DOWNLOAD_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 7, "excel download error"),
        /**
         * excel 模板报错
         */
        EXCEL_TEMPLATE_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 8, "excel template error"),

        /**
         * excel导入报错
         */
        EXCEL_IMPORT_ERROR(ExceptionCodeLevel.OFFICE.getValue() + 9, "excel import error"),
        ;
        private int code;
        private String description;

        Proxy(int code, String description) {
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
