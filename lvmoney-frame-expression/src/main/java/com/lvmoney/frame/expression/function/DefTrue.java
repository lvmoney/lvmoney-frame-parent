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
 * @describe：接收1个参数，如果第一个参数值是错误数字（NaN、无限）返回1
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
@MathFunction(name = "DefTrue")
public class DefTrue extends MultiFunction {
    @Override
    public double eval(List<Expression> args) {

        return NaNDef.nanDef(getDouble(args.get(0)), 1);
    }


}
