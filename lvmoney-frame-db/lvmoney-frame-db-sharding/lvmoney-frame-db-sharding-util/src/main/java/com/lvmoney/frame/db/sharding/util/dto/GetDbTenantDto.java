package com.lvmoney.frame.db.sharding.util.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.dto
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
 * @version:v1.0 2020/10/27 16:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDbTenantDto implements Serializable {
    private static final long serialVersionUID = -8905905826417092445L;
    /**
     * 租户列表
     */
    private String tenantId;
    /**
     * 库分类
     */
    private String group;

    /**
     * 分类。例如：绵阳的数据存绵阳库，成都的数据存成都数据库。主要用在 一致性hash 对于租户和库表的时候。例如，成都租户的数据只落到成都库
     */
    private String classify;

}
