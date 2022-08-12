package com.lvmoney.frame.expression.parser;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.parser
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.expression.eval.Expression;
import com.lvmoney.frame.expression.eval.Parser;
import com.lvmoney.frame.expression.eval.Scope;
import com.lvmoney.frame.expression.tokenizer.ParseException;

import java.io.Reader;

import static com.lvmoney.frame.base.core.constant.BaseConstant.*;


/**
 * @describe：*解决表达式如：x = x*0.2 + x*0.11 + 8.87 时，计算不对的问题，思路是将"="号右边的表达式移到左边，
 * 且"="号右边为：0",如上述表达式转义后为：(x)-(x*0.2 + x*0.11 + 8.87)=0，才能计算正确
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class EscapeParser extends Parser {

    protected EscapeParser(Reader input, Scope scope) {
        super(input, scope);
    }

    public static Expression parse(String input) throws ParseException {
        return Parser.parse(escapeExpression(input));
    }

    public static Expression parse(String input, Scope scope)
            throws ParseException {
        return Parser.parse(escapeExpression(input), scope);
    }

    public static String escapeExpression(String exp) {
        if (exp.contains(CHAR_EQUAL_SIGN)) {
            String[] arr = exp.split(CHAR_EQUAL_SIGN);
            String leftExp = arr[0], rightExp = arr[1];
            if (isNumber(leftExp) || isNumber(rightExp)) {
                return exp;
            }
            return BRACKETS_LEFT + leftExp + BRACKETS_RIGHT
                    + DASH_LINE + BRACKETS_LEFT + rightExp
                    + BRACKETS_RIGHT + CHAR_EQUAL_SIGN + ZERO_CHAR;
        }
        return exp;
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
