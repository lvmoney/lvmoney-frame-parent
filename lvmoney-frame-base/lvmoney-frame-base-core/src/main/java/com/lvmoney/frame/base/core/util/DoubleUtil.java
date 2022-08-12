package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.util
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class DoubleUtil {
    /**
     * 判断参数是不是错误浮点数
     *
     * @param v:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/13 9:36
     */
    public static boolean isErrNumber(double v) {
        return Double.isNaN(v) || Double.isInfinite(v);
    }
}
