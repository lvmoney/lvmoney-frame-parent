/**
 * 描述:
 * 包名:com.lvmoney.pay.factory
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  上午11:07:01
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.sync.canal.common.spring;

import com.lvmoney.frame.sync.canal.common.listener.DataHandEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：调用不同数据处理策略模式环境角色类
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月18日 上午11:07:01
 */
@Component
public class HandListenerContext {
    @Autowired
    private Map<String, DataHandEventListener> strategyMap = new ConcurrentHashMap<>();

    /**
     * @describe:策略注入
     * @author: lvmoney /成都三合力通科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> HandListenerContext(Map<String, DataHandEventListener> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));
    }

    public Map<String, DataHandEventListener> getStrategyMap() {
        return strategyMap;
    }

}
