package com.lvmoney.frame.expression.parser;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.parser
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.expression.eval.Constant;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class ConstantExpr extends Constant {

    double calculateValue;
    boolean exclude;

    public ConstantExpr(double value) {
        this(value, false);
    }

    public ConstantExpr(double value, boolean exclude) {
        super(value);
        if (exclude) {
            this.calculateValue = 0;
        }
        this.exclude = exclude;
    }

    @Override
    public double evaluate() {
        if (exclude) {
            return this.calculateValue;
        }
        return super.evaluate();
    }

    @Override
    public String toString() {
        if (evaluate() < 0) {
            return BaseConstant.BRACKETS_LEFT + evaluate() + BaseConstant.BRACKETS_RIGHT;
        }
        return super.toString();
    }
}
