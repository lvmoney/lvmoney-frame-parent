package com.lvmoney.frame.newsql.clickhouse.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.newsql.clickhouse.base.datasource.ClickhouseDataSource;
import com.lvmoney.frame.newsql.clickhouse.base.service.ClickhouseBaseService;
import com.lvmoney.frame.newsql.clickhouse.base.vo.BaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.clickhouse.domain.ClickHouseFormat;
import ru.yandex.clickhouse.settings.ClickHouseQueryParam;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import static com.lvmoney.frame.newsql.clickhouse.base.constant.ClickHouseBaseConstant.MAX_PARALLEL_REPLICAS_VALUE;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 16:39
 */
@Service
public class ClickhouseBaseServiceImpl implements ClickhouseBaseService {
    @Autowired
    ClickhouseDataSource clickhouseDataSource;

    @Override
    public void batchSave(BaseVo baseVo) {
        String inputString = JsonUtil.t2JsonString(baseVo.getData());
        try {
            clickhouseDataSource.getSth().write().table(String.format(" %s.%s", baseVo.getDatabase(), baseVo.getTableName())).data(new ByteArrayInputStream(inputString.getBytes()), ClickHouseFormat.JSONEachRow).addDbParam(ClickHouseQueryParam.MAX_PARALLEL_REPLICAS, MAX_PARALLEL_REPLICAS_VALUE).send();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
