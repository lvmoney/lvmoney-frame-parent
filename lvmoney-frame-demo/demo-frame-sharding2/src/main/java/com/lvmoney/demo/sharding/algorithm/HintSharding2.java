package com.lvmoney.demo.sharding.algorithm;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.algorithm
 * 版本信息: 版本1.0
 * 日期:2020/10/27
 * Copyright XXXXXX科技有限公司
 */


import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0
 * 2020/10/27 10:22
 */
public class HintSharding2 implements HintShardingAlgorithm<String> {
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

