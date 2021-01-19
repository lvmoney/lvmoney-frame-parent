package com.lvmoney.frame.log.logback.clickhouse.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.log.logback.clickhouse.dto
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.log.logback.clickhouse.dto.item.SysLogDtoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 9:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLogDto implements Serializable {
    /**
     * 表名
     */
    private String tableName;
    /**
     * 数据库数据
     */
    private List<SysLogDtoItem> sysLogDtoItems;

}
