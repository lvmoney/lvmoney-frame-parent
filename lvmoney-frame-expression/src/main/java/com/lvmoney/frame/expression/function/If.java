package com.lvmoney.frame.expression.function;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.function
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.expression.annotations.MathFunction;
import com.lvmoney.frame.expression.common.MultiFunction;
import com.lvmoney.frame.expression.eval.Expression;

import java.util.List;

/**
 * @describe：if函数,支持if else if ...   else
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
@MathFunction(name = "if")
public class If extends MultiFunction {
    @Override
    public double eval(List<Expression> args) {
        //参数是单数
        boolean hasElse = args.size() % 2 == 1;
        double elseVal = hasElse ? getDouble(args.get(args.size() - 1)) : 0;

        int caseCount = hasElse ? args.size() - 1 : args.size();

        for (int i = 0; i < caseCount; i += 2) {
            double condition = getDouble(args.get(i));
            if (condition > 0.0d) {
                return getDouble(args.get(i + 1));
            }
        }
        return elseVal;
    }
}
