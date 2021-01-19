package com.lvmoney.frame.cache.queue.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.queue.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.queue.annotations.QueueImpl;
import com.lvmoney.frame.cache.queue.service.RedisDelayedQueueService;
import com.lvmoney.frame.cache.queue.vo.MessageVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 17:43
 */
@QueueImpl(name = "default")
public class DefaultRedisDelayedQueueServiceImpl implements RedisDelayedQueueService {
    @Override
    public void handData(MessageVo messageVo) {
        System.out.println("hand data:" + JsonUtil.t2JsonString(messageVo));
    }
}
