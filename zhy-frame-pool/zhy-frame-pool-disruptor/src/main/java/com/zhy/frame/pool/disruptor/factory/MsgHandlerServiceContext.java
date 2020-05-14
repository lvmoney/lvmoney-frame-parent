package com.zhy.frame.pool.disruptor.factory;/**
 * 描述:
 * 包名:com.zhy.frame.pool.disruptor.factory
 * 版本信息: 版本1.0
 * 日期:2020/4/27
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.pool.common.annotion.MsgHandler;
import com.zhy.frame.pool.common.exception.PoolException;
import com.zhy.frame.pool.disruptor.service.MsgHandlerService;
import com.zhy.frame.pool.disruptor.vo.MsgEvent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/27 21:57
 */
@Component
public class MsgHandlerServiceContext {
    @Autowired
    private Map<String, MsgHandlerService> strategyMap = new ConcurrentHashMap();

    /**
     * @describe:策略注入
     * @author: lvmoney /四川******科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> MsgHandlerServiceContext(Map<String, MsgHandlerService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    /**
     * @param implName:
     * @param msgEvent:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 22:37
     */
    public void getData(String implName, MsgEvent msgEvent) {
        String beanName = getEventHandlerBeanName(implName);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(PoolException.Proxy.DISRUPTOR_DYNAMIC_NOT_EXIST);
        }
        try {
            strategyMap.get(beanName).onEvent(msgEvent);
        } catch (Exception e) {
            throw new BusinessException(PoolException.Proxy.DISRUPTOR_MSG_HANDLER_ERROR);
        }
    }

    public Map<String, MsgHandlerService> getStrategyMap() {
        return strategyMap;
    }

    private String getEventHandlerBeanName(String mqType) {
        Map<String, MsgHandlerService> map = this.getStrategyMap();
        for (Map.Entry<String, MsgHandlerService> entry : map.entrySet()) {
            Class<? extends MsgHandlerService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(MsgHandler.class)) {
                MsgHandler msgHandler = clazz.getAnnotation(MsgHandler.class);
                String name = msgHandler.name();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }
}
