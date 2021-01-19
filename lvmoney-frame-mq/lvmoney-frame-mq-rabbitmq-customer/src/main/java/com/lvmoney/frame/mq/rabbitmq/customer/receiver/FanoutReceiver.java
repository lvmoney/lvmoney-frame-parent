package com.lvmoney.frame.mq.rabbitmq.customer.receiver;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.mq.common.exception.MqException;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.rabbitmq.common.constant.RabbitmqConstant;
import com.lvmoney.frame.mq.common.factory.MqDataHandServiceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
@RabbitListener(queues = RabbitmqConstant.MESSAGE_FANOUT)
public class FanoutReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(FanoutReceiver.class);
    @Autowired
    MqDataHandServiceContext handMqServiceContext;

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) {
        MessageVo mqDataVo = JSONObject.parseObject(msg, MessageVo.class);
        String mqType = mqDataVo.getMsgType();
        handMqServiceContext.getData(mqType, mqDataVo);
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("消费消息失败:{}", e);
            throw new BusinessException(MqException.Proxy.RABBIT_MESSAGE_RECEIVER_FANOUT_ERROR);
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }
}
