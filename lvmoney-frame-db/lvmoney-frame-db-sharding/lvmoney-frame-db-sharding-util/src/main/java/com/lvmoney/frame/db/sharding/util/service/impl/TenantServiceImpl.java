package com.lvmoney.frame.db.sharding.util.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.core.util.ConsistentHashVirtualNodeUtil;
import com.lvmoney.frame.db.common.exception.DbException;
import com.lvmoney.frame.db.sharding.util.bo.GetDbTenantBo;
import com.lvmoney.frame.db.sharding.util.constant.DbUtilConstant;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTableDto;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTenantDto;
import com.lvmoney.frame.db.sharding.util.ro.DbTableRelRo;
import com.lvmoney.frame.db.sharding.util.ro.DbTenantRelRo;
import com.lvmoney.frame.db.sharding.util.ro.item.DbTableRelRoItem;
import com.lvmoney.frame.db.sharding.util.ro.item.DbTenantRelRoItem;
import com.lvmoney.frame.db.sharding.util.service.TenantService;
import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.Mapper;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.lvmoney.frame.db.sharding.util.constant.DbUtilConstant.REL_DB_TABLE_PREFIX;
import static com.lvmoney.frame.db.sharding.util.constant.DbUtilConstant.REL_DB_TENANT_PREFIX;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 18:08
 */
@Service
public class TenantServiceImpl implements TenantService {
    @Value("${spring.application.name:lvmoney}")
    private String applicationName;

    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    @Autowired
    Mapper mapper;

    @Override
    public GetDbTenantBo getDbTenant(GetDbTenantDto getDbTenantDto) {
        GetDbTenantDto getDbTenantDtoRedis = mapper.map(getDbTenantDto, GetDbTenantDto.class);
        DbTenantRelRoItem dbTenantRelRoItem = getDbTenantByRedis(getDbTenantDtoRedis);
        GetDbTenantBo getDbTenantBo = new GetDbTenantBo();
        if (ObjectUtils.isNotEmpty(dbTenantRelRoItem)) {
            getDbTenantBo = mapper.map(dbTenantRelRoItem, GetDbTenantBo.class);
            getDbTenantBo.setTenantId(getDbTenantDto.getTenantId());
            return getDbTenantBo;
        }
        GetDbTableDto getDbTableDto = new GetDbTableDto();
        getDbTableDto.setGroup(getDbTenantDto.getGroup());
        getDbTableDto.setClassify(getDbTenantDto.getClassify());
        List<DbTableRelRoItem> dbTableRelRoItems = this.getDbTableByRedis(getDbTableDto);
        List<String> serverNameList = new ArrayList<>();
        dbTableRelRoItems.stream().forEach(e -> {
            e.getTable().stream().forEach(f -> {
                serverNameList.add(e.getDb() + BaseConstant.CHAR_AND + f);
            });
        });
        String ip = ConsistentHashVirtualNodeUtil.getServer(getDbTenantDto.getTenantId(), serverNameList, DbUtilConstant.DEFAULT_VIRTUAL_NODE_SUM);

        getDbTenantBo.setDb(ip.split(BaseConstant.CHAR_AND)[0]);
        getDbTenantBo.setTable(ip.split(BaseConstant.CHAR_AND)[1]);
        getDbTenantBo.setTenantId(getDbTenantDto.getTenantId());
        DbTenantRelRo dbTenantRelRo = new DbTenantRelRo();
        dbTenantRelRo.setGroup(getDbTenantDto.getGroup());
        Map<String, DbTenantRelRoItem> data = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
        DbTenantRelRoItem dbTenantRelRoItem2Redis = new DbTenantRelRoItem();
        dbTenantRelRoItem2Redis.setDb(getDbTenantBo.getDb());
        dbTenantRelRoItem2Redis.setTable(getDbTenantBo.getTable());
        data.put(getDbTenantDto.getTenantId(), dbTenantRelRoItem2Redis);
        dbTenantRelRo.setRel(data);
        this.dbTenantRel2Redis(dbTenantRelRo);
        return getDbTenantBo;
    }

    @Override
    public void dbTableRel2Redis(DbTableRelRo dbTableRelRo) {
        cacheCommonService.addMap(getDbTableRel(), dbTableRelRo.getData(), dbTableRelRo.getExpired());
    }

    @Override
    public List<DbTableRelRoItem> getDbTableByRedis(GetDbTableDto getDbTableDto) {
        Object obj = cacheCommonService.getByMapKey(getDbTableRel(), getDbTableDto.getClassify() + BaseConstant.CONNECTOR_UNDERLINE + getDbTableDto.getGroup());
        if (ObjectUtils.isNotEmpty(obj)) {
            List<DbTableRelRoItem> dbTableRelRoItems = JSON.parseObject(obj.toString(), new TypeReference<List<DbTableRelRoItem>>() {
            });
            return dbTableRelRoItems;
        } else {
            throw new BusinessException(DbException.Proxy.SHARDING_DB_TABLE_ERROR);
        }
    }


    @Override
    public void dbTenantRel2Redis(DbTenantRelRo dbTenantRelRo) {
        cacheCommonService.addMap(getTenantDbRel(dbTenantRelRo.getGroup()), dbTenantRelRo.getRel(), dbTenantRelRo.getExpired());
    }

    @Override
    public DbTenantRelRoItem getDbTenantByRedis(GetDbTenantDto getDbTenantDto) {
        Object obj = cacheCommonService.getByMapKey(getTenantDbRel(getDbTenantDto.getGroup()), getDbTenantDto.getTenantId());
        if (ObjectUtils.isNotEmpty(obj)) {
            DbTenantRelRoItem dbTenantRelRoItem = JSON.parseObject(obj.toString(), new TypeReference<DbTenantRelRoItem>() {
            });
            return dbTenantRelRoItem;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, DbTenantRelRoItem> getDbTenantByKey(String group) {
        Map<String, DbTenantRelRoItem> obj = cacheCommonService.getMap(getTenantDbRel(group));
        return obj;
    }

    /**
     * 获得库表关系redis key
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 18:14
     */

    private String getDbTableRel() {
        return REL_DB_TABLE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + applicationName;
    }

    /**
     * 获得指定group的租户库表关系redis key
     *
     * @param group:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 18:17
     */

    private String getTenantDbRel(String group) {
        return REL_DB_TENANT_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + applicationName + BaseConstant.CONNECTOR_UNDERLINE + group;
    }
}
