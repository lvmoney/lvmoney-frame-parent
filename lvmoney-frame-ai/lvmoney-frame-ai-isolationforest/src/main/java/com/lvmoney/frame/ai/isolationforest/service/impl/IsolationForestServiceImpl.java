package com.lvmoney.frame.ai.isolationforest.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.ai.isolationforest.constant.IsolationForestConstant;
import com.lvmoney.frame.ai.isolationforest.dto.GetIsolationForestFieldDto;
import com.lvmoney.frame.ai.isolationforest.dto.GetIsolationForestParamDto;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestFieldRo;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestInputRo;
import com.lvmoney.frame.ai.isolationforest.ro.IsolationForestParamRo;
import com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestField;
import com.lvmoney.frame.ai.isolationforest.ro.item.IsolationForestParam;
import com.lvmoney.frame.ai.isolationforest.service.IsolationForestService;
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
public class IsolationForestServiceImpl implements IsolationForestService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;


    @Override
    public boolean saveIsolationForestParam(IsolationForestParamRo isolationForestParamRo) {
        cacheCommonService.addMap(IsolationForestConstant.SMART_DATA_ISOLATION_FOREST_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, isolationForestParamRo.getParam(), isolationForestParamRo.getExpired());
        return true;
    }

    @Override
    public IsolationForestParam GetIsolationForestParam(GetIsolationForestParamDto getIsolationForestParamDto) {
        Object obj = cacheCommonService.getByMapKey(IsolationForestConstant.SMART_DATA_ISOLATION_FOREST_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, getIsolationForestParamDto.getIsolationForestParamKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            IsolationForestParam isolationForestParam = JSON.parseObject(obj.toString(), new TypeReference<IsolationForestParam>() {
            });
            return isolationForestParam;
        } else {
            return null;
        }
    }

    @Override
    public boolean saveIsolationForestField(IsolationForestFieldRo isolationForestFieldRo) {
        cacheCommonService.addMap(IsolationForestConstant.SMART_DATA_ISOLATION_FOREST_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, isolationForestFieldRo.getParam(), isolationForestFieldRo.getExpired());
        return true;
    }

    @Override
    public IsolationForestField GetIsolationForestField(GetIsolationForestFieldDto getIsolationForestFieldDto) {
        Object obj = cacheCommonService.getByMapKey(IsolationForestConstant.SMART_DATA_ISOLATION_FOREST_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, getIsolationForestFieldDto.getIsolationForestFieldKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            IsolationForestField isolationForestField = JSON.parseObject(obj.toString(), new TypeReference<IsolationForestField>() {
            });
            return isolationForestField;
        } else {
            return null;
        }
    }

    @Override
    public boolean saveIsolationForestInput(IsolationForestInputRo isolationForestInputRo) {
        cacheCommonService.addMap(IsolationForestConstant.SMART_DATA_ISOLATION_FOREST_INPUT + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + isolationForestInputRo.getClassification().getValue(), isolationForestInputRo.getData(), isolationForestInputRo.getExpired());
        return true;
    }
}
