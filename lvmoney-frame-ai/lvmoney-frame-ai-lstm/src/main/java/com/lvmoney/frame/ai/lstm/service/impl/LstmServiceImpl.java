package com.lvmoney.frame.ai.lstm.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.ai.lstm.constant.LstmConstant;
import com.lvmoney.frame.ai.lstm.ro.LstmMultivariableFieldRo;
import com.lvmoney.frame.ai.lstm.ro.LstmInputRo;
import com.lvmoney.frame.ai.lstm.ro.LstmParamRo;
import com.lvmoney.frame.ai.lstm.ro.LstmUnivariateFieldRo;
import com.lvmoney.frame.ai.lstm.ro.item.LstmModelBestParams;
import com.lvmoney.frame.ai.lstm.service.LstmService;
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
public class LstmServiceImpl implements LstmService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;

    @Override
    public boolean saveLstmInput(LstmInputRo lstmInputRo) {
        cacheCommonService.addMap(LstmConstant.SMART_DATA_LSTM_INPUT + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + lstmInputRo.getClassification().getValue(), lstmInputRo.getData(), lstmInputRo.getExpired());
        return true;
    }

    @Override
    public boolean saveMultivariableLstmParam(LstmParamRo lstmParamRo) {
        cacheCommonService.addMap(LstmConstant.SMART_DATA_LSTM_PARAM_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, lstmParamRo.getParam(), lstmParamRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLstmUnivariateParam(LstmParamRo lstmParamRo) {
        cacheCommonService.addMap(LstmConstant.SMART_DATA_LSTM_PARAM_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, lstmParamRo.getParam(), lstmParamRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLstmMultivariableField(LstmMultivariableFieldRo lstmMultivariableFieldRo) {
        cacheCommonService.addMap(LstmConstant.SMART_DATA_LSTM_FIELD_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, lstmMultivariableFieldRo.getParam(), lstmMultivariableFieldRo.getExpired());
        return true;
    }

    @Override
    public boolean saveLstmUnivariateField(LstmUnivariateFieldRo lstmUnivariateFieldRo) {
        cacheCommonService.addMap(LstmConstant.SMART_DATA_LSTM_FIELD_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, lstmUnivariateFieldRo.getParam(), lstmUnivariateFieldRo.getExpired());
        return true;
    }

    @Override
    public LstmModelBestParams getUnivariateByPredictId(String predictId) {
        Object obj = cacheCommonService.getByMapKey(LstmConstant.SMART_DATA_LSTM_BEST_MODEL_PARAM_UNIV + BaseConstant.CONNECTOR_UNDERLINE + serverName, predictId);
        if (ObjectUtils.isNotEmpty(obj)) {
            LstmModelBestParams lstmModelBestParams = JSON.parseObject(obj.toString(), new TypeReference<LstmModelBestParams>() {
            });
            return lstmModelBestParams;
        } else {
            return null;
        }
    }

    @Override
    public LstmModelBestParams getMultivariableByPredictId(String predictId) {
        Object obj = cacheCommonService.getByMapKey(LstmConstant.SMART_DATA_LSTM_BEST_MODEL_PARAM_MULT + BaseConstant.CONNECTOR_UNDERLINE + serverName, predictId);
        if (ObjectUtils.isNotEmpty(obj)) {
            LstmModelBestParams lstmModelBestParams = JSON.parseObject(obj.toString(), new TypeReference<LstmModelBestParams>() {
            });
            return lstmModelBestParams;
        } else {
            return null;
        }
    }
}
