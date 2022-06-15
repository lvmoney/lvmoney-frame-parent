package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public abstract class Expression implements Serializable {

    private static final long serialVersionUID = -6078443078086081582L;

    private Scope scope;

    /**
     * Evaluates the expression to a double number.
     *
     * @return the double value as a result of evaluating this expression. Returns NaN if an error occurs
     */
    public abstract double evaluate();

    /**
     * Returns a simplified version of this expression.
     *
     * @return a simplified version of this expression or <tt>this</tt> if the expression cannot be simplified
     */
    public Expression simplify() {
        return this;
    }

    /**
     * Determines the this expression is constant
     *
     * @return <tt>true</tt> if the result of evaluate will never change and does not depend on external state like
     * variables
     */
    public boolean isConstant() {
        return false;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public Scope getScope() {
        return scope;
    }

}
