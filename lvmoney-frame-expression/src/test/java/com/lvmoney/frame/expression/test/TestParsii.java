package com.lvmoney.frame.expression.test;

import com.lvmoney.frame.expression.common.CalcParser;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * @author ：LiuTQ
 * @description：f
 * @date ：Created in 2021/6/16 13:15
 */

public class TestParsii {

    public static void main(String[] a) {
//
//        Map<String,Double> param = ImmutableMap.of("p1",2.0,"p2",3.4);//传参
//        String expr = "DefTrue(0)";//表达式
//        double v = CalcParser.g().doubleValue(expr,param,2);//计算结果，最后一个参数是小数位数
//        System.out.println(v);

//        Map<String,Double> param = ImmutableMap.of("p1",2.0,"p2",3.0);//传参
//        String expr = "(p1+p2)*p1+(p1+p2)";//表达式
//        double v = CalcParser.g().doubleValue(expr,param,2);//计算结果，最后一个参数是小数位数
//        System.out.println(v);
//
        Map<String, Double> r = ImmutableMap.of("a", 1d, "aa", 2d, "aaa", 3d);
        String expr = "if(a<200,600,a>430,430,23)";
        // a = "x-5=6";

        double v = CalcParser.g().doubleValue(expr, r, 2);
        System.out.println(v);

    }
}
