package com.lvmoney.demo.sharding.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.application
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 11:05
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@MapperScan("com.lvmoney.demo.sharding.dao*")
public class ShardingDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShardingDemoApplication.class, args);
    }
}
