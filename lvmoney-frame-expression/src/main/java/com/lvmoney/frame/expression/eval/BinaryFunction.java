package com.lvmoney.frame.expression.eval;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.eval
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public abstract class BinaryFunction implements Function {

    @Override
    public int getNumberOfArguments() {
        return 2;
    }

    @Override
    public double eval(List<Expression> args) {
        double a = args.get(0).evaluate();
        if (Double.isNaN(a)) {
            return a;
        }
        double b = args.get(1).evaluate();
        if (Double.isNaN(b)) {
            return b;
        }
        return eval(a, b);
    }

    /**
     * Performs the computation of the binary function
     *
     * @param a the first argument of the function
     * @param b the second argument of the function
     * @return the result of calling the function with a and b
     */
    protected abstract double eval(double a, double b);

    @Override
    public boolean isNaturalFunction() {
        return true;
    }
}
