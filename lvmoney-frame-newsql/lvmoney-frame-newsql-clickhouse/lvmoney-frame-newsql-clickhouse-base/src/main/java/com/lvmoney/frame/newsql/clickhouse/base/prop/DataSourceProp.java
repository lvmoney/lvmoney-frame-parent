package com.lvmoney.frame.newsql.clickhouse.base.prop;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.base.prop
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 16:24
 */
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
public class DataSourceProp implements Serializable {
    private static final long serialVersionUID = 1287910649578172594L;
    @Value("${frame.db.clickhouse.host:localhost}")
    private String host;
    @Value("${frame.db.clickhouse.port:8123}")
    private String port;
    @Value("${frame.db.clickhouse.user:default}")
    private String user;
    @Value("${frame.db.clickhouse.password}")
    private String password;
    @Value("${frame.db.clickhouse.database:default}")
    private String database;

}
