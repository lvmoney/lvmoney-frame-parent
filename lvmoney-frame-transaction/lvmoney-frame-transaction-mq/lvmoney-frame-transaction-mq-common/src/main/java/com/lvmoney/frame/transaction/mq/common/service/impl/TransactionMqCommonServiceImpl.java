package com.lvmoney.frame.transaction.mq.common.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.transaction.mq.common.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/11/25
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.core.util.SignUtil;
import com.lvmoney.frame.core.vo.SignVo;
import com.lvmoney.frame.transaction.common.constant.TransactionConstant;
import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;
import com.lvmoney.frame.transaction.mq.common.service.TransactionMqCommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/25 17:21
 */
@Service
public class TransactionMqCommonServiceImpl implements TransactionMqCommonService {
    /**
     * TransactionVo 排序拼接时忽略字段，因为该字段在变更
     */
    private static final String TRANSACTION_VO_IGN_FIELD = "StatusEnum";
    /**
     * 服务名称
     */
    @Value("${spring.application.name:lvmoney}")
    String applicationName;

    @Override
    public String getTransactionRedisKey(TransactionRo transactionRo) {
        String status = transactionRo.getData().getStatusEnum().name();
        SignVo signVo = new SignVo();
        signVo.setData(transactionRo.getData());
        String code = SignUtil.contentAndBase64(signVo, TRANSACTION_VO_IGN_FIELD);
        return TransactionConstant.TRANSACTION_MQ_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + status + BaseConstant.CONNECTOR_UNDERLINE + applicationName + BaseConstant.CONNECTOR_UNDERLINE + code;
    }
}
