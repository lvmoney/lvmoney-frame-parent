package com.lvmoney.frame.expression.tokenizer;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.tokenizer
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class ParseException extends Exception {
    private static final long serialVersionUID = 1140772718463095285L;
    private List<ParseError> errors;

    /**
     * Creates a new exception based on the list of errors.
     *
     * @param errors the errors which occurred while processing the user input
     * @return a new ParseException which can be thrown
     */
    public static ParseException create(List<ParseError> errors) {
        if (errors.size() == 1) {
            return new ParseException(errors.get(0).getMessage(), errors);
        } else if (errors.size() > 1) {
            return new ParseException(String.format("%d errors occured. First: %s", errors.size(), errors.get(0).getMessage()), errors);
        } else {
            return new ParseException("An unknown error occured", errors);
        }
    }

    private ParseException(String message, List<ParseError> errors) {
        super(message);
        this.errors = errors;
    }

    /**
     * Provides a list of all errors and warnings which occurred
     *
     * @return all errors and warnings which occurred while processing the user input
     */
    public List<ParseError> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ParseError error : errors) {
            if (sb.length() > 0) {
                sb.append(BaseConstant.PLACEHOLDER_WARP_SPACE);
            }
            sb.append(error);
        }

        return sb.toString();
    }
}
