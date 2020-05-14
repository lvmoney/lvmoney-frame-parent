package com.zhy.frame.authentication.oauth2.center.service.impl;

import com.zhy.frame.authentication.common.exception.AuthorityException;
import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.authentication.oauth2.center.service.Oauth2RedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;


/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class RedisClientDetailsServiceImpl implements ClientDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisClientDetailsServiceImpl.class);

    @Autowired
    Oauth2RedisService oauth2RedisService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails baseClientDetails = oauth2RedisService.getClientDetailsByClientId(clientId);
        if (ObjectUtils.anyNotNull(baseClientDetails)) {
            return baseClientDetails;
        } else {
            baseClientDetails = oauth2RedisService.getClientDetailsByClientId(clientId);
            if (baseClientDetails == null) {
                LOGGER.error("客户端clientid:{}详细信息未找到", clientId);
                throw new CustomOauthException(AuthorityException.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST.getDescription());
            }
            return baseClientDetails;
        }
    }


}
