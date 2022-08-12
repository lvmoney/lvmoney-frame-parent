package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class VariableReference extends Expression implements IValue {

    private static final long serialVersionUID = -6597902286788822085L;
    private Variable var;

    /**
     * Creates a new reference to the given variable.
     *
     * @param var the variable to access when this expression is evaluated
     */
    public VariableReference(Variable var) {
        this.var = var;
    }

    @Override
    public double evaluate() {
        try {
            return Double.parseDouble(var.getValue());
        } catch (Exception e) {
            return Double.NaN;
        }
    }

    @Override
    public String value() {
        return var.getValue();
    }

    @Override
    public boolean isNumber() {
        return var.isNumber();
    }

    @Override
    public String toString() {
        return var.getName();
    }

    @Override
    public boolean isConstant() {
        return var.isConstant();
    }

    @Override
    public Expression simplify() {
        if (isConstant()) {
            return new Constant(evaluate());
        }
        return this;
    }
}
