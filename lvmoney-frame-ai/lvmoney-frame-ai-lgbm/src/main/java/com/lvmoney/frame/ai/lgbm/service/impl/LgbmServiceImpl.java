package com.lvmoney.frame.ai.lgbm.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.ai.lgbm.constant.LgbmConstant;
import com.lvmoney.frame.ai.lgbm.ro.LgbmInputRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmMultivariableFieldRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmParamRo;
import com.lvmoney.frame.ai.lgbm.ro.LgbmUnivariateFieldRo;
import com.lvmoney.frame.ai.lgbm.ro.item.LgbmModelBestParams;
import com.lvmoney.frame.ai.lgbm.service.LgbmService;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/16 13:47
 */
@Service
public class LgbmServiceImpl implements LgbmService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;

    @Override
    public boolean saveLgbmInput(LgbmInputRo lgbmInputRo) {
        cacheCommonService.addMap(LgbmConstant.SMART_DATA_LGBM_INPUT + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + lgbmInputRo.getClassification().getValue(), lgbmInputRo.getData(), lgbmInputRo.getExpired());
        return true;
    }

    @Override
    public boolean saveMultivariableLgbmParam(LgbmParamRo lgbmParamRo) {
        cacheCommonService.addMap(LgbmConstant.SMART_DATA_LGBM_PARAM_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, lgbmParamRo.getParam(), lgbmParamRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLgbmUnivariateParam(LgbmParamRo lgbmParamRo) {
        cacheCommonService.addMap(LgbmConstant.SMART_DATA_LGBM_PARAM_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, lgbmParamRo.getParam(), lgbmParamRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLgbmMultivariableField(LgbmMultivariableFieldRo lgbmMultivariableFieldRo) {
        cacheCommonService.addMap(LgbmConstant.SMART_DATA_LGBM_FIELD_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, lgbmMultivariableFieldRo.getParam(), lgbmMultivariableFieldRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLgbmUnivariateField(LgbmUnivariateFieldRo lgbmUnivariateFieldRo) {
        cacheCommonService.addMap(LgbmConstant.SMART_DATA_LGBM_FIELD_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, lgbmUnivariateFieldRo.getParam(), lgbmUnivariateFieldRo.getExpired());
        return true;
    }

    @Override
    public LgbmModelBestParams getUnivariateByPredictId(String predictId) {
        Object obj = cacheCommonService.getByMapKey(LgbmConstant.SMART_DATA_LGBM_BEST_MODEL_PARAM_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, predictId);
        if (ObjectUtils.isNotEmpty(obj)) {
            LgbmModelBestParams lstmModelBestParams = JSON.parseObject(obj.toString(), new TypeReference<LgbmModelBestParams>() {
            });
            return lstmModelBestParams;
        } else {
            return null;
        }
    }

    @Override
    public LgbmModelBestParams getMultivariableByPredictId(String predictId) {
        Object obj = cacheCommonService.getByMapKey(LgbmConstant.SMART_DATA_LGBM_BEST_MODEL_PARAM_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, predictId);
        if (ObjectUtils.isNotEmpty(obj)) {
            LgbmModelBestParams lstmModelBestParams = JSON.parseObject(obj.toString(), new TypeReference<LgbmModelBestParams>() {
            });
            return lstmModelBestParams;
        } else {
            return null;
        }
    }
}
