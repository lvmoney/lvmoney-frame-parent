package com.lvmoney.frame.mq.rabbitmq.provider.sender;/**
 * 描述:
 * 包名:com.lvmoney.rabbitmq.ack.sender
 * 版本信息: 版本1.0
 * 日期:2019/11/12
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.mq.common.annotations.MqService;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.ro.ErrorRecordRo;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.common.vo.MsgErrorVo;
import com.lvmoney.frame.mq.rabbitmq.common.constant.RabbitmqConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import com.lvmoney.frame.mq.rabbitmq.provider.service.BaseRabbitmqErrorService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/12 18:44
 */
@MqService(MqConstant.RABBIT_TYPE_TOPIC_RICE)
public class TopicRichSenderImpl implements MqSendService {
    @Autowired
    BaseRabbitmqErrorService baseRabbitmqErrorService;
    @Value("${rabbitmq.error.record.expire:18000}")
    String expire;
    @Autowired
    ConnectionFactory connectionFactory;
    @Value("${frame.mq.name:lvmoney}")
    private String frameMqName;
    @Bean("topicRichRabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

    /**
     * @describe: 通配符匹配一topic开头的队列
     * @param: [msg]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/1/21
     */
    @Override
    public boolean send(MessageVo msg) {
        AtomicBoolean result = new AtomicBoolean(true);
        RabbitTemplate rabbitTemplate = rabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //只要exchange 错误被调用，ack=false
            if (!ack) {
                ErrorRecordRo errorRecordRo = new ErrorRecordRo();
                errorRecordRo.setExpire(Long.valueOf(expire));
                MsgErrorVo msgErrorVo = new MsgErrorVo();
                msgErrorVo.setMessageVo(msg);
                msgErrorVo.setMqName(RabbitmqConstant.SIMPLE_QUEUE_NAME);
                List<MsgErrorVo> data = new ArrayList<>();
                data.add(msgErrorVo);
                errorRecordRo.setData(data);
                baseRabbitmqErrorService.errorRecord2Redis(errorRecordRo);
                result.set(false);
            }
        });
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText,
                                          String exchange, String routingKey) -> { //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
            ErrorRecordRo errorRecordRo = new ErrorRecordRo();
            errorRecordRo.setExpire(Long.valueOf(expire));
            MsgErrorVo msgErrorVo = new MsgErrorVo();
            msgErrorVo.setMessageVo(msg);
            msgErrorVo.setMqName(RabbitmqConstant.SIMPLE_QUEUE_NAME);
            List<MsgErrorVo> data = new ArrayList<>();
            data.add(msgErrorVo);
            errorRecordRo.setData(data);
            baseRabbitmqErrorService.errorRecord2Redis(errorRecordRo);
            result.set(false);
        });
        rabbitTemplate.convertAndSend(frameMqName + BaseConstant.CONNECTOR_UNDERLINE + RabbitmqConstant.EXCHANGE_TOPIC, RabbitmqConstant.MESSAGE_TOPICS, JsonUtil.t2JsonString(msg));
        return result.get();
    }
}
