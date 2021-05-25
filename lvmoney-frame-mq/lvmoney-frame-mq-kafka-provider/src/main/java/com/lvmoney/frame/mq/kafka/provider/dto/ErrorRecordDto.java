package com.lvmoney.frame.mq.kafka.provider.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.mq.kafka.provider.dto
 * 版本信息: 版本1.0
 * 日期:2020/11/23
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/23 10:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorRecordDto<K, V> implements Serializable {
    private static final long serialVersionUID = 7537167626673587246L;
    private String topic;
    private Integer partition;
    private Headers headers;
    private K key;
    private V value;
    private Long timestamp;
}
