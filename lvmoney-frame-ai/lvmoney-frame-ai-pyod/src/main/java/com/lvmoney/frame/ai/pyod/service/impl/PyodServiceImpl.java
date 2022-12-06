package com.lvmoney.frame.ai.pyod.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.ai.pyod.constant.PyodConstant;
import com.lvmoney.frame.ai.pyod.dto.GetPyodFieldDto;
import com.lvmoney.frame.ai.pyod.dto.GetPyodParamDto;
import com.lvmoney.frame.ai.pyod.ro.PyodFieldRo;
import com.lvmoney.frame.ai.pyod.ro.PyodInputRo;
import com.lvmoney.frame.ai.pyod.ro.PyodParamRo;
import com.lvmoney.frame.ai.pyod.ro.item.PyodFieldItem;
import com.lvmoney.frame.ai.pyod.ro.item.PyodParamItem;
import com.lvmoney.frame.ai.pyod.service.PyodService;
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
public class PyodServiceImpl implements PyodService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;


    @Override
    public boolean savePyodParam(PyodParamRo pyodParamRo) {
        cacheCommonService.addMap(PyodConstant.SMART_DATA_PYOD_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, pyodParamRo.getParam(), pyodParamRo.getExpired());
        return true;
    }

    @Override
    public PyodParamItem getPyodParam(GetPyodParamDto getPyodParamDto) {
        Object obj = cacheCommonService.getByMapKey(PyodConstant.SMART_DATA_PYOD_PARAM + BaseConstant.CONNECTOR_UNDERLINE + serverName, getPyodParamDto.getPyodParamKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            PyodParamItem pyodParamItem = JSON.parseObject(obj.toString(), new TypeReference<PyodParamItem>() {
            });
            return pyodParamItem;
        } else {
            return null;
        }
    }

    @Override
    public boolean savePyodField(PyodFieldRo pyodFieldRo) {
        cacheCommonService.addMap(PyodConstant.SMART_DATA_PYOD_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, pyodFieldRo.getParam(), pyodFieldRo.getExpired());
        return true;
    }

    @Override
    public PyodFieldItem getPyodField(GetPyodFieldDto getPyodFieldDto) {
        Object obj = cacheCommonService.getByMapKey(PyodConstant.SMART_DATA_PYOD_FIELD + BaseConstant.CONNECTOR_UNDERLINE + serverName, getPyodFieldDto.getPyodFieldKey());
        if (ObjectUtils.isNotEmpty(obj)) {
            PyodFieldItem pyodFieldItem = JSON.parseObject(obj.toString(), new TypeReference<PyodFieldItem>() {
            });
            return pyodFieldItem;
        } else {
            return null;
        }
    }

    @Override
    public boolean savePyodInput(PyodInputRo pyodInputRo) {
        cacheCommonService.addMap(PyodConstant.SMART_DATA_PYOD_INPUT + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + pyodInputRo.getClassification().getValue(), pyodInputRo.getData(), pyodInputRo.getExpired());
        return true;
    }
}
