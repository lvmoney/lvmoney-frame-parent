package com.zhy.demo.db.application;/**
 * 描述:
 * 包名:com.zhy.platform.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@MapperScan("com.zhy.demo.db.dao")
public class MysqlDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(MysqlDemoApplication.class, args);
    }

}
