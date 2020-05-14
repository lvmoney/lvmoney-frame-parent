package com.zhy.platform.authentication.oauth2.common.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.resource.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.common.constant.AuthConstant;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.platform.authentication.oauth2.common.constant.Oauth2CommonConstant;
import com.zhy.platform.authentication.oauth2.common.ro.ProtectResrouceRo;
import com.zhy.platform.authentication.oauth2.common.service.Oauth2ResourceService;
import com.zhy.platform.authentication.oauth2.common.vo.ProtectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/12 14:23
 */
@Service
public class Oauth2ResourceServiceImpl implements Oauth2ResourceService {

    @Autowired
    BaseRedisService baseRedisService;
    @Value("${spring.application.name:resource}")
    String projectName;

    String protectResourceKey = Oauth2CommonConstant.REDIS_FRAME_OAUTH2_PROTECT_RESOURCE + BaseConstant.CONNECTOR_UNDERLINE + projectName;

    @Override
    public void saveProtectResource2Redis(ProtectResrouceRo protectResrouceRo) {
        baseRedisService.addList(protectResourceKey, protectResrouceRo.getData(), protectResrouceRo.getExpired());
    }

    @Override
    public List<ProtectResource> getProtectResource() {
        List<ProtectResource> result = baseRedisService.getListAll(protectResourceKey);
        return result;
    }
}
