package com.zhy.frame.db.sharding.base.ro;/**
 * 描述:
 * 包名:com.zhy.frame.db.sharding.base.ro
 * 版本信息: 版本1.0
 * 日期:2020/3/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/18 9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShardingRuleConfigurationRo implements Serializable {
    private Map<String, ShardingRuleConfiguration> shardingRuleConfiguration;
    private Long expired;
}
