package com.lvmoney.frame.authentication.oauth2.center.config;

import com.lvmoney.frame.authentication.oauth2.center.service.Oauth2RedisService;
import com.lvmoney.frame.authentication.oauth2.common.service.Oauth2CommonService;
import com.lvmoney.frame.authentication.oauth2.common.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class CustomTokenEnhancer implements TokenEnhancer {
    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    Oauth2CommonService oauth2CommonService;

    /**
     * 自定义一些token属性
     *
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        final Map<String, Object> additionalInformation = new HashMap<>(6);
        // Important !,client_credentials mode ,no user!
        if (authentication.getUserAuthentication() != null) {
            // 与登录时候放进去的UserDetail实现类一致
            String username = (String) authentication.getUserAuthentication().getName();
            UserInfo user = oauth2CommonService.getOauth2UserVo(username);
            additionalInformation.put("grantType", authentication.getOAuth2Request().getGrantType());
            additionalInformation.put("username", user.getUsername());
            additionalInformation.put("nikename", user.getNickname());
            additionalInformation.put("gender", user.getGender());
        }
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        return accessToken;
    }

}
