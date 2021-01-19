package com.lvmoney.frame.newsql.clickhouse.base.datasource;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.base.datasource
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.newsql.clickhouse.base.prop.DataSourceProp;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.ClickHouseStatement;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import javax.annotation.PostConstruct;
import java.sql.SQLException;

import static com.lvmoney.frame.newsql.clickhouse.base.constant.ClickHouseBaseConstant.CLICK_HOUSE_PREFIX;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 16:22
 */
@Service
public class ClickhouseDataSource {

    @Autowired
    DataSourceProp dataSourceProp;
    @Getter
    private BalancedClickhouseDataSource dataSource;
    @Getter
    private ClickHouseConnectionImpl conn;
    @Getter
    private ClickHouseStatement sth;

    @PostConstruct
    private void init() {

        String host = "";
        if (!dataSourceProp.getHost().startsWith(CLICK_HOUSE_PREFIX)) {
            host = CLICK_HOUSE_PREFIX + dataSourceProp.getHost();
        }
        String jdbcUrl = String.format("%s:%s/%s", host, dataSourceProp.getPort(), dataSourceProp.getDatabase());
        ClickHouseProperties properties = new ClickHouseProperties().withCredentials(dataSourceProp.getUser(), dataSourceProp.getPassword());
        //properties.setUseServerTimeZone(false);
        this.dataSource = new BalancedClickhouseDataSource(jdbcUrl, properties);
        try {
            this.conn = (ClickHouseConnectionImpl) dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            this.sth = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
