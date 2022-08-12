package com.lvmoney.frame.sync.uniformity.db.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Description
 *
 * @author hujy
 * @version 1.0
 * @date 2019-09-19 19:25
 */
public class HintSharding implements HintShardingAlgorithm<String> {
    /**
     * @param availableTargetNames 分片表名的集合
     * @param hintShardingValue    分片键集合
     * @return java.util.Collection<java.lang.String>
     * @author hujy
     * @date 2019-09-22 12:23
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> hintShardingValue) {
        Collection<String> result = new ArrayList<>();

        for (String value : hintShardingValue.getValues()) {
            result.add(value);
        }
        return result;
    }
}
