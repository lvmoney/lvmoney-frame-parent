package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class Constant extends Expression implements IValue {
    /**
     * 值
     */
    private String value;
    /**
     * 是不是数字
     */
    boolean isNumber = false;
    /**
     * Used as dummy expression by the parser if an error occurs while parsing.
     */
    public static final Constant EMPTY = new Constant("");

    public Constant(String value) {
        if (value.startsWith(BaseConstant.SINGLE_QUOTATION_MARK) && value.endsWith(BaseConstant.SINGLE_QUOTATION_MARK) || value.startsWith(BaseConstant.DOUBLE_QUOTATION_MARKS) && value.endsWith(BaseConstant.DOUBLE_QUOTATION_MARKS)) {
            this.value = value.substring(1, value.length() - 1);
        }
        this.value = value;
    }

    public Constant(double value) {
        isNumber = true;
        this.value = String.valueOf(value);
    }

    @Override
    public double evaluate() {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean isNumber() {
        return isNumber;
    }

    @Override
    public boolean isConstant() {
        return true;
    }

    @Override
    public String toString() {
        return value;
    }
}
