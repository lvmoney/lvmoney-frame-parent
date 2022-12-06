package com.lvmoney.frame.ai.adtk.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.ai.adtk.constant.AdtkConstant;
import com.lvmoney.frame.ai.adtk.ro.item.AdtkFieldItem;
import com.lvmoney.frame.ai.adtk.ro.item.AdtkParamItem;
import com.lvmoney.frame.ai.adtk.service.AdtkService;
import com.lvmoney.frame.ai.adtk.dto.GetAdtkFieldDto;
import com.lvmoney.frame.ai.adtk.dto.GetAdtkParamDto;
import com.lvmoney.frame.ai.adtk.ro.AdtkFieldRo;
import com.lvmoney.frame.ai.adtk.ro.AdtkInputRo;
import com.lvmoney.frame.ai.adtk.ro.AdtkParamRo;
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
 * @version:v1.0 2022/5/12 10:33
 */
@Service
public class AdtkServiceImpl implements AdtkService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;


    @Override
    public boolean saveAdtkParam(AdtkParamRo pyodParamRo) {
        cacheCommonService.addMap(AdtkConstant.SMART_DATA_ADTK_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, pyodParamRo.getParam(), pyodParamRo.getExpired());
        return true;
    }

    @Override
    public AdtkParamItem getAdtkParam(GetAdtkParamDto getAdtkParamDto) {
        Object obj = cacheCommonService.getByMapKey(AdtkConstant.SMART_DATA_ADTK_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, getAdtkParamDto.getAdtkParamKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            AdtkParamItem pyodParamItem = JSON.parseObject(obj.toString(), new TypeReference<AdtkParamItem>() {
            });
            return pyodParamItem;
        } else {
            return null;
        }
    }

    @Override
    public boolean saveAdtkField(AdtkFieldRo pyodFieldRo) {
        cacheCommonService.addMap(AdtkConstant.SMART_DATA_ADTK_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, pyodFieldRo.getParam(), pyodFieldRo.getExpired());
        return true;
    }

    @Override
    public AdtkFieldItem getAdtkField(GetAdtkFieldDto getAdtkFieldDto) {
        Object obj = cacheCommonService.getByMapKey(AdtkConstant.SMART_DATA_ADTK_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, getAdtkFieldDto.getAdtkFieldKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            AdtkFieldItem pyodFieldItem = JSON.parseObject(obj.toString(), new TypeReference<AdtkFieldItem>() {
            });
            return pyodFieldItem;
        } else {
            return null;
        }
    }

    @Override
    public boolean saveAdtkInput(AdtkInputRo pyodInputRo) {
        cacheCommonService.addMap(AdtkConstant.SMART_DATA_ADTK_INPUT + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + pyodInputRo.getClassification().getValue(), pyodInputRo.getData(), pyodInputRo.getExpired());
        return true;
    }
}
