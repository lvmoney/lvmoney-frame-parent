package com.zhy.frame.authentication.uri.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.uri.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/3/4
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.authentication.uri.constant.UriConstant;
import com.zhy.frame.authentication.uri.ro.SysUriRo;
import com.zhy.frame.authentication.uri.service.UriService;
import com.zhy.frame.authentication.uri.vo.SysUriVo;
import com.zhy.frame.cache.common.annotations.CacheImpl;
import com.zhy.frame.cache.common.constant.CacheConstant;
import com.zhy.frame.cache.common.service.CacheCommonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/4 11:27
 */
@Service
public class UriServiceImpl implements UriService {
    /**
     * 系统uri失效时间，方便权限配置，应该为永远
     */
    @Value("${shiro.auth.expire:0}")
    String expireUri;
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService baseRedisService;

    @Override
    public void deleteSysUriByServerName(String serverName) {
        baseRedisService.deleteByMapKey(UriConstant.SYS_URI_RES, serverName);
    }

    @Override
    public List<SysUriVo> getSysUriByServerName(String serverName) {
        Object obj = baseRedisService.getByMapKey(UriConstant.SYS_URI_RES, serverName);
        List<SysUriVo> sysServiceDataVos = JSON.parseObject(obj.toString(), new TypeReference<List<SysUriVo>>() {
        });
        return sysServiceDataVos;
    }

    @Override
    public void saveSysUri(SysUriRo sysUriRo) {
        long ex = sysUriRo.getExpire();
        if (ex > 0) {
            baseRedisService.addMap(UriConstant.SYS_URI_RES, sysUriRo.getData(), sysUriRo.getExpire());
        } else if (Long.parseLong(expireUri) > 0) {
            baseRedisService.addMap(UriConstant.SYS_URI_RES, sysUriRo.getData(), Long.parseLong(expireUri));
        } else {
            baseRedisService.addMap(UriConstant.SYS_URI_RES, sysUriRo.getData(), null);
        }
    }
}
