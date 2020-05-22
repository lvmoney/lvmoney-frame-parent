package com.zhy.cache.test;/**
 * 描述:
 * 包名:com.zhy.cache.test
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 10:48
 */
public class SwitchTest {
    public static void main(String[] args) {
        String action = "set";
        switch (action) {
            case "set":
                System.out.println("set");
                break;
            case "expired":
                System.out.println("expired");
                break;
            case "del":
                System.out.println("del");
                break;
            case "rename":
                System.out.println("rename");
                break;
            default:
                System.out.println("other");
                break;
        }
    }
}
