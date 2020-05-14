package com.zhy.frame.db.sharding.base.algorithm;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.algorithm
 * 版本信息: 版本1.0
 * 日期:2020/1/11
 * Copyright XXXXXX科技有限公司
 */


import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/11 10:46
 */
public class DbHintSharding1 implements HintShardingAlgorithm<String> {
    /**
     * 通过算法定位到数据库
     *
     * @param availableTargetNames:
     * @param hintShardingValue:
     * @throws
     * @return: java.util.Collection<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/1/8 10:47
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, HintShardingValue<String> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : availableTargetNames) {
            result.add(each);
        }
        return result;
    }
}
