package com.lvmoney.frame.expression.common;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.common
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.expression.exception.ExpressionException;
import com.lvmoney.frame.expression.parser.BinaryExpr;
import com.lvmoney.frame.expression.parser.EscapeParser;
import com.lvmoney.frame.expression.eval.Expression;
import com.lvmoney.frame.expression.eval.Parser;
import com.lvmoney.frame.expression.eval.Scope;
import com.lvmoney.frame.expression.tokenizer.ParseException;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class CalcParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalcParser.class);

    static class SingletonHolder {
        static CalcParser calcParser = new CalcParser();
    }


    public static CalcParser getInstance() {
        return SingletonHolder.calcParser;
    }

    /**
     * 初始化
     *
     * @throws
     * @return: com.lvmoney.frame.expression.common.CalcParser
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/13 8:55
     */
    public static CalcParser g() {
        return getInstance();
    }

    public Set<String> parseVars(String exp, boolean errDefaultNull) {
        try {

            Expression expression = Parser.parse(exp, new Scope());
            return new TreeSet<>(expression.getScope().getLocalNames());
        } catch (ParseException e) {
            if (errDefaultNull) {
                return null;
            }
            LOGGER.error("{} calc error:{}", exp, e);
            throw new BusinessException(ExpressionException.Proxy.CALC_ERROR);
        }
    }

    public Set<String> parseVars(String exp) {
        return parseVars(exp, true);
    }

    public boolean boolValue(final String exp, final Map<String, String> param) {
        return boolValue(exp, param, false);
    }

    public double doubleValue(final String exp, final Map<String, Double> param) {
        return doubleValue(exp, param, Double.NaN, -1);
    }

    public double doubleValue(final String exp) {
        return doubleValue(exp, -1);
    }

    public double doubleValue(final String exp, final Map<String, Double> param, int scale) {
        return doubleValue(exp, param, Double.NaN, scale);
    }

    public double doubleValue(final String exp, int scale) {
        return doubleValue(exp, null, Double.NaN, scale);
    }

    public double doubleValue(final String exp, final Map<String, Double> param, final double defaultValue, int scale) {
        try {
            Expression expression = Parser.parse(exp, new Scope());
            if (param != null) {
                for (Map.Entry<String, Double> e : param.entrySet()) {
                    expression.getScope().getVariable(e.getKey()).setValue(e.getValue());
                }
            }
            double v = expression.evaluate();

            if (scale >= 0) {
                v = new BigDecimal(v).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
            }
            return v;

        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean boolValue(final String exp, final Map<String, String> param, final boolean defaultValue) {
        try {
            Expression expression = Parser.parse(exp, new Scope());
            if (param != null) {
                for (Map.Entry<String, String> e : param.entrySet()) {
                    expression.getScope().getVariable(e.getKey()).setValue(e.getValue());
                }
            }
            return expression.evaluate() > 0;
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public String testParse(final String exp) {
        try {
            Parser.parse(exp, new Scope());
            return null;
        } catch (ParseException e) {
            LOGGER.error("{} test pase error:[]", exp, e);
            return null;
        }
    }

    public static void main(String[] args) {
        Map<String, Double> r = ImmutableMap.of("a", 1d, "aa", 2d, "aaa", 3d);
        String a = "if(a<200,200,a>430,430,23)";
        // a = "x-5=6";

        double v = CalcParser.g().escapeValue(a, r);
        System.out.println(v);
    }

    public double escapeValue(String expr) {
        return escapeValue(expr, null);
    }

    public double escapeValue(String expr, Map<String, Double> params) {
        Scope scope = new Scope();

        expr = replaceExprVars(expr, params);

        Expression expression;
        try {
            expression = EscapeParser.parse(expr, scope);
        } catch (ParseException e) {
            LOGGER.error("{} Parse expr fail. expr:{}", expr, e);
            throw new BusinessException(ExpressionException.Proxy.PARSE_EXPR_FAIL);
        }
        BinaryExpr expr0 = new BinaryExpr(expression);
        return expr0.getValue();
    }

    public String replaceExprVars(String expr, Map<String, Double> params) {
        if (params == null) {
            return expr;
        }
        Set<String> vars = this.parseVars(expr);
        if (vars == null) {
            return expr;
        }
        List<String> list = vars.stream().sorted((v1, v2) -> (v1.length() < v2.length()) ? 1 : -1).collect(Collectors.toList());


        for (String s : list) {
            Double v = params.get(s);
            if (v == null) {
                continue;
            }
            expr = expr.replaceAll(s, v.toString());
        }
        return expr;
    }
}
