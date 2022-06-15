package com.lvmoney.frame.ai.jpython.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.test
 * 版本信息: 版本1.0
 * 日期:2022/5/13
 * Copyright XXXXXX科技有限公司
 */


import java.util.Arrays;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/13 16:37
 */
public class ArrayTest {
    public static void main(String[] args) {
        String[] str1 = {"J", "a", "v", "a", "中"};
        String[] str2 = {"如", "何", "把", "两", "个", "数", "组", "合", "并", "为", "一", "个"};
        int strLen1 = str1.length;//保存第一个数组长度
        int strLen2 = str2.length;//保存第二个数组长度
        str1 = Arrays.copyOf(str1, strLen1 + strLen2);//扩容
        System.arraycopy(str2, 0, str1, strLen1, strLen2);//将第二个数组与第一个数组合并
        System.out.println(Arrays.toString(str1));//输出数组
    }
}
