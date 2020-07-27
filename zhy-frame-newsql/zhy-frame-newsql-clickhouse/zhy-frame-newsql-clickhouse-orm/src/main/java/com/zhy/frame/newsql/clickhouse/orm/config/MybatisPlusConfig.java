package com.zhy.frame.newsql.clickhouse.orm.config;/**
 * 描述:
 * 包名:com.zhy.frame.newsql.clickhouse.orm.config
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */

import java.util.Properties;

import org.springframework.context.annotation.Bean;

import com.github.pagehelper.PageHelper;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:14
 */
public class MybatisPlusConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
