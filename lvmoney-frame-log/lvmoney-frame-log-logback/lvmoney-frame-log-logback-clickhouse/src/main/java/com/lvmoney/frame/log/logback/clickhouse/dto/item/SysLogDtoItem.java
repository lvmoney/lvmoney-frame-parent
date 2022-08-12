package com.lvmoney.frame.log.logback.clickhouse.dto.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.log.logback.clickhouse.entity
 * 版本信息: 版本1.0
 * 日期:2020/7/27
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/27 14:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SysLogDtoItem implements Serializable {
    private static final long serialVersionUID = -3422331509247492181L;
    /**
     * id
     */
    private String id;
    /**
     * ip
     */
    private String ip;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 日志类型
     */
    private String level;
    /**
     * 信息
     */
    private String msg;

    /**
     * 线程
     */
    private String thread;

    /**
     * 执行时间
     */
    private LocalDateTime exeDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}
