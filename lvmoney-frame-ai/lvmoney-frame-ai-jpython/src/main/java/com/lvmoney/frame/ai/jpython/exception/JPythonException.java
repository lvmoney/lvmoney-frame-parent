package com.lvmoney.frame.ai.jpython.exception;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center
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
public interface JPythonException {


    enum Proxy implements ExceptionType {
        /**
         * 执行python脚本报错
         */
        EXEC_PYTHON_PROCESS_ERROR(ExceptionCodeLevel.JPYTHON.getValue() + 1, "java调用执行python脚本报错"),

        /**
         * 执行python脚本报错
         */
        EXEC_PYTHON_PROCESS_IO_ERROR(ExceptionCodeLevel.JPYTHON.getValue() + 2, "java调用执行python脚本IO报错"),


        /**
         * 执行python脚本报错
         */
        EXEC_PYTHON_ERROR(ExceptionCodeLevel.JPYTHON.getValue() + 3, "python脚本执行报错"),
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
