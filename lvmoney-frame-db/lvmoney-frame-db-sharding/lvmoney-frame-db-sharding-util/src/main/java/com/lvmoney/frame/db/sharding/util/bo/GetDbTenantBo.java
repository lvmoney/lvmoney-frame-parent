package com.lvmoney.frame.db.sharding.util.bo;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.bo
 * 版本信息: 版本1.0
 * 日期:2020/10/27
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/27 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetDbTenantBo implements Serializable {
    /**
     * 对应的库
     */
    private String db;
    /**
     * 对应的表
     */
    private String table;
    /**
     * 对应的租户id
     */
    private String tenantId;
}
