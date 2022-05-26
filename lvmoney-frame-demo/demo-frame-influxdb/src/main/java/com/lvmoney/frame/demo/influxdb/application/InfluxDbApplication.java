package com.lvmoney.frame.demo.influxdb.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.influxdb.application
 * 版本信息: 版本1.0
 * 日期:2022/1/14
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/14 14:53
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class InfluxDbApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfluxDbApplication.class, args);
    }
}
