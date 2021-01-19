package com.lvmoney.frame.cache.queue.factory;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.queue.factory
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.cache.common.exception.CacheException;
import com.lvmoney.frame.cache.queue.annotations.QueueImpl;
import com.lvmoney.frame.cache.queue.service.RedisDelayedQueueService;
import com.lvmoney.frame.cache.queue.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:46
 */
@Component
public class RedisDelayedQueueListenerContext {
    @Autowired
    private Map<String, RedisDelayedQueueService> strategyMap = new ConcurrentHashMap();

    /**
     * @describe:策略注入
     * @author: lvmoney /四川******科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> RedisDelayedQueueListenerContext(Map<String, RedisDelayedQueueService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    /**
     * @param implName
     * @param messageVo 2018年11月8日下午3:08:26
     * @describe:策略方法
     * @author: lvmoney /四川******科技有限公司
     */
    public void handData(String implName, MessageVo messageVo) {
        String beanName = getMqDataHandServiceBeanName(implName);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(CacheException.Proxy.REDIS_DELAY_QUEUE_DYNAMIC_ERROR);
        }
        strategyMap.get(beanName).handData(messageVo);
    }

    public Map<String, RedisDelayedQueueService> getStrategyMap() {
        return strategyMap;
    }

    private String getMqDataHandServiceBeanName(String mqType) {
        Map<String, RedisDelayedQueueService> map = this.getStrategyMap();
        for (Map.Entry<String, RedisDelayedQueueService> entry : map.entrySet()) {
            Class<? extends RedisDelayedQueueService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(QueueImpl.class)) {
                QueueImpl queueImpl = clazz.getAnnotation(QueueImpl.class);
                String name = queueImpl.name();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }
}
