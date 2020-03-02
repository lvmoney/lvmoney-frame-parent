package com.zhy.frame.mq.rabbitmq.provider.service.impl;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.mq.common.annation.MqService;
import com.zhy.frame.mq.common.constant.MqConstant;
import com.zhy.frame.mq.common.ro.ErrorRecordRo;
import com.zhy.frame.mq.rabbitmq.provider.service.BaseRabbitmqErrorService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.zhy.frame.mq.common.constant.MqConstant.MQ_ERROR_TYPE_RABBIT;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@MqService(MQ_ERROR_TYPE_RABBIT)
public class BaseRabbitmqErrorServiceImpl extends BaseRabbitmqErrorService {
    @Autowired
    BaseRedisService baseRedisService;


    @Override
    public void errorRecord2Redis(ErrorRecordRo errorRecordRo) {
        baseRedisService.addList(MqConstant.MQ_ERRROR_RECORD_REDIS_KEY, errorRecordRo.getData(), errorRecordRo.getExpire());
    }

    @Override
    public List getAllErrorRecord() {
        return baseRedisService.getListAll(MqConstant.MQ_ERRROR_RECORD_REDIS_KEY);
    }
}
