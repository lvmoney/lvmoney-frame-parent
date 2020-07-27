package com.zhy.frame.newsql.clickhouse.sink.service;/**
 * 描述:
 * 包名:com.zhy.frame.newsql.clickhouse.sink.service
 * 版本信息: 版本1.0
 * 日期:2020/7/21
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/21 9:14
 */
public interface KafkaService {
    /**
     * 处理kafka消息
     *
     * @param header:
     * @param body:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/21 9:17
     */
    void handKafkaMsg(String header, String body);
}
