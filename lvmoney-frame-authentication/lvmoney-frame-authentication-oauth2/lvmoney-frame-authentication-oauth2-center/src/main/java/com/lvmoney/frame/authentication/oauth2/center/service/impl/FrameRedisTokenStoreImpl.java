//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lvmoney.frame.authentication.oauth2.center.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.authentication.common.exception.AuthorityException;
import com.lvmoney.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.lvmoney.frame.authentication.oauth2.center.vo.FrameOauth2AccessToken;
import com.lvmoney.frame.authentication.oauth2.center.vo.FrameOauth2RefreshToken;
import com.lvmoney.frame.authentication.oauth2.center.vo.resp.Oauth2AuthenticationRespVo;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.ExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月16日 下午1:30:33
 */
@Service
public class FrameRedisTokenStoreImpl implements TokenStore {
    @Autowired
    BaseRedisService baseRedisService;
    private static final String ACCESS = "access:";
    private static final String AUTH_TO_ACCESS = "auth_to_access:";
    private static final String AUTH = "auth:";
    private static final String REFRESH_AUTH = "refresh_auth:";
    private static final String ACCESS_TO_REFRESH = "access_to_refresh:";
    private static final String REFRESH = "refresh:";
    private static final String REFRESH_TO_ACCESS = "refresh_to_access:";
    private static final String CLIENT_ID_TO_ACCESS = "client_id_to_access:";
    private static final String UNAME_TO_ACCESS = "uname_to_access:";

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    private String prefix = "";


    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }


    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String key = authenticationKeyGenerator.extractKey(authentication);
        String rKey = AUTH_TO_ACCESS + key;
        Object obj = baseRedisService.getByKey(rKey);
        if (obj == null) {
            return null;
        }
        FrameOauth2AccessToken frameOauth2AccessToken = JSON.parseObject(obj.toString(), new TypeReference<FrameOauth2AccessToken>() {
        });
        return frameOauth2AccessToken;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        String rKey = AUTH + token;
        Object obj = baseRedisService.getByKey(rKey);
        if (obj == null) {
            return null;
        }
        Oauth2AuthenticationRespVo authorizationRespVo = JSON.parseObject(obj.toString(), new TypeReference<Oauth2AuthenticationRespVo>() {
        });
        OAuth2Authentication auth = authorizationRespVo2AuthorizationVo(authorizationRespVo);
        return auth;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthenticationForRefreshToken(token.getValue());
    }

    public OAuth2Authentication readAuthenticationForRefreshToken(String token) {
        try {
            String rKey = REFRESH_AUTH + token;
            Object obj = baseRedisService.getByKey(rKey);
            if (obj == null) {
                return null;
            }
            Oauth2AuthenticationRespVo authorizationRespVo = JSON.parseObject(obj.toString(), new TypeReference<Oauth2AuthenticationRespVo>() {
            });
            OAuth2Authentication auth = authorizationRespVo2AuthorizationVo(authorizationRespVo);
            return auth;
        } catch (Exception e) {
            throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_TOKEN_AUTHENTICATION_ERROR.getDescription());
        }
    }


    private OAuth2Authentication authorizationRespVo2AuthorizationVo(Oauth2AuthenticationRespVo authorizationRespVo) {
        com.lvmoney.frame.authentication.oauth2.center.vo.Oauth2Request fOauth2Request = authorizationRespVo.getOAuth2Request();
        OAuth2Request oAuth2Request = new OAuth2Request(fOauth2Request.
                getRequestParameters(), fOauth2Request.getClientId(), null,
                fOauth2Request.isApproved(), fOauth2Request.getScope(),
                null, fOauth2Request.getRedirectUri(), fOauth2Request.
                getResponseTypes(), null);
        com.lvmoney.frame.authentication.oauth2.center.vo.Authentication fAuthorization = authorizationRespVo.getUserAuthentication();
        if (fAuthorization == null) {
            return new OAuth2Authentication(oAuth2Request, null);
        } else {
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
                    return fAuthorization == null ? null : fAuthorization.getDetails();
                }

                @Override
                public Object getPrincipal() {
                    return fAuthorization == null ? null : fAuthorization.getPrincipal();
                }

                @Override
                public boolean isAuthenticated() {
                    return fAuthorization == null ? null : fAuthorization.isAuthenticated();
                }

                @Override
                public void setAuthenticated(boolean b) throws IllegalArgumentException {
                }

                @Override
                public String getName() {
                    return fAuthorization == null ? null : fAuthorization.getName();
                }
            };
            return new OAuth2Authentication(oAuth2Request, authentication);
        }
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        try {
            String accessKey = ACCESS + token.getValue();
            baseRedisService.setString(accessKey, token);
            String authKey = AUTH + token.getValue();
            baseRedisService.setString(authKey, authentication);
            String authToAccessKey = AUTH_TO_ACCESS + authenticationKeyGenerator.extractKey(authentication);
            baseRedisService.setString(authToAccessKey, token);
            String approvalKey = UNAME_TO_ACCESS + getApprovalKey(authentication);
            if (!authentication.isClientOnly()) {
                baseRedisService.addList(approvalKey, new ArrayList() {{
                    add(token);
                }});
            }
            String clientId = CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId();

            baseRedisService.addList(clientId, new ArrayList() {{
                add(token);
            }});
            if (token.getExpiration() != null) {
                Long seconds = Long.parseLong(String.valueOf(token.getExpiresIn()));
                baseRedisService.setExpire(accessKey, seconds);
                baseRedisService.setExpire(authKey, seconds);
                baseRedisService.setExpire(authToAccessKey, seconds);
                baseRedisService.setExpire(clientId, seconds);
                baseRedisService.setExpire(approvalKey, seconds);
            }
            OAuth2RefreshToken refreshToken = token.getRefreshToken();
            if (refreshToken != null && refreshToken.getValue() != null) {
                String refreshToAccessKey = REFRESH_TO_ACCESS + token.getRefreshToken().getValue();
                baseRedisService.setString(refreshToAccessKey, token.getValue());
                String accessToRefreshKey = ACCESS_TO_REFRESH + token.getValue();
                baseRedisService.setString(accessToRefreshKey, token.getRefreshToken().getValue());
                if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
                    ExpiringOAuth2RefreshToken expiringRefreshToken = (ExpiringOAuth2RefreshToken) refreshToken;
                    Date expiration = expiringRefreshToken.getExpiration();
                    if (expiration != null) {
                        Long seconds = Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L);
                        baseRedisService.setExpire(refreshToAccessKey, seconds);
                        baseRedisService.setExpire(accessToRefreshKey, seconds);
                    }
                }
            }
        } catch (Exception e) {

            throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_STORE_ACCESS_TOKEN_ERROR.getDescription());

        }
    }

    private static String getApprovalKey(OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication() == null ? ""
                : authentication.getUserAuthentication().getName();
        return getApprovalKey(authentication.getOAuth2Request().getClientId(), userName);
    }

    private static String getApprovalKey(String clientId, String userName) {
        return clientId + (userName == null ? "" : ":" + userName);
    }

    @Override
    public void removeAccessToken(OAuth2AccessToken accessToken) {
        removeAccessToken(accessToken.getValue());
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        String rKey = ACCESS + tokenValue;
        Object obj = baseRedisService.getByKey(rKey);
        if (obj == null) {
            throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_ACCESS_TOKEN_ERROR.getDescription());
        }
        FrameOauth2AccessToken frameOauth2AccessToken = JSON.parseObject(obj.toString(), new TypeReference<FrameOauth2AccessToken>() {
        });
        return frameOauth2AccessToken;
    }

    public void removeAccessToken(String tokenValue) {
        String accessKey = ACCESS + tokenValue;
        Object obj = baseRedisService.getByKey(accessKey);
        FrameOauth2AccessToken access = JSON.parseObject(obj.toString(), new TypeReference<FrameOauth2AccessToken>() {
        });
        baseRedisService.deleteByKey(accessKey);
        String authKey = AUTH + tokenValue;
        Object authObj = baseRedisService.getByKey(authKey);
        Oauth2AuthenticationRespVo authorizationRespVo = JSON.parseObject(authObj.toString(), new TypeReference<Oauth2AuthenticationRespVo>() {
        });
        OAuth2Authentication authentication = authorizationRespVo2AuthorizationVo(authorizationRespVo);
        baseRedisService.deleteByKey(authKey);
        String accessToRefreshKey = ACCESS_TO_REFRESH + tokenValue;
        baseRedisService.deleteByKey(accessToRefreshKey);
        try {
            if (authentication != null) {
                String key = authenticationKeyGenerator.extractKey(authentication);
                String authToAccessKey = AUTH_TO_ACCESS + key;
                String unameKey = UNAME_TO_ACCESS + getApprovalKey(authentication);
                String clientId = CLIENT_ID_TO_ACCESS + authentication.getOAuth2Request().getClientId();
                baseRedisService.deleteByKey(authToAccessKey);
                baseRedisService.rmValueByList(unameKey, 0L, access);
                baseRedisService.rmValueByList(clientId, 0L, access);
                baseRedisService.deleteByKey(ACCESS + key);
            }
        } catch (Exception e) {
            throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_RM_ACCESS_TOKEN_ERROR.getDescription());
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        String refreshKey = REFRESH + refreshToken.getValue();
        baseRedisService.setString(refreshKey, refreshToken);
        String refreshAuthKey = REFRESH_AUTH + refreshToken.getValue();
        baseRedisService.setString(refreshAuthKey, authentication);
        if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
            ExpiringOAuth2RefreshToken expiringRefreshToken = (ExpiringOAuth2RefreshToken) refreshToken;
            Date expiration = expiringRefreshToken.getExpiration();
            if (expiration != null) {
                Long seconds = Long.valueOf((expiration.getTime() - System.currentTimeMillis()) / 1000L);
                baseRedisService.setExpire(refreshKey, seconds);
                baseRedisService.setExpire(refreshAuthKey, seconds);
            }
        }
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
        String rKey = REFRESH + tokenValue;
        Object obj = baseRedisService.getByKey(rKey);
        if (obj == null) {
            return null;
        }
        FrameOauth2RefreshToken result = JSON.parseObject(obj.toString(), new TypeReference<FrameOauth2RefreshToken>() {
        });
        return result;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken refreshToken) {
        removeRefreshToken(refreshToken.getValue());
    }

    public void removeRefreshToken(String tokenValue) {
        String refreshKey = REFRESH + tokenValue;
        baseRedisService.deleteByKey(refreshKey);
        String refreshAuthKey = REFRESH_AUTH + tokenValue;
        baseRedisService.deleteByKey(refreshAuthKey);
        String refresh2AccessKey = REFRESH_TO_ACCESS + tokenValue;
        baseRedisService.deleteByKey(refresh2AccessKey);
        String access2RefreshKey = ACCESS_TO_REFRESH + tokenValue;
        baseRedisService.deleteByKey(access2RefreshKey);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        removeAccessTokenUsingRefreshToken(refreshToken.getValue());
    }

    private void removeAccessTokenUsingRefreshToken(String refreshToken) {
        String rKey = REFRESH_TO_ACCESS + refreshToken;
        String accessToken = baseRedisService.getByKey(rKey).toString();
        removeAccessToken(accessToken);
        baseRedisService.deleteByKey(rKey);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        String rKey = UNAME_TO_ACCESS + getApprovalKey(clientId, userName);
        List list = baseRedisService.getListAll(rKey);
        List<FrameOauth2AccessToken> frameOauth2AccessTokens = JSON.parseObject(JsonUtil.t2JsonString(list), new TypeReference<List<FrameOauth2AccessToken>>() {
        });
        return Collections.<OAuth2AccessToken>unmodifiableCollection(frameOauth2AccessTokens);
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        String rKey = CLIENT_ID_TO_ACCESS + clientId;
        List list = baseRedisService.getListAll(rKey);
        List<FrameOauth2AccessToken> frameOauth2AccessTokens = JSON.parseObject(JsonUtil.t2JsonString(list), new TypeReference<List<FrameOauth2AccessToken>>() {
        });
        return Collections.<OAuth2AccessToken>unmodifiableCollection(frameOauth2AccessTokens);
    }
}
