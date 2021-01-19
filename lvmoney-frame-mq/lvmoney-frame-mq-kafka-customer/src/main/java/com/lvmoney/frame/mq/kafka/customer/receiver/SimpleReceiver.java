package com.lvmoney.frame.mq.kafka.customer.receiver;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.mq.common.factory.MqDataHandServiceContext;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.kafka.common.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class SimpleReceiver {
    @Autowired
    MqDataHandServiceContext handMqServiceContext;

    @KafkaListener(topics = {KafkaConstant.SIMPLE_QUEUE_NAME}, groupId = KafkaConstant.SIMPLE_QUEUE_GROUP_ID)
    public void receive(ConsumerRecord consumerRecord) throws InterruptedException {
        MessageVo mqDataVo = JSONObject.parseObject(consumerRecord.value().toString(), MessageVo.class);
        String mqType = mqDataVo.getMsgType();
        handMqServiceContext.getData(mqType, mqDataVo);
    }
}
