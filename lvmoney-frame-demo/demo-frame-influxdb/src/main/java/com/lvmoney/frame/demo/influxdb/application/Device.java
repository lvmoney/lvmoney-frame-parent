package com.lvmoney.frame.demo.influxdb.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.influxdb.application
 * 版本信息: 版本1.0
 * 日期:2022/1/14
 * Copyright XXXXXX科技有限公司
 */
import lombok.Data;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;
import plus.ojbk.influxdb.annotation.Count;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/14 15:01
 */
@Data
@Measurement(name = "device")
public class Device {
    /**
     * 设备编号
     */
    @Column(name = "device_no", tag = true)  //tag 可以理解为influxdb的索引
    private String deviceNo;
    /**
     * 数据值
     */
    @Count("value")
    @Column(name = "value")
    private BigDecimal value;
    /**
     * 电压
     */
    @Column(name = "voltage")
    private Float voltage;
    /**
     * 状态
     */
    @Column(name = "state")
    private Boolean state;
    /**
     * 上报时间
     */
    @Column(name = "time")
    private LocalDateTime time;

}



