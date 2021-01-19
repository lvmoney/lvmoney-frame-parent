package com.lvmoney.frame.db.sharding.util.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.ro
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.db.sharding.util.ro.item.DbTenantRelRoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 17:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DbTenantRelRo implements Serializable {
    /**
     * 属于哪个库。例如：是 “user”类库的分库分表
     */
    private String group;
    /**
     * 租户库表对应关系，key是租户id，value是多对多的库表关系
     */
    private Map<String, DbTenantRelRoItem> rel;
    /**
     * 过期时间
     */
    private Long expired;
}
