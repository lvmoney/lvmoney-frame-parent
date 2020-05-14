package com.zhy.frame.mq.kafka.provider.sender;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.mq.common.annation.MqService;
import com.zhy.frame.mq.common.constant.MqConstant;
import com.zhy.frame.mq.common.service.MqSendService;
import com.zhy.frame.mq.common.vo.MessageVo;
import com.zhy.frame.mq.kafka.common.constant.KafkaConstant;
import com.zhy.frame.mq.kafka.provider.listener.ProviderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@MqService(MqConstant.KAFKA_TYPE_SIMPLE)
public class SimpleSenderImpl implements MqSendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleSenderImpl.class);

    @Autowired
    KafkaTemplate kafkaTemplate;

    @Autowired
    private ProviderListener producerListener;

    @Override
    public void send(MessageVo messageVo) {
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.send(KafkaConstant.SIMPLE_QUEUE_NAME, JsonUtil.t2JsonString(messageVo));
        try {
            //发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("kafka发送消息报错:{}", e.getMessage());
        }
    }

}
