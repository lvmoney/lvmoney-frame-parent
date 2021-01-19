/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */
package com.lvmoney.frame.mq.rabbitmq.common.config;

import com.lvmoney.frame.mq.rabbitmq.common.constant.RabbitmqConstant;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class BaseConfig {

    @Bean
    public Queue simpleMessage() {
        return new Queue(RabbitmqConstant.SIMPLE_QUEUE_NAME);
    }

    @Bean
    public Queue topicMessage() {
        return new Queue(RabbitmqConstant.MESSAGE_TOPIC);
    }

    @Bean
    public Queue topicMessages() {
        return new Queue(RabbitmqConstant.MESSAGE_TOPICS);
    }

    @Bean
    public Queue fanoutMessage() {
        return new Queue(RabbitmqConstant.MESSAGE_FANOUT);
    }


    @Bean
    TopicExchange exchange() {
        return new TopicExchange(RabbitmqConstant.EXCHANGE_TOPIC);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitmqConstant.EXCHANGE_FANOUT);
    }

    @Bean
    Binding bindingExchangeMessages(Queue topicMessages, TopicExchange exchange) {
        return BindingBuilder.bind(topicMessages).to(exchange).with(RabbitmqConstant.ROUTING_KEY_TOPIC);
    }

    @Bean
    Binding bindingExchangeMessage(Queue topicMessage, TopicExchange exchange) {
        return BindingBuilder.bind(topicMessage).to(exchange).with(RabbitmqConstant.MESSAGE_TOPIC);
    }

    @Bean
    Binding bindingFanoutExchange(Queue fanoutMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutMessage).to(fanoutExchange);
    }

}
