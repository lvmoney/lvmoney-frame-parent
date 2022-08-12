package com.lvmoney.frame.transaction.mq.customer.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.transaction.mq.customer.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/11/23
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.cache.lock.service.DistributedLockerService;
import com.lvmoney.frame.core.util.SignUtil;
import com.lvmoney.frame.core.vo.SignVo;
import com.lvmoney.frame.transaction.common.constant.TransactionConstant;
import com.lvmoney.frame.transaction.mq.common.enums.StatusEnum;
import com.lvmoney.frame.transaction.mq.common.ro.TransactionRo;
import com.lvmoney.frame.transaction.mq.common.service.TransactionMqCommonService;
import com.lvmoney.frame.transaction.mq.common.vo.TransactionVo;
import com.lvmoney.frame.transaction.mq.customer.service.TransactionCustomerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/23 15:16
 */
@Service
public class TransactionCustomerServiceImpl implements TransactionCustomerService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;

    @Autowired
    DistributedLockerService distributedLockerService;

    @Value("${transaction.customer.lock.time:10}")
    private int transactionLockTime;
    /**
     * process成功后默认30分钟自动过期时间
     */
    @Value("${transaction.customer.expired.time:1800}")
    private Long transactionCustomerExpired;

    @Autowired
    TransactionMqCommonService transactionMqCommonService;

    @Override
    public void transaction2Redis(TransactionRo transactionRo) {
        String redisKey = transactionMqCommonService.getTransactionRedisKey(transactionRo);
        cacheCommonService.setString(redisKey, transactionRo, transactionRo.getExpired());
    }

    @Override
    public void receiveTransaction(MessageVo messageVo) {
        TransactionRo transactionRo = new TransactionRo();
        TransactionVo transactionVo = JSON.parseObject(messageVo.getData().toString(), new TypeReference<TransactionVo>() {
        });
        if (ObjectUtils.isNotEmpty(transactionVo) && StatusEnum.NEW.equals(transactionVo.getStatusEnum())) {
            transactionVo.setStatusEnum(StatusEnum.RECEIVED);
            transactionRo.setData(transactionVo);
            transaction2Redis(transactionRo);
        }
    }

    @Override
    public void processTransaction(MessageVo messageVo) {
        TransactionVo transactionVo = JSON.parseObject(messageVo.getData().toString(), new TypeReference<TransactionVo>() {
        });
        TransactionRo transactionRo = new TransactionRo();
        if (ObjectUtils.isNotEmpty(transactionVo) && StatusEnum.NEW.equals(transactionVo.getStatusEnum())) {
            transactionVo.setStatusEnum(StatusEnum.RECEIVED);
            transactionRo.setData(transactionVo);
        }
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
        transactionVo.setStatusEnum(StatusEnum.PROCESSED);
        transactionRo.setData(transactionVo);
        transactionRo.setExpired(transactionCustomerExpired);
        transaction2Redis(transactionRo);
        distributedLockerService.unlock(lockKey);
    }

    @Override
    public boolean isHandle(MessageVo messageVo) {
        TransactionRo transactionRo = new TransactionRo();
        TransactionVo transactionVo = JSON.parseObject(messageVo.getData().toString(), new TypeReference<TransactionVo>() {
        });
        if (ObjectUtils.isNotEmpty(transactionVo) && StatusEnum.NEW.equals(transactionVo.getStatusEnum())) {
            transactionVo.setStatusEnum(StatusEnum.RECEIVED);
            transactionRo.setData(transactionVo);
            String redisKey = transactionMqCommonService.getTransactionRedisKey(transactionRo);
            if (cacheCommonService.isExist(redisKey)) {
                return false;
            }
            return true;
        }
        return false;
    }


}
