package com.zhy.frame.cloud.common.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.cloud.common.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/3/7
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.cloud.common.constant.CloudCommonConstant;
import com.zhy.frame.cloud.common.ro.AuthorizedRo;
import com.zhy.frame.cloud.common.service.AuthorizedService;
import com.zhy.frame.cloud.common.vo.AuthorizedVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/7 22:38
 */
@Service
public class AuthorizedServiceImpl implements AuthorizedService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void addAuthorized2Server(AuthorizedRo authorizedRo) {
        baseRedisService.addMap(CloudCommonConstant.REDIS_AUTHORIZED_SYS, authorizedRo.getData(), authorizedRo.getExpired());

    }

    @Override
    public void deleteAuthorizedByServer(String serverName) {
        baseRedisService.deleteByMapKey(CloudCommonConstant.REDIS_AUTHORIZED_SYS, serverName);
    }

    @Override
    public AuthorizedVo getSysIdByServer(String serverName) {
        try {
            Object obj = baseRedisService.getByMapKey(CloudCommonConstant.REDIS_AUTHORIZED_SYS, serverName);
            Set<String> sydIds = JSON.parseObject(obj.toString(), new TypeReference<Set>() {
            });
            return new AuthorizedVo(sydIds);
        } catch (Exception e) {
            return null;
        }
    }
}
