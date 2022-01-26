package com.lvmoney.frame.blockchain.webase.weidentity.common.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.common.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/7/2
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.common.constant.WeIdCommonConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.common.ro.WeidRo;
import com.lvmoney.frame.blockchain.webase.weidentity.common.service.WeidService;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/2 11:08
 */
@Service
public class WeidServiceImpl implements WeidService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;

    @Override
    public void saveSignRo2redis(WeidRo weidRo) {
        cacheCommonService.setString(WeIdCommonConstant.WE_ID_SING_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + weidRo.getNonce(), weidRo);
    }

    @Override
    public WeidRo getByNonce(String nonce) {
        WeidRo weidRo = JSON.parseObject(cacheCommonService.getByKey(WeIdCommonConstant.WE_ID_SING_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + nonce).toString(), new TypeReference<WeidRo>() {
        });
        return weidRo;
    }
}
