package com.lvmoney.frame.expression.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.test
 * 版本信息: 版本1.0
 * 日期:2022/5/30
 * Copyright XXXXXX科技有限公司
 */


import parsii.eval.Expression;
import parsii.eval.Parser;
import parsii.eval.Scope;
import parsii.eval.Variable;
import parsii.tokenizer.ParseException;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class Calculate {
    public static void main(String[] args) throws ParseException {
        String exp = "2+(7-5)+3.14159*x+sin(0)";
        Scope scope = new Scope();
        Expression parsiiExpr = Parser.parse(exp);
        Variable var = scope.getVariable("x");
        var.setValue(0.0d);
        double result = parsiiExpr.evaluate();
        System.out.println(result);
    }
}
