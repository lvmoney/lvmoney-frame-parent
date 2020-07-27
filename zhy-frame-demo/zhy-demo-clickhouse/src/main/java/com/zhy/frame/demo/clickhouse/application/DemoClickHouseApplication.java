package com.zhy.frame.demo.clickhouse.application;/**
 * 描述:
 * 包名:com.zhy.frame.demo.clickhouse.application
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:27
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@MapperScan("com.zhy.frame.demo.clickhouse.dao")
public class DemoClickHouseApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoClickHouseApplication.class, args);
    }
}
