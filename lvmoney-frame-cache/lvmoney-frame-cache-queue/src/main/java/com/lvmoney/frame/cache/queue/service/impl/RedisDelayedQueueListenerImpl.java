package com.lvmoney.frame.cache.queue.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.queue.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.queue.factory.RedisDelayedQueueListenerContext;
import com.lvmoney.frame.cache.queue.service.RedisDelayedQueueListener;
import com.lvmoney.frame.cache.queue.service.RedisDelayedQueueService;
import com.lvmoney.frame.cache.queue.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:18
 */
@Service
public class RedisDelayedQueueListenerImpl implements RedisDelayedQueueListener {
    @Autowired
    RedisDelayedQueueListenerContext redisDelayedQueueListenerContext;

    @Override
    public void receive(MessageVo messageVo) {
        redisDelayedQueueListenerContext.handData(messageVo.getType(), messageVo);
    }
}
