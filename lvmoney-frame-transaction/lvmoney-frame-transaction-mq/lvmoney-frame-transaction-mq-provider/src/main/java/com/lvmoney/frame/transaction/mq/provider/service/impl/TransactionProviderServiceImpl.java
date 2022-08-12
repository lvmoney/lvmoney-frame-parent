package com.lvmoney.frame.transaction.mq.provider.service.impl;/**
 * 描述:
 * 包名:mq.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/11/13
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.cache.lock.service.DistributedLockerService;
import com.lvmoney.frame.core.util.DateUtil;
import com.lvmoney.frame.core.util.SignUtil;
import com.lvmoney.frame.core.util.StringUtil;
import com.lvmoney.frame.core.vo.SignVo;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.factory.MqSendServiceContext;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.transaction.common.constant.TransactionConstant;
import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;
import com.lvmoney.frame.transaction.mq.common.service.TransactionMqCommonService;
import com.lvmoney.frame.transaction.mq.provider.dto.TransactionDto;
import com.lvmoney.frame.transaction.mq.common.enums.StatusEnum;
import com.lvmoney.frame.transaction.mq.provider.service.TransactionProviderService;
import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/13 15:36
 */
@Service
public class TransactionProviderServiceImpl implements TransactionProviderService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Autowired
    MqSendServiceContext mqSendServiceContext;

    @Autowired
    DistributedLockerService distributedLockerService;

    @Value("${transaction.provider.lock.time:10}")
    private int transactionLockTime;

    /**
     * publish成功后默认30分钟自动过期时间
     */
    @Value("${transaction.provider.expired.time:1800}")
    private Long transactionProviderExpired;

    @Autowired
    TransactionMqCommonService transactionMqCommonService;

    @Override
    public void transaction2Redis(TransactionRo transactionRo) {
        String redisKey = transactionMqCommonService.getTransactionRedisKey(transactionRo);
        cacheCommonService.setString(redisKey, transactionRo, transactionRo.getExpired());
    }

    @Override
    public void newTransaction(TransactionRo transactionRo) {
        transaction2Redis(transactionRo);
    }

    @Override
    public void publishTransaction(TransactionRo transactionRo) {
        TransactionVo transactionVo = transactionRo.getData();
        if (sendMsg(transactionVo)) {
            //当发送成功后，修改数据状态，加上分布式锁
            SignVo signVo = new SignVo();
            signVo.setData(transactionVo);
            String code = SignUtil.contentAndBase64(signVo);
            String status = transactionRo.getData().getStatusEnum().name();
            String lockKey = TransactionConstant.TRANSACTION_MQ_LOCK_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + code + BaseConstant.CONNECTOR_UNDERLINE + status;
            distributedLockerService.tryLock(lockKey, TimeUnit.SECONDS, 1, transactionLockTime);
            String deleteRedisKey = transactionMqCommonService.getTransactionRedisKey(transactionRo);
            if (!cacheCommonService.isExist(deleteRedisKey)) {
                return;
            }
            cacheCommonService.deleteByKey(deleteRedisKey);
            transactionVo.setStatusEnum(StatusEnum.PUBLISHED);
            transactionRo.setData(transactionVo);
            transactionRo.setExpired(transactionProviderExpired);
            transaction2Redis(transactionRo);
            distributedLockerService.unlock(lockKey);
        }
    }

    @Override
    public boolean sendMsg(TransactionVo transactionVo) {
        MessageVo messageVo = new MessageVo();
        messageVo.setData(transactionVo);
        messageVo.setMsgType(transactionVo.getType());
        messageVo.setDate(transactionVo.getDate());
        return mqSendServiceContext.sendMsg(MqConstant.KAFKA_TYPE_SIMPLE, messageVo);
    }

    @Override
    public void distributedTransaction(TransactionDto transactionDto) {
        TransactionRo transactionRo = transactionDto2TransactionRo(transactionDto);
        newTransaction(transactionRo);
        publishTransaction(transactionRo);
    }


    /**
     * transactionDto 转化成 transactionRo
     *
     * @param transactionDto:
     * @throws
     * @return: com.lvmoney.frame.transaction.mq.common.ro.TransactionRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/25 16:58
     */

    private TransactionRo transactionDto2TransactionRo(TransactionDto transactionDto) {
        TransactionVo transactionVo = new TransactionVo();
        transactionVo.setType(transactionDto.getType());
        transactionVo.setData(transactionDto.getData());
        transactionVo.setStatusEnum(StatusEnum.NEW);
        transactionVo.setUuid(StringUtil.getUuid());
        transactionVo.setDate(DateUtil.getNowFormatLong());
        TransactionRo transactionRo = new TransactionRo();
        transactionRo.setData(transactionVo);
        return transactionRo;
    }
}
