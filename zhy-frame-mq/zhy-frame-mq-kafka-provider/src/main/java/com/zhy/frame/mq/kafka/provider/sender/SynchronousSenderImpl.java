package com.zhy.frame.mq.kafka.provider.sender;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright 四川******科技有限公司
 *
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0
 * 2018年10月30日 下午3:29:38   
 */

import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.core.util.JsonUtil;
import com.zhy.frame.mq.common.annation.MqService;
import com.zhy.frame.mq.common.constant.MqConstant;
import com.zhy.frame.mq.common.service.MqSendService;
import com.zhy.frame.mq.common.vo.MessageVo;
import com.zhy.frame.mq.kafka.common.constant.KafkaConstant;
import com.zhy.frame.mq.kafka.provider.listener.ProviderListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@MqService(MqConstant.KAFKA_TYPE_SYN)
public class SynchronousSenderImpl implements MqSendService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SynchronousSenderImpl.class);
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    private ProviderListener producerListener;
    @Value("${kafka.send.get.milliseconds:1000}")
    String millseconds;

    @Override
    public void send(MessageVo messageVo) {
        try {
            kafkaTemplate.setProducerListener(producerListener);
            kafkaTemplate.send(KafkaConstant.SYN_QUEUE_NAME, JsonUtil.t2JsonString(messageVo)).get(Long.valueOf(millseconds), TimeUnit.MILLISECONDS);
            //发送消息的时候需要休眠一下，否则发送时间较长的时候会导致进程提前关闭导致无法调用回调时间。主要是因为KafkaTemplate发送消息是采取异步方式发送的
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            LOGGER.error("kafka发送同步消息报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_INTERRUPTED_ERROR);

        } catch (ExecutionException e) {
            LOGGER.error("kafka发送同步消息执行错误:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_EXE_ERROR);

        } catch (TimeoutException e) {
            LOGGER.error("kafka发送同步消息超时:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.KAFKA_SEND_SYN_TIME_ERROR);
        }
    }
}
