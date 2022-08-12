package com.lvmoney.frame.mq.kafka.provider.sender;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.mq.common.annotations.MqService;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.kafka.common.constant.KafkaConstant;
import com.lvmoney.frame.mq.kafka.provider.listener.ProviderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    @Value("${kafka.send.get.milliseconds:1000}")
    String millseconds;

    @Value("${frame.mq.name:lvmoney}")
    private String frameMqName;

    @Override
    public boolean send(MessageVo messageVo) {
        try {
            kafkaTemplate.setProducerListener(producerListener);
            kafkaTemplate.send(frameMqName + BaseConstant.CONNECTOR_UNDERLINE + KafkaConstant.SIMPLE_QUEUE_NAME, JsonUtil.t2JsonString(messageVo)).get(Long.valueOf(millseconds), TimeUnit.MILLISECONDS);
            //发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
            Thread.sleep(1000);
        } catch (TimeoutException | ExecutionException | InterruptedException e) {
            LOGGER.error("kafka发送同步消息报错:{}", e);
            return false;
        }
        return true;
    }

}
