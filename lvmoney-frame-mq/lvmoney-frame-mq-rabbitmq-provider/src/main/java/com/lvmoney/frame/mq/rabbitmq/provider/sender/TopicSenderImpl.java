package com.lvmoney.frame.mq.rabbitmq.provider.sender;/**
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
import com.lvmoney.frame.mq.common.ro.ErrorRecordRo;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.common.vo.MsgErrorVo;
import com.lvmoney.frame.mq.rabbitmq.common.constant.RabbitmqConstant;
import com.lvmoney.frame.mq.rabbitmq.provider.service.BaseRabbitmqErrorService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @describe：topic 是RabbitMQ中最灵活的一种方式，可以根据binding_key自由的绑定不同的队列
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@MqService(MqConstant.RABBIT_TYPE_TOPIC)
public class TopicSenderImpl implements MqSendService {
    @Autowired
    BaseRabbitmqErrorService baseRabbitmqErrorService;
    @Value("${rabbitmq.error.record.expire:18000}")
    String expire;
    @Autowired
    ConnectionFactory connectionFactory;
    @Value("${frame.mq.name:lvmoney}")
    private String frameMqName;

    @Bean("topicRabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

    /**
     * @describe:只会匹配topic.message队列
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
            //exchange 错误被调用ack=false
            if (!ack) {
                ErrorRecordRo errorRecordRo = new ErrorRecordRo();
                errorRecordRo.setExpire(Long.valueOf(expire));
                MsgErrorVo msgErrorVo = new MsgErrorVo();
                msgErrorVo.setMessageVo(msg);
                msgErrorVo.setMqName(RabbitmqConstant.MESSAGE_TOPIC);
                msgErrorVo.setExName(RabbitmqConstant.EXCHANGE_TOPIC);
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
            msgErrorVo.setExName(exchange);
            msgErrorVo.setRoutingKey(routingKey);
            List<MsgErrorVo> data = new ArrayList<>();
            data.add(msgErrorVo);
            errorRecordRo.setData(data);
            baseRabbitmqErrorService.errorRecord2Redis(errorRecordRo);
            result.set(false);
        });
        rabbitTemplate.convertAndSend(frameMqName + BaseConstant.CONNECTOR_UNDERLINE + RabbitmqConstant.EXCHANGE_TOPIC, RabbitmqConstant.MESSAGE_TOPIC, JsonUtil.t2JsonString(msg));
        return result.get();
    }


}
