package com.zhy.frame.demo.clickhouse.entity;/**
 * 描述:
 * 包名:com.zhy.frame.demo.clickhouse.entity
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:28
 */
@TableName("test_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestTableEntity {

    private Long id;

    private String name;

    private String value;

    private Date createDate;
}
