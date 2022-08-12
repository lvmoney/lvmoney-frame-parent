package com.lvmoney.demo.transaction.customer.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.transaction.mq.customer.application.impl
 * 版本信息: 版本1.0
 * 日期:2020/11/22
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.mq.common.annotations.CustomerService;
import com.lvmoney.frame.mq.common.service.MqDataHandService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.transaction.mq.customer.annotations.HandleTransaction;
import com.lvmoney.frame.transaction.mq.customer.service.TransactionCustomerService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/22 17:27
 */
@CustomerService(name = "USER_DATA_TYPE")
public class UserDataHandServiceImpl implements MqDataHandService {
    @Autowired
    TransactionCustomerService transactionCustomerService;

    @HandleTransaction
    @Override
    public void handData(MessageVo messageVo) {
        JsonUtil.t2JsonString(messageVo);
//        throw new BusinessException(CacheException.Proxy.REDIS_HOT_REQUEST_SUPPORT_ERROR);

    }
}
