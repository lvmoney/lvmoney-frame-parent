package com.zhy.frame.newsql.clickhouse.orm.prop;/**
 * 描述:
 * 包名:com.zhy.frame.newsql.clickhouse.orm.prop
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:13
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.clickhouse")
public class ClickHouseProp {
    private String driverClassName;

    private String url;

    private Integer initialSize;

    private Integer maxActive;

    private Integer minIdle;

    private Integer maxWait;
}
