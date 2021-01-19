package com.lvmoney.frame.mq.rabbitmq.common.constant;/**
 * 描述:
 * 包名:lvmoney.frame.mq.rabbitmq.constant
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class RabbitmqConstant {
    /**
     * 简单的队列
     */
    public static final String SIMPLE_QUEUE_NAME = "simple";
    /**
     * topic队列
     */
    public static final String MESSAGE_TOPIC = "topic.message";
    /**
     * 通配符队列
     */
    public static final String MESSAGE_TOPICS = "topic.richmessage";
    /**
     * fanout队列
     */
    public static final String MESSAGE_FANOUT = "fanout.message";
    /**
     * fanout 交换机
     */
    public static final String EXCHANGE_FANOUT = "fanout.exchange";
    /**
     * topic交换机
     */
    public static final String EXCHANGE_TOPIC = "topic.exchange";

    /**
     * 通配符队列
     */
    public static final String ROUTING_KEY_TOPIC = "topic.#";


}
