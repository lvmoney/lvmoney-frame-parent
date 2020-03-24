package com.zhy.frame.db.sharding.base.algorithm;

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
public class HintSharding implements HintShardingAlgorithm<Integer> {
    /**
     * @param availableTargetNames 分片表名的集合
     * @param hintShardingValue    分片键集合
     * @return java.util.Collection<java.lang.String>
     * @author hujy
     * @date 2019-09-22 12:23
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<Integer> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            for (Integer value : hintShardingValue.getValues()) {
                if (each.endsWith(String.valueOf(value % 2))) {
                    System.out.println("*********************");
                    result.add(each);
                }
            }
        }
        return result;
    }
}
