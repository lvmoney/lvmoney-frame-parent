package com.zhy.frame.authentication.oauth2.customer.properties;/**
 * 描述:
 * 包名:com.zhy.frame.oauth2.customer.properties
 * 版本信息: 版本1.0
 * 日期:2019/8/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/10 18:32
 */
@Component
@Data
public class OauthClientProp {
    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.scope}")
    private String scope;
    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;
    @Value("${security.oauth2.client.user-authorization-uri}")
    private String userAuthorizationUri;
    @Value("${security.oauth2.resource.token-info-uri}")
    private String tokenInfoUri;
}
