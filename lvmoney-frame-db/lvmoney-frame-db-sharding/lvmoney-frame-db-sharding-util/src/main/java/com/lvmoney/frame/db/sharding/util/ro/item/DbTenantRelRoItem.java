package com.lvmoney.frame.db.sharding.util.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.ro.item
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 18:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbTenantRelRoItem implements Serializable {
    private static final long serialVersionUID = 4458664634224393798L;
    /**
     * 数据库名称
     */
    private String db;
    /**
     * 表名称
     */
    private String table;
}
