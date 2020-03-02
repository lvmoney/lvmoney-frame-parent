package com.zhy.frame.authentication.oauth2.center.config;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.config
 * 版本信息: 版本1.0
 * 日期:2020/1/14
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.common.constant.AuthConstant;
import com.zhy.frame.authentication.oauth2.center.constant.Oauth2ServerConstant;
import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.platform.authentication.oauth2.common.constant.Oauth2CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/14 10:02
 */
public class ShortMsgTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "short_msg";
    UserDetailsService userDetailsService;


    public ShortMsgTokenGranter(UserDetailsService userDetailsService,
                                AuthorizationServerTokenServices authorizationServerTokenServices,
                                ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(authorizationServerTokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        // 客户端提交的用户名
        String username = parameters.get("telephone");
        // 客户端提交的验证码
        String password = parameters.get("password");
        username = Oauth2CommonConstant.TOKEN_GRANTER_SHORT_MSG + AuthConstant.UNDERLINE + username;
        // 从库里查用户
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if (user == null) {
            throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_USER_NOT_EXIST.getDescription());
        }
        String rPass = user.getPassword();
        if (!new BCryptPasswordEncoder().matches(password, rPass)) {
            throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_PASSWORD_ERROR.getDescription());
        }

        Authentication userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        // 关于user.getAuthorities(): 我们的自定义用户实体是实现了
        // org.springframework.security.core.userdetails.UserDetails 接口的, 所以有
        // user.getAuthorities()
        // 当然该参数传null也行
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        OAuth2Request result = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(result, userAuth);
    }
}
