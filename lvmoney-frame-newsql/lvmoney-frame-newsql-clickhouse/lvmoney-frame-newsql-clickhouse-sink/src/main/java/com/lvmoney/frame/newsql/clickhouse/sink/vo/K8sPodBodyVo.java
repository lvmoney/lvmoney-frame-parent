package com.lvmoney.frame.newsql.clickhouse.sink.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.vo
 * 版本信息: 版本1.0
 * 日期:2021/1/12
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
 * @version:v1.0 2021/1/12 18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class K8sPodBodyVo implements Serializable {
    /**
     * id
     */
    private String id;

    /**
     * 执行时间
     */
    private LocalDateTime exeDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
    /**
     * 信息
     */
    private String msg;

    /**
     * 日志类型
     */
    private String level;


    /**
     * 系统名称
     */
    private String sysName;



}
