package com.lvmoney.frame.transaction.mq.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.transaction.mq.common.service
 * 版本信息: 版本1.0
 * 日期:2020/11/25
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/25 17:20
 */
public interface TransactionMqCommonService {
    /**
     * 获得保存到redis 的key
     *
     * @param transactionRo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/25 17:21
     */
    String getTransactionRedisKey(TransactionRo transactionRo);
}
