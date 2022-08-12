package com.lvmoney.frame.expression.exception;/**
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
public interface ExpressionException {


    enum Proxy implements ExceptionType {
        /**
         * 方法执行报错
         */
        FUNCTION_EVAL_ERROR(ExceptionCodeLevel.EXPRESSION.getValue() + 1, "方法执行报错"),

        /**
         * calc执行报错
         */
        CALC_ERROR(ExceptionCodeLevel.EXPRESSION.getValue() + 2, "算术表达式执行报错"),
        /**
         * 表达式解析失败
         */
        PARSE_EXPR_FAIL(ExceptionCodeLevel.EXPRESSION.getValue() + 3, "表达式解析失败"),
        /**
         * 表达式解析报错
         */
        PARSE_EXPRESS_ERROR(ExceptionCodeLevel.EXPRESSION.getValue() + 4, "表达式解析报错"),

        /**
         * 方法注册报错
         */
        REGISTER_FUNCTION_ERROR(ExceptionCodeLevel.EXPRESSION.getValue() + 5, "方法注册报错"),

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
