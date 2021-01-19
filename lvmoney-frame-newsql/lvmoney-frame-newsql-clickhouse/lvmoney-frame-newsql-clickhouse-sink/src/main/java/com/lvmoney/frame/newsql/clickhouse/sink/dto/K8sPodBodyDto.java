package com.lvmoney.frame.newsql.clickhouse.sink.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.dto
 * 版本信息: 版本1.0
 * 日期:2021/1/13
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/1/13 9:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class K8sPodBodyDto implements Serializable {
    private String log;
    private String stream;
    private String time;
}
