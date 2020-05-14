package com.zhy.frame.cache.lock.service.impl;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/26
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.cache.common.exception.CacheException;
import com.zhy.frame.cache.lock.constant.LockConstant;
import com.zhy.frame.cache.lock.service.DistributedLockerService;
import com.zhy.frame.cache.lock.service.ProdLockService;
import com.zhy.frame.cache.lock.vo.req.ProdLockInitReqVo;
import com.zhy.frame.cache.lock.vo.req.ProdLockStockReqVo;
import com.zhy.frame.cache.lock.vo.req.ProdLockUpdateReqVo;
import com.zhy.frame.cache.lock.vo.resp.ProdLockStockRespVo;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class ProdLockServiceImpl implements ProdLockService {
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    DistributedLockerService distributedLockerService;

    @Override
    public ProdLockStockRespVo getStock(ProdLockStockReqVo prodLockStockReqVo) {
        ProdLockStockRespVo prodLockStockRespVo = new ProdLockStockRespVo();
        String prodId = prodLockStockReqVo.getProdId();
        distributedLockerService.lock(LockConstant.PROD_SECTION_LOCK_KEY, TimeUnit.SECONDS, LockConstant.LOCK_TIME);
        Integer stock = 0;
        Integer num = prodLockStockReqVo.getNum();
        try {
            stock = (Integer) baseRedisService.getByMapKey(LockConstant.PROD_LOCK_KEY, prodId);
        } catch (Exception e) {
            throw new BusinessException(CacheException.Proxy.LOCK_SOURCE_ERROR);
        }
        if (stock < 0 || stock == null || stock < num) {
            prodLockStockRespVo.setResult(false);
            prodLockStockRespVo.setProdId(prodId);
            prodLockStockRespVo.setStock(stock);
        } else {
            stock = stock - num;
            Map<String, Integer> prod = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
            prod.put(prodId, stock);
            baseRedisService.addMap(LockConstant.PROD_LOCK_KEY, prod, prodLockStockReqVo.getExpire());
            prodLockStockRespVo.setResult(true);
            prodLockStockRespVo.setProdId(prodId);
            prodLockStockRespVo.setStock(stock);
        }
        distributedLockerService.unlock(LockConstant.PROD_SECTION_LOCK_KEY);
        return prodLockStockRespVo;
    }

    @Override
    public boolean initLockStock(ProdLockInitReqVo prodLockInitReqVo) {
        try {
            baseRedisService.addMap(LockConstant.PROD_LOCK_KEY, prodLockInitReqVo.getStock(), prodLockInitReqVo.getExpire());
            return true;
        } catch (Exception e) {
            throw new BusinessException(CacheException.Proxy.LOCK_SOURCE_INIT_ERROR);
        }
    }

    @Override
    public ProdLockStockRespVo updateLockStock(ProdLockUpdateReqVo prodLockUpdateReqVo) {
        String prodId = prodLockUpdateReqVo.getProdId();
        boolean bool = baseRedisService.isExistMapKey(LockConstant.PROD_LOCK_KEY, prodId);
        if (!bool) {
            throw new BusinessException(CacheException.Proxy.LOCK_SOURCE_NOT_EXIST);
        }
        distributedLockerService.lock(LockConstant.PROD_SECTION_UPDATE_LOCK_KEY, TimeUnit.SECONDS, LockConstant.LOCK_TIME);
        Map<String, Integer> prod = new HashMap<>(2);
        Integer stock = prodLockUpdateReqVo.getStock();
        prod.put(prodId, stock);
        baseRedisService.addMap(LockConstant.PROD_LOCK_KEY, prod, prodLockUpdateReqVo.getExpire());
        distributedLockerService.unlock(LockConstant.PROD_SECTION_UPDATE_LOCK_KEY);
        ProdLockStockRespVo prodLockStockRespVo = new ProdLockStockRespVo();
        prodLockStockRespVo.setStock(stock);
        prodLockStockRespVo.setResult(true);
        prodLockStockRespVo.setProdId(prodId);
        return prodLockStockRespVo;
    }
}
