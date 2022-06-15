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
public interface IValue {
    /**
     * 值
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/13 15:43
     */
    String value();

    /**
     * 是不是数字
     *
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/13 15:43
     */
    boolean isNumber();
}
