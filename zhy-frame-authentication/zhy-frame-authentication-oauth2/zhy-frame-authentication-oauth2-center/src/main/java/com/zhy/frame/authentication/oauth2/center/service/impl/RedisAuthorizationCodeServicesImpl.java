package com.zhy.frame.authentication.oauth2.center.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.config
 * 版本信息: 版本1.0
 * 日期:2019/7/29
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.oauth2.center.ro.AuthorizationCodeRo;
import com.zhy.frame.authentication.oauth2.center.service.Oauth2RedisService;
import com.zhy.frame.authentication.oauth2.center.vo.AuthorizationVo;
import com.zhy.frame.base.core.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/29 17:41
 */
@Service
public class RedisAuthorizationCodeServicesImpl extends RandomValueAuthorizationCodeServices {
    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Value("${oauth2.authorization.code.expire:18000}")
    String expire;

    @Override
    protected void store(String code, OAuth2Authentication oAuth2Authentication) {
        AuthorizationCodeRo authorizationCodeRo = new AuthorizationCodeRo();
        authorizationCodeRo.setExpire(Long.parseLong(expire));
        AuthorizationVo authorizationVo = new AuthorizationVo();
        authorizationVo.setOAuth2Request(oAuth2Authentication.getOAuth2Request());
        authorizationVo.setAuthentication(oAuth2Authentication.getUserAuthentication());
        oAuth2Authentication.getUserAuthentication();
        authorizationCodeRo.setData(new HashMap(BaseConstant.MAP_DEFAULT_SIZE) {{
            put(code, authorizationVo);
        }});
        oauth2RedisService.authorizationCode2Redis(authorizationCodeRo);
    }

    @Override
    protected OAuth2Authentication remove(String code) {
        AuthorizationVo authorizationVo = oauth2RedisService.getAuthorizationCodeByCode(code);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(authorizationVo.getOAuth2Request(), authorizationVo.getAuthentication());
        oauth2RedisService.deleteAuthorizationCode(code);
        return oAuth2Authentication;
    }
}
