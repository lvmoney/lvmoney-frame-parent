package com.zhy.frame.db.sharding.base.algorithm;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.algorithm
 * 版本信息: 版本1.0
 * 日期:2019/11/21
 * Copyright XXXXXX科技有限公司
 */


import lombok.NoArgsConstructor;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/21 16:46
 */
@NoArgsConstructor
@Service
public class HintStrategy implements HintShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection availableTargetNames, HintShardingValue shardingValue) {
        Collection shardingValueList = shardingValue.getValues();
        return shardingValueList;
    }
}
