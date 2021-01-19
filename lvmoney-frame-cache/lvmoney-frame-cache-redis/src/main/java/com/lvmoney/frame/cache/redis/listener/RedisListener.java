package com.lvmoney.frame.cache.redis.listener;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.handler
 * 版本信息: 版本1.0
 * 日期:2020/5/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.cache.redis.service.RedisListenerService;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/17 22:58
 */
public class RedisListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        RedisListenerService redisListenerService = SpringBeanUtil.getBean(RedisListenerService.class);
        String action = new String(message.getChannel());
        action = action.substring(action.indexOf(":") + 1);
        switch (action) {
            case "set":
                redisListenerService.set(message);
                break;
            case "expire":
                redisListenerService.expire(message);
                break;
            case "expired":
                redisListenerService.expired(message);
                break;
            case "del":
                redisListenerService.delete(message);
                break;
            case "rename":
                redisListenerService.rename(message);
                break;
            default:
                redisListenerService.other(message);
                break;
        }
    }
}
