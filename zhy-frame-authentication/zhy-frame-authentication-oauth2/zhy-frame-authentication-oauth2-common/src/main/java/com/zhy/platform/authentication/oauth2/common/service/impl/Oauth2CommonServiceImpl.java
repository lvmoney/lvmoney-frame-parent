package com.zhy.platform.authentication.oauth2.common.service.impl;/**
 * 描述:
 * 包名:com.zhy.platform.authentication.oauth2.common.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/2/18
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.platform.authentication.oauth2.common.constant.Oauth2CommonConstant;
import com.zhy.platform.authentication.oauth2.common.ro.Oauth2UserRo;
import com.zhy.platform.authentication.oauth2.common.service.Oauth2CommonService;
import com.zhy.platform.authentication.oauth2.common.vo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/2/18 19:10
 */
@Service
public class Oauth2CommonServiceImpl implements Oauth2CommonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Oauth2CommonServiceImpl.class);

    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void userDetails2Redis(Oauth2UserRo oauth2UserRo) {
        baseRedisService.addMap(Oauth2CommonConstant.REDIS_FRAME_USER_DETAILS_NAME, oauth2UserRo.getData(), oauth2UserRo.getExpire());
    }

    @Override
    public UserInfo getOauth2UserVo(String username) {
        Object obj = this.baseRedisService.getByMapKey("REDIS_FRAME_USER_DETAILS_NAME", username);

        try {
            UserInfo userInfo = (UserInfo) JSON.parseObject(obj.toString(), new TypeReference<UserInfo>() {
            }, new Feature[0]);
            return userInfo;
        } catch (Exception var4) {
            LOGGER.error("通过用户名获得oauth2用户信息报错:{}", var4.getMessage());
            return null;
        }
    }
}
