package com.lvmoney.frame.db.sharding.util.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.ro
 * 版本信息: 版本1.0
 * 日期:2020/10/27
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.db.sharding.util.ro.item.DbTableRelRoItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/27 13:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbTableRelRo implements Serializable {
    /**
     * 所有数据库表。key值=classify_group。value为具体的库名和对应的表列表
     */
    private Map<String, List<DbTableRelRoItem>> data;
    /**
     * 失效时间
     */
    private Long expired;
}
