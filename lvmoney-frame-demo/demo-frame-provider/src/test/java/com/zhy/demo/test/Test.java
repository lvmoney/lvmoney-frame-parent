package com.lvmoney.demo.test;/**
 * 描述:
 * 包名:com.lvmoney.demo.test
 * 版本信息: 版本1.0
 * 日期:2020/3/6
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/6 10:28
 */
public class Test {
    public static void main(String[] args) {
        String url = "http://localhost:9090";
        String ip = "10.10.10.28";
        url = url.replace("localhost", ip);
        System.out.println(url);
    }
}
