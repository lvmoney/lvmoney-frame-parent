package com.lvmoney.frame.cache.redis.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.lock.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/3
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.cache.common.exception.CacheException;
import com.lvmoney.frame.cache.redis.constant.RedisConstant;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.cache.redis.service.SeckillService;
import com.lvmoney.frame.cache.redis.vo.SeckillProductVo;
import com.lvmoney.frame.cache.redis.vo.SeckillVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/3 13:26
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    @Value("${frame.seckill.expired:180000}")
    private Long expired;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public Long reduce(SeckillVo seckillVo) {
        String proKey = buildProdKey(seckillVo.getCode());
        if (ObjectUtils.isEmpty(baseRedisService.getByKey(proKey))) {
            throw new BusinessException(CacheException.Proxy.SECKILL_PRODUCT_NOT_EXIST);
        }
        DefaultRedisScript<String> longDefaultRedisScript = new DefaultRedisScript<>(RedisConstant.SECKILL_SCRIPT_LUA_REDUCE, String.class);
        String result = stringRedisTemplate.execute(longDefaultRedisScript, Collections.singletonList(proKey), String.valueOf(seckillVo.getNum()));
        return Long.valueOf(result);
    }

    @Override
    public Long add(SeckillVo seckillVo) {
        String proKey = buildProdKey(seckillVo.getCode());
        if (ObjectUtils.isEmpty(baseRedisService.getByKey(proKey))) {
            throw new BusinessException(CacheException.Proxy.SECKILL_PRODUCT_NOT_EXIST);
        }
        DefaultRedisScript<String> longDefaultRedisScript = new DefaultRedisScript<>(RedisConstant.SECKILL_SCRIPT_LUA_ADD, String.class);
        String result = stringRedisTemplate.execute(longDefaultRedisScript, Collections.singletonList(proKey), String.valueOf(seckillVo.getNum()));
        return Long.valueOf(result);
    }

    @Override
    public void save(SeckillProductVo seckillProductVo) {
        String proKey = buildProdKey(seckillProductVo.getCode());
        baseRedisService.setString(proKey, seckillProductVo.getCount(), expired);
    }

    private String buildProdKey(String code) {
        return RedisConstant.SECKILL_PRODUCT_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + code;
    }
}
