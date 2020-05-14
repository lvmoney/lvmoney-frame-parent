package com.zhy.frame.authentication.oauth2.center.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.authentication.common.exception.AuthorityException;
import com.zhy.frame.authentication.oauth2.center.constant.Oauth2ServerConstant;
import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.authentication.oauth2.center.ro.AuthorizationCodeRo;
import com.zhy.frame.authentication.oauth2.center.ro.Oauth2ClientDetailRo;
import com.zhy.frame.authentication.oauth2.center.service.Oauth2RedisService;
import com.zhy.frame.authentication.oauth2.center.vo.AuthorizationVo;
import com.zhy.frame.authentication.oauth2.center.vo.resp.AuthorizationRespVo;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 上午11:28:35
 */
@Service
public class Oauth2RedisServiceImpl implements Oauth2RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Oauth2RedisServiceImpl.class);

    @Autowired
    BaseRedisService baseRedisService;



    @Override
    public BaseClientDetails getClientDetailsByClientId(String clientId) {
        try {
            Object obj = baseRedisService.getByMapKey(Oauth2ServerConstant.REDIS_FRAME_CLIENT_DETAILS_NAME, clientId);
            BaseClientDetails baseClientDetails = JSON.parseObject(obj.toString(), new TypeReference<BaseClientDetails>() {
            });
            return baseClientDetails;
        } catch (Exception e) {
            LOGGER.error("通过clientId获得oauth2客户端详情信息报错:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void clientDetails2Redis(Oauth2ClientDetailRo oauth2ClientDetailRo) {
        baseRedisService.addMap(Oauth2ServerConstant.REDIS_FRAME_CLIENT_DETAILS_NAME, oauth2ClientDetailRo.getData(), oauth2ClientDetailRo.getExpire());
    }


    @Override
    public void authorizationCode2Redis(AuthorizationCodeRo authorizationCodeRo) {
        baseRedisService.addMap(Oauth2ServerConstant.REDIS_FRAME_AUTHENTICATION_CODE_NAME, authorizationCodeRo.getData(), authorizationCodeRo.getExpire());
    }

    @Override
    public void deleteAuthorizationCode(String code) {
        baseRedisService.deleteByMapKey(Oauth2ServerConstant.REDIS_FRAME_AUTHENTICATION_CODE_NAME, code);
    }

    @Override
    public AuthorizationVo getAuthorizationCodeByCode(String code) {
        try {
            Object obj = baseRedisService.getByMapKey(Oauth2ServerConstant.REDIS_FRAME_AUTHENTICATION_CODE_NAME, code);
            AuthorizationRespVo authorizationRespVo = JSON.parseObject(obj.toString(), new TypeReference<AuthorizationRespVo>() {
            });
            AuthorizationVo authorizationVo = authorizationRespVo2AuthorizationVo(authorizationRespVo);
            return authorizationVo;
        } catch (Exception e) {
            throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_CUSTOM_OAUTH_ERROR.getDescription());
        }

    }

    private AuthorizationVo authorizationRespVo2AuthorizationVo(AuthorizationRespVo authorizationRespVo) {
        AuthorizationVo authorizationVo = new AuthorizationVo();
        com.zhy.frame.authentication.oauth2.center.vo.Oauth2Request fOauth2Request = authorizationRespVo.getOAuth2Request();
        OAuth2Request oAuth2Request = new OAuth2Request(fOauth2Request.
                getRequestParameters(), fOauth2Request.getClientId(), null,
                fOauth2Request.isApproved(), fOauth2Request.getScope(),
                null, fOauth2Request.getRedirectUri(), fOauth2Request.
                getResponseTypes(), null);
        authorizationVo.setOAuth2Request(oAuth2Request);
        com.zhy.frame.authentication.oauth2.center.vo.Authentication fAuthorization = authorizationRespVo.getAuthentication();
        Authentication authentication = new Authentication() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return fAuthorization.getDetails();
            }

            @Override
            public Object getPrincipal() {
                return fAuthorization.getPrincipal();
            }

            @Override
            public boolean isAuthenticated() {
                return fAuthorization.isAuthenticated();
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }

            @Override
            public String getName() {
                return fAuthorization.getName();
            }
        };
        authorizationVo.setAuthentication(authentication);
        return authorizationVo;
    }

}
