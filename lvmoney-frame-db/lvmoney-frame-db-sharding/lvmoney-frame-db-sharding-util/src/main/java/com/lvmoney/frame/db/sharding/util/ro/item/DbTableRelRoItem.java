package com.lvmoney.frame.db.sharding.util.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.ro.item
 * 版本信息: 版本1.0
 * 日期:2020/10/27
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/27 13:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbTableRelRoItem implements Serializable {
    private static final long serialVersionUID = -8960611620112529213L;
    /**
     * 数据库
     */
    private String db;
    /**
     * 数据库表
     */
    private List<String> table;


}
