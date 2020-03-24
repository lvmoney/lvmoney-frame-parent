package com.zhy.frame.cloud.base.application;/**
 * 描述:
 * 包名:com.zhy.customer.application
 * 版本信息: 版本1.0
 * 日期:2019/8/9
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/9 9:48
 * @SpringBootApplication(scanBasePackages = {"com.zhy.**"})
 */
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class, args);
    }
}
