/**
 * 描述:
 * 包名:com.lvmoney.hotel.util
 * 版本信息: 版本1.0
 * 日期:2018年11月23日  上午10:17:17
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.core.util;


import com.lvmoney.frame.base.core.constant.BaseConstant;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年11月23日 上午10:17:17
 */

public class IntervalUtil {
    /**
     * 表示判断方向（如>）在前面 如：≥80%
     */
    private static final String MATCH = "^([<>≤≥\\[\\(]{1}(-?\\d+.?\\d*\\%?))$";

    /**
     * 判断data_value是否在interval区间范围内
     *
     * @param dataValue 数值类型的
     * @param interval  正常的数学区间，包括无穷大等，如：(1,3)、>5%、(-∞,6]、(125%,135%)U(70%,80%)
     * @throws
     * @return: boolean 表示data_value在区间interval范围内，false：表示data_value不在区间interval范围内
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:41
     */
    public static boolean isInTheInterval(String dataValue, String interval) {
        //将区间和data_value转化为可计算的表达式
        String formula = getFormulaByAllInterval(dataValue, interval, "||");
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            //计算表达式
            return (Boolean) jse.eval(formula);
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * 将所有阀值区间转化为公式：如
     * [75,80)   =》        date_value < 80 && date_value >= 75
     * (125%,135%)U(70%,80%)   =》        (date_value < 1.35 && date_value > 1.25) || (date_value < 0.8 && date_value > 0.7)
     *
     * @param dataValue
     * @param interval  形式如：(125%,135%)U(70%,80%)
     * @param connector 连接符 如：") || ("
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:42
     */
    private static String getFormulaByAllInterval(String dataValue, String interval, String connector) {
        StringBuffer buff = new StringBuffer();
        for (String limit : interval.split(BaseConstant.CHAR_U_UPPER)) {
            //如：（125%,135%）U (70%,80%)
            buff.append("(").append(getFormulaByInterval(dataValue, limit, " && ")).append(")").append(connector);
        }
        String allLimitInterval = buff.toString();
        int index = allLimitInterval.lastIndexOf(connector);
        allLimitInterval = allLimitInterval.substring(0, index);
        return allLimitInterval;
    }

    /**
     * 将整个阀值区间转化为公式：如
     * 145)      =》         date_value < 145
     * [75,80)   =》        date_value < 80 && date_value >= 75
     *
     * @param dataValue
     * @param interval  形式如：145)、[75,80)
     * @param connector 连接符 如：&&
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:43
     */
    private static String getFormulaByInterval(String dataValue, String interval, String connector) {
        StringBuffer buff = new StringBuffer();
        for (String halfInterval : interval.split(BaseConstant.CHAR_COMMA)) {
            //如：[75,80)、≥80
            buff.append(getFormulaByHalfInterval(halfInterval, dataValue)).append(connector);
        }
        String limitInvel = buff.toString();
        int index = limitInvel.lastIndexOf(connector);
        limitInvel = limitInvel.substring(0, index);
        return limitInvel;
    }

    /**
     * 将半个阀值区间转化为公式：如
     * 145)      =》         date_value < 145
     * ≥80%      =》         date_value >= 0.8
     * [130      =》         date_value >= 130
     * <80%     =》         date_value < 0.8
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:43
     */
    private static String getFormulaByHalfInterval(String halfInterval, String dataValue) {
        halfInterval = halfInterval.trim();
        if (halfInterval.contains(BaseConstant.CHAR_INFINITE)) {
            //包含无穷大则不需要公式
            return "1 == 1";
        }
        StringBuffer formula = new StringBuffer();
        String data = "";
        String opera = "";
        if (halfInterval.matches(MATCH)) {
            //表示判断方向（如>）在前面 如：≥80%
            opera = halfInterval.substring(0, 1);
            data = halfInterval.substring(1);
        } else {//[130、145)
            opera = halfInterval.substring(halfInterval.length() - 1);
            data = halfInterval.substring(0, halfInterval.length() - 1);
        }
        double value = dealPercent(data);
        formula.append(dataValue).append(" ").append(opera).append(" ").append(value);
        String a = formula.toString();
        //转化特定字符
        return a.replace("[", ">=").replace("(", ">").replace("]", "<=").replace(")", "<").replace("≤", "<=").replace("≥", ">=");
    }

    /**
     * 去除百分号，转为小数
     *
     * @param str 可能含百分号的数字
     * @throws
     * @return: double
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 14:43
     */
    private static double dealPercent(String str) {
        double d = 0.0;
        if (str.contains(BaseConstant.CHAR_PERCENT)) {
            str = str.substring(0, str.length() - 1);
            d = Double.parseDouble(str) / 100;
        } else {
            d = Double.parseDouble(str);
        }
        return d;
    }

    public static void main(String[] args) {
        System.out.println(IntervalUtil.isInTheInterval("7", "(-∞,10]"));
    }
}
