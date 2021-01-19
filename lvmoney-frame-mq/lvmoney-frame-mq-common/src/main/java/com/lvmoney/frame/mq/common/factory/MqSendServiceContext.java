package com.lvmoney.frame.mq.common.factory;/**
 * 描述:
 * 包名:com.lvmoney.frame.mq.common.factory
 * 版本信息: 版本1.0
 * 日期:2020/11/14
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.mq.common.annotations.MqService;
import com.lvmoney.frame.mq.common.exception.MqException;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/14 11:44
 */
@Component
public class MqSendServiceContext {
    private Map<String, MqSendService> strategyMap = new ConcurrentHashMap<>();

    /**
     * @describe:策略注入
     * @author: lvmoney /xxxx科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> MqSendServiceContext(Map<String, MqSendService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    /**
     * @param implName
     * @param messageVo 2018年11月8日下午3:08:26
     * @describe:策略方法
     * @author: lvmoney /xxxx科技有限公司
     */
    @SuppressWarnings("rawtypes")
    public boolean sendMsg(String implName, MessageVo messageVo) {
        String beanName = getBeanName(implName);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(MqException.Proxy.KAFKA_DYNAMIC_NOT_EXIST);
        }
        return strategyMap.get(beanName).send(messageVo);
    }

    public Map<String, MqSendService> getStrategyMap() {
        return strategyMap;
    }

    private String getBeanName(String mqType) {
        Map<String, MqSendService> map = this.getStrategyMap();
        for (Map.Entry<String, MqSendService> entry : map.entrySet()) {
            Class<? extends MqSendService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(MqService.class)) {
                MqService dynamicService = clazz.getAnnotation(MqService.class);
                String name = dynamicService.value();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }

}
