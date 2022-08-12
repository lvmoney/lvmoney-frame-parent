/**
 * 描述:
 * 包名:com.lvmoney.shiro.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午5:30:27
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.authentication.shiro.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.authentication.shiro.constant.ShiroConstant;
import com.lvmoney.frame.authentication.shiro.ro.ShiroDataRo;
import com.lvmoney.frame.authentication.shiro.ro.ShiroUriRo;
import com.lvmoney.frame.authentication.shiro.service.ShiroRedisService;
import com.lvmoney.frame.authentication.shiro.vo.ShiroDataVo;
import com.lvmoney.frame.authentication.shiro.vo.ShiroUriVo;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月8日 下午5:30:27
 */
@Service("frameShiroRedisService")
public class ShiroRedisServiceImpl implements ShiroRedisService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService baseRedisService;
    /**
     * 登录用户相关数据失效时间
     */
    @Value("${shiro.data.expire:18000}")
    String expire;
    /**
     * 系统uri失效时间，方便权限配置，应改为永远
     */
    @Value("${shiro.auth.expire:0}")
    String expireUri;


    @Override
    public void saveShiroData(ShiroDataRo shiroDataRo) {
        long ex = shiroDataRo.getExpire();
        String redisKey = ShiroConstant.USER_SHIRO_RES + BaseConstant.CONNECTOR_UNDERLINE + shiroDataRo.getSysId() + BaseConstant.CONNECTOR_UNDERLINE + shiroDataRo.getUsername();
        if (ex > 0) {
            baseRedisService.setString(redisKey, JsonUtil.t2JsonString(shiroDataRo), shiroDataRo.getExpire());
        } else {
            baseRedisService.setString(redisKey, JsonUtil.t2JsonString(shiroDataRo), Long.parseLong(expire));
        }
    }


    @Override
    public void saveShiroUriData(ShiroUriRo shiroUriRo) {
        Map<String, String> map = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
        map.put(shiroUriRo.getSysId() + BaseConstant.CONNECTOR_UNDERLINE + shiroUriRo.getUri(), JsonUtil.t2JsonString(shiroUriRo.getShiroUriVo()));
        baseRedisService.addMap(ShiroConstant.SYS_SHIRO_URI, map, shiroUriRo.getExpire());
    }

    @Override
    public ShiroUriVo getShiroUriData(String uri) {
        String str = (String) baseRedisService.getByMapKey(ShiroConstant.SYS_SHIRO_URI, uri);
        ShiroUriVo result = JSONObject.parseObject(str, ShiroUriVo.class);
        return result;
    }

    @Override
    public ShiroDataVo getShiroData(String username) {
        ShiroDataVo result = new ShiroDataVo();
        String shiroString = baseRedisService.getByKey(ShiroConstant.USER_SHIRO_RES + BaseConstant.CONNECTOR_UNDERLINE + username).toString();
        if (StringUtils.isBlank(shiroString)) {
            return null;
        }
        ShiroDataRo shiroDataRo = JSON.parseObject(shiroString, new TypeReference<ShiroDataRo>() {
        });
        if (shiroDataRo != null) {
            result.setPermissions(shiroDataRo.getPermissions());
            result.setRoles(shiroDataRo.getRoles());
            result.setExpire(shiroDataRo.getExpire());
            return result;
        }
        return null;
    }

}
