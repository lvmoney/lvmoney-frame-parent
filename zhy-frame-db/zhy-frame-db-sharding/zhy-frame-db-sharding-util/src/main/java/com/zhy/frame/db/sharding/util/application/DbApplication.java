package com.zhy.frame.db.sharding.util.application;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.util.application
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 16:33
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})

public class DbApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbApplication.class, args);
    }
}
