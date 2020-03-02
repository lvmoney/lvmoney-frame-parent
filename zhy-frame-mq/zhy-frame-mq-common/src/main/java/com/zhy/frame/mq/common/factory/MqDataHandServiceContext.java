/**
 * 描述:
 * 包名:com.zhy.pay.factory
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  上午11:07:01
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.mq.common.factory;

import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.mq.common.annation.CustomerService;
import com.zhy.frame.mq.common.service.MqDataHandService;
import com.zhy.frame.mq.common.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：调用不同数据处理策略模式环境角色类
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月18日 上午11:07:01
 */
@Component
public class MqDataHandServiceContext {
    @Autowired
    private Map<String, MqDataHandService> strategyMap = new ConcurrentHashMap();

    /**
     * @describe:策略注入
     * @author: lvmoney /四川******科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> MqDataHandServiceContext(Map<String, MqDataHandService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    /**
     * @param implName
     * @param messageVo 2018年11月8日下午3:08:26
     * @describe:策略方法
     * @author: lvmoney /四川******科技有限公司
     */
    @SuppressWarnings("rawtypes")
    public void getData(String implName, MessageVo messageVo) {
        String beanName = getMqDataHandServiceBeanName(implName);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(CommonException.Proxy.RABBITMQ_DYNAMIC_NOT_EXSIT);
        }
        strategyMap.get(beanName).handData(messageVo);
    }

    public Map<String, MqDataHandService> getStrategyMap() {
        return strategyMap;
    }

    private String getMqDataHandServiceBeanName(String mqType) {
        Map<String, MqDataHandService> map = this.getStrategyMap();
        for (Map.Entry<String, MqDataHandService> entry : map.entrySet()) {
            Class<? extends MqDataHandService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(CustomerService.class)) {
                CustomerService dynamicService = clazz.getAnnotation(CustomerService.class);
                String name = dynamicService.name();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }

}
