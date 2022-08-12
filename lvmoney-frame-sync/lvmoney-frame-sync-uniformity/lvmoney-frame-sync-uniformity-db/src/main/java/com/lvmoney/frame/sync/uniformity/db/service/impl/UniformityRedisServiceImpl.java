package com.lvmoney.frame.sync.uniformity.db.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/1/5
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.sync.uniformity.db.common.UniformityDbConstant;
import com.lvmoney.frame.sync.uniformity.db.dto.GetSelectDurationDto;
import com.lvmoney.frame.sync.uniformity.db.ro.SelectDurationRo;
import com.lvmoney.frame.sync.uniformity.db.ro.ShardingLogicRo;
import com.lvmoney.frame.sync.uniformity.db.service.UniformityRedisService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lvmoney.frame.sync.uniformity.db.common.UniformityDbConstant.UNIFORMITY_SELECT_END_PREFIX;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/5 14:55
 */
@Service
public class UniformityRedisServiceImpl implements UniformityRedisService {

    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Value("${uniformity.client.id:lvmoney}")
    String clientId;

    @Override
    public void uniformityConfig2Redis(List<ShardingLogicRo> shardingLogicRoList) {
        Map data = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
        shardingLogicRoList.forEach(d -> {
            data.put(d.getClassify() + BaseConstant.CONNECTOR_UNDERLINE + d.getDbLogicTable() + BaseConstant.CONNECTOR_UNDERLINE + d.getTableLogicTable(), d);
        });
        cacheCommonService.addMap(UniformityDbConstant.UNIFORMITY_SHARDING_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + clientId, data, 0L);
    }

    @Override
    public List<ShardingLogicRo> getUniformityConfig() {
        Map map = cacheCommonService.getMap(UniformityDbConstant.UNIFORMITY_SHARDING_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + clientId);
        List<ShardingLogicRo> result = new ArrayList<>();
        map.forEach((k, v) -> {
            ShardingLogicRo shardingLogicRo = JSON.parseObject(v.toString(), new TypeReference<ShardingLogicRo>() {
            });
            result.add(shardingLogicRo);
        });
        return result;

    }

    @Override
    public void selectDuration2Redis(SelectDurationRo selectDurationRo) {
        cacheCommonService.setString(UNIFORMITY_SELECT_END_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + selectDurationRo.getDbLogicTable() + BaseConstant.CONNECTOR_UNDERLINE + selectDurationRo.getTableLogicTable() + BaseConstant.CONNECTOR_UNDERLINE + selectDurationRo.getClassify(), selectDurationRo, selectDurationRo.getExpired());
    }

    @Override
    public SelectDurationRo getSelectDuration(GetSelectDurationDto getSelectDurationDto) {
        Object obj = cacheCommonService.getByKey(UNIFORMITY_SELECT_END_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + getSelectDurationDto.getDbLogicTable() + BaseConstant.CONNECTOR_UNDERLINE + getSelectDurationDto.getTableLogicTable() + BaseConstant.CONNECTOR_UNDERLINE + getSelectDurationDto.getClassify());
        if (ObjectUtil.isNotEmpty(obj)) {
            SelectDurationRo selectDurationRo = JSON.parseObject(obj.toString(), new TypeReference<SelectDurationRo>() {
            });
            return selectDurationRo;
        } else {
            return null;
        }

    }
}
