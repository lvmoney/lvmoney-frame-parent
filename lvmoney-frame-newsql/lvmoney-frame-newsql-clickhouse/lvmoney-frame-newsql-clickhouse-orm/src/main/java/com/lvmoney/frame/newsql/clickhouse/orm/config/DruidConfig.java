package com.lvmoney.frame.newsql.clickhouse.orm.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.orm.config
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.lvmoney.frame.newsql.clickhouse.orm.prop.ClickHouseProp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:10
 */
@Configuration
public class DruidConfig {
    @Resource
    private ClickHouseProp clickHouseProp;

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(clickHouseProp.getUrl());
        dataSource.setDriverClassName(clickHouseProp.getDriverClassName());
        dataSource.setInitialSize(clickHouseProp.getInitialSize());
        dataSource.setMinIdle(clickHouseProp.getMinIdle());
        dataSource.setMaxActive(clickHouseProp.getMaxActive());
        dataSource.setMaxWait(clickHouseProp.getMaxWait());
        return dataSource;
    }
}
