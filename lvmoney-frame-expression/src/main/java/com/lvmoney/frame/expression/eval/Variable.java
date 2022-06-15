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
public class Variable implements IValue {
    /**
     * 数字的正则表达式
     */
    private static final String NUMBER_FORMAT = "-?[0-9]+.*[0-9]*";

    /**
     * 值
     */
    private String value = "";
    /**
     * 名称
     */
    private String name;
    /**
     * 是否常量
     */
    private boolean constant = false;

    /**
     * Creates a new variable.
     * <p>
     * Variables should only be created by their surrounding {@link Scope} so that all following look-ups
     * yield the same variable.
     *
     * @param name the name of the variable
     */
    protected Variable(String name) {
        this.name = name;
    }

    /**
     * Sets the value if the variable.
     *
     * @param value the new value of the variable
     * @throws IllegalStateException if the variable is constant
     */
    public void setValue(String value) {
        if (constant) {
            throw new IllegalStateException(String.format("%s is constant!", name));
        }
        if (BaseConstant.SUPPORT_TRUE.equals(value)) {
            this.value = "1";
        } else if (BaseConstant.SUPPORT_FALSE.equals(value)) {
            this.value = "0";
        } else {
            this.value = value;
        }
    }

    public void setValue(double value) {
        setValue(String.valueOf(value));
    }

    /**
     * Sets the given value and marks it as constant.
     *
     * @param value the new (and final) value of this variable
     */
    public void makeConstant(String value) {
        setValue(value);
        this.constant = true;
    }

    public void makeConstant(double value) {
        makeConstant(String.valueOf(value));
    }

    /**
     * Returns the value previously set.
     *
     * @return the value previously set or 0 if the variable is not written yet
     */
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return name + BaseConstant.COLON + BaseConstant.PLACEHOLDER_BLANK_SPACE + value;
    }

    /**
     * Returns the name of the variable.
     *
     * @return the name of this variable
     */
    public String getName() {
        return name;
    }

    /**
     * Determines if this variable is constant.
     *
     * @return <tt>true</tt> if this variable cannot be modified anymore, <tt>false</tt> otherwise
     */
    public boolean isConstant() {
        return constant;
    }

    /**
     * Sets the value and returns <tt>this</tt>.
     *
     * @param value the new value of this variable
     * @return <tt>this</tt> for fluent method calls
     */
    public Variable withValue(String value) {
        setValue(value);
        return this;
    }

    public Variable withValue(double value) {
        return withValue(String.valueOf(value));
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public boolean isNumber() {
        return value != null && value.matches(NUMBER_FORMAT);
    }
}
