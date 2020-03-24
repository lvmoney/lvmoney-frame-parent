package com.zhy.frame.mq.kafka.provider.listener;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.mq.common.ro.ErrorRecordRo;
import com.zhy.frame.mq.kafka.provider.service.BaseKafkaErrorService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class ProviderListener implements ProducerListener {
    @Value("${kafka.error.record.expire:18000}")
    String expire;
    @Autowired
    BaseKafkaErrorService kafkaRedisService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderListener.class);

    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata) {
        LOGGER.debug("发送数据到kafka队列成功:{}", JsonUtil.t2JsonString(producerRecord));
    }

    @Override
    public void onError(ProducerRecord producerRecord, Exception exception) {
        ErrorRecordRo errorRecordRo = new ErrorRecordRo();
        errorRecordRo.setExpire(Long.valueOf(expire));
        List<ProducerRecord> data = new ArrayList<>();
        data.add(producerRecord);
        errorRecordRo.setData(data);
        kafkaRedisService.errorRecord2Redis(errorRecordRo);
    }
}
