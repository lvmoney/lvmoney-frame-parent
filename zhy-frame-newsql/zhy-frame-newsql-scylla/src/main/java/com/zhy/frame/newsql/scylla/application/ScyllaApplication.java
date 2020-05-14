package com.zhy.frame.newsql.scylla.application;/**
 * 描述:
 * 包名:com.zhy.frame.nosql.scylla.application
 * 版本信息: 版本1.0
 * 日期:2020/4/16
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/16 10:38
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@EnableCassandraRepositories("com.zhy.frame.newsql.scylla.dao")

public class ScyllaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScyllaApplication.class, args);
    }
}
