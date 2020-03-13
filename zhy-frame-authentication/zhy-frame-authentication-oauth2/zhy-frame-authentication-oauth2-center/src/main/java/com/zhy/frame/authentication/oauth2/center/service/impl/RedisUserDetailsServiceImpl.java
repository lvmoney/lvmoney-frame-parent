package com.zhy.frame.authentication.oauth2.center.service.impl;

import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.service.Db2RedisService;
import com.zhy.frame.authentication.oauth2.center.service.Oauth2RedisService;
import com.zhy.platform.authentication.oauth2.common.service.Oauth2CommonService;
import com.zhy.platform.authentication.oauth2.common.vo.UserInfo;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:40
 */
@Service
public class RedisUserDetailsServiceImpl implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUserDetailsServiceImpl.class);

    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    Db2RedisService db2RedisService;
    @Autowired
    Oauth2CommonService oauth2CommonService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserInfo userInfo = oauth2CommonService.getOauth2UserVo(username);
        if (ObjectUtils.anyNotNull(userInfo)) {
            return userInfo;
        } else {
            db2RedisService.loadUserByUsername(username);
            userInfo = oauth2CommonService.getOauth2UserVo(username);
            if (userInfo == null) {
                throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST.getDescription());
            }
            return userInfo;
        }


    }
}
