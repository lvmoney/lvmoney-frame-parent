package com.lvmoney.frame.newsql.clickhouse.base.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.base.vo
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 16:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVo<T> implements Serializable {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 数据
     */
    private T data;
    /**
     * 数据库名称
     */
    private String database;
}
