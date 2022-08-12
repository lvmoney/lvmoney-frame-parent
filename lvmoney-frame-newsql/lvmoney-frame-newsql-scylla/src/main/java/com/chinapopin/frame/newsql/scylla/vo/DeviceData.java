package com.lvmoney.frame.newsql.scylla.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.nosql.scylla.vo
 * 版本信息: 版本1.0
 * 日期:2020/4/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/16 10:27
 */

@Table("device_data")
@Data
public class DeviceData {
    /**
     * 存储主键
     */
    @PrimaryKey
    private String id;

    @Column(value = "device_id")
    private Long deviceId;
    @Column(value = "property")
    private String property;

    @Column(value = "value")
    private String value;

}
