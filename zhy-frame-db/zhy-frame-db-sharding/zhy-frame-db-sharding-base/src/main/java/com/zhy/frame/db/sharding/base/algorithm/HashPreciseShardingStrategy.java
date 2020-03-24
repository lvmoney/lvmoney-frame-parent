package com.zhy.frame.db.sharding.base.algorithm;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.algorithm
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.shardingsphere.api.config.sharding.strategy.HintShardingStrategyConfiguration;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.TreeSet;

/**
 * @describe：自定义分表策略
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 14:00
 */
@NoArgsConstructor
@Service
public class HashPreciseShardingStrategy implements HintShardingAlgorithm<String> {
    //支持多分片键
    @Getter
    Collection<String> shardingColumns;

    HintShardingAlgorithm shardingAlgorithm;

    public HashPreciseShardingStrategy(final HintShardingStrategyConfiguration hintShardingStrategyConfig) {
        Preconditions.checkNotNull(hintShardingStrategyConfig.getShardingAlgorithm(), "Sharding algorithm cannot be null.");
        shardingColumns = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        shardingAlgorithm = hintShardingStrategyConfig.getShardingAlgorithm();
    }

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> shardingValue) {
        System.out.println("test");
        return null;
    }


}
