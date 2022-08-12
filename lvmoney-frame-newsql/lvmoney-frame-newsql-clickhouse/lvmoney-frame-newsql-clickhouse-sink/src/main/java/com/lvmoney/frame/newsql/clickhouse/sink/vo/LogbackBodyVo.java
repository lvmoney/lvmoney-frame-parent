package com.lvmoney.frame.newsql.clickhouse.sink.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/30 19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogbackBodyVo implements Serializable {
    /**
     * id
     */
    private String id;
    /**
     * 系统名称
     */
    @JSONField(name = "sys_name")
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
    @JSONField(name = "exe_date")
    private String exeDate;

    /**
     * 创建时间
     */
    @JSONField(name = "create_date")
    private String createDate;

}
