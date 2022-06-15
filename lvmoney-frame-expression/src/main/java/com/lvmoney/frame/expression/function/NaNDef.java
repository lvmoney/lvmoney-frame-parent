package com.lvmoney.frame.expression.function;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.function
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.util.DoubleUtil;
import com.lvmoney.frame.expression.annotations.MathFunction;
import com.lvmoney.frame.expression.common.MultiFunction;
import com.lvmoney.frame.expression.eval.Expression;

import java.util.List;

/**
 * @describe：接收2个参数，如果第一个参数值是错误数字（NaN、无限）返回第二个参数，否则返回第一个参数
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
@MathFunction(name = "NaNDef")
public class NaNDef extends MultiFunction {
    @Override
    public double eval(List<Expression> args) {
        return nanDef(getDouble(args.get(0)), getDouble(args.get(1)));
    }

    public static double nanDef(double v1, double v2) {
        if (DoubleUtil.isErrNumber(v1)) {
            return v2;
        }
        return v1;
    }


}
