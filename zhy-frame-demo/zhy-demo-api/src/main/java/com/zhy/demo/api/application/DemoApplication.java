package com.zhy.demo.api.application;/**
 * 描述:
 * 包名:com.zhy.tmc.api.application
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 16:51
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
