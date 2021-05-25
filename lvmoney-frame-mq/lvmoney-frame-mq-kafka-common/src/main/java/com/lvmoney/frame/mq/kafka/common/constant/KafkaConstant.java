package com.lvmoney.frame.mq.kafka.common.constant;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class KafkaConstant {
    private KafkaConstant() {
    }

    /**
     * 简单
     */
    public static final String SIMPLE_QUEUE_NAME = "simple";
    /**
     * 异步
     */
    public static final String SYN_QUEUE_NAME = "synchronous";
    /**
     * 简单 group
     */
    public static final String SIMPLE_QUEUE_GROUP_ID = "simple_group";
    /**
     * 异步 group
     */
    public static final String SYN_QUEUE_GROUP_ID = "synchronous_group";


}
