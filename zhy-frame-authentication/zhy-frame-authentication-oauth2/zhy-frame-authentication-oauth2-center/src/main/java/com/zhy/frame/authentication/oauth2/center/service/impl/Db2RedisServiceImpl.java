package com.zhy.frame.authentication.oauth2.center.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/6
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.authentication.oauth2.center.constant.Oauth2ServerConstant;
import com.zhy.frame.authentication.oauth2.center.db.dao.OauthClientDao;
import com.zhy.frame.authentication.oauth2.center.db.dao.UserAccountDao;
import com.zhy.frame.authentication.oauth2.center.db.dao.UserRoleDao;
import com.zhy.frame.authentication.oauth2.center.db.entity.OauthClient;
import com.zhy.frame.authentication.oauth2.center.db.entity.UserAccount;
import com.zhy.frame.authentication.oauth2.center.db.entity.UserRole;
import com.zhy.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.ro.Oauth2ClientDetailRo;
import com.zhy.frame.authentication.oauth2.center.ro.Oauth2UserRo;
import com.zhy.frame.authentication.oauth2.center.service.Db2RedisService;
import com.zhy.frame.authentication.oauth2.center.service.Oauth2RedisService;
import com.zhy.frame.authentication.oauth2.center.vo.UserInfo;
import com.zhy.frame.core.constant.BaseConstant;
import com.zhy.frame.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/6 14:16
 */
@Service
public class Db2RedisServiceImpl implements Db2RedisService {
    @Autowired
    Oauth2RedisService oauth2RedisService;
    @Autowired
    OauthClientDao oauthClientDao;
    @Autowired
    UserAccountDao userAccountDao;
    @Autowired
    UserRoleDao userRoleDao;
    @Value("${oauth2.expired:18000}")
    String expired;


    @Override
    public void loadUserByUsername(String username) {
        UserAccount userAccount = userAccountDao.findByUsername(username);
        List<UserRole> userRoles = userRoleDao.findRoleByUserId(userAccount.getUserAccid());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        userRoles.forEach(e -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(e.getCode()));
        });
        Map<String, UserInfo> data = new HashMap<String, UserInfo>(BaseConstant.MAP_DEFAULT_SIZE);
        Long userid = Long.parseLong(userAccount.getUserAccid());
        String userName = userAccount.getUsername();
        String password = userAccount.getPassword();
        UserInfo oauth2UserVo = new UserInfo(userid, userName, password,
                userAccount.getValid() > 0, true, true, userAccount.getValid() != Oauth2ServerConstant.OAUTH2_USER_LOCKED, grantedAuthorityList);
        oauth2UserVo.setNickname(userAccount.getNickName());
        oauth2UserVo.setGender(userAccount.getGender());
        data.put(username, oauth2UserVo);
        Oauth2UserRo oauth2UserRo = new Oauth2UserRo();
        oauth2UserRo.setExpire(Long.parseLong(expired));
        oauth2UserRo.setData(data);
        oauth2RedisService.userDetails2Redis(oauth2UserRo);
    }

    @Override
    public void loadClientDetailsByClientId(String clientId) {
        OauthClient oauthClient = oauthClientDao.findByClientId(clientId);
        if (oauthClient == null) {
            throw new CustomOauthException(Oauth2Exception.Proxy.OAUTH2_CLIENT_DETAIL_NO_EXIST.getDescription());
        }
        Oauth2ClientDetailRo oauth2ClientDetailRo = new Oauth2ClientDetailRo();
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(oauthClient.getClientId());
        baseClientDetails.setClientSecret(oauthClient.getClientSecret());
        baseClientDetails.setRefreshTokenValiditySeconds(oauthClient.getRefreshTokenValidity());
        baseClientDetails.setAccessTokenValiditySeconds(oauthClient.getAccessTokenValidity());
        String scopeStr = oauthClient.getScope();
        Set<String> scope = jsonString2Set(scopeStr);
        baseClientDetails.setScope(scope);
        String authCrantTypeStr = oauthClient.getAuthorizedGrantTypes();
        Set<String> authCrantType = jsonString2Set(authCrantTypeStr);
        baseClientDetails.setAuthorizedGrantTypes(authCrantType);
        String redirectUriStr = oauthClient.getWebServerRedirectUri();
        Set<String> redirectUri = jsonString2Set(redirectUriStr);
        baseClientDetails.setRegisteredRedirectUri(redirectUri);
        String grantedAuthorityStr = oauthClient.getAuthorities();
        Set<String> grantedAuthority = jsonString2Set(grantedAuthorityStr);
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthority.forEach(e -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(e));
        });
        baseClientDetails.setAuthorities(grantedAuthorityList);
        String approveStr = oauthClient.getAuthorities();
        Set<String> approveScopes = jsonString2Set(approveStr);
        baseClientDetails.setAutoApproveScopes(approveScopes);
        Map<String, BaseClientDetails> data = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
        data.put(clientId, baseClientDetails);
        oauth2ClientDetailRo.setData(data);
        oauth2ClientDetailRo.setExpire(Long.parseLong(expired));
        oauth2RedisService.clientDetails2Redis(oauth2ClientDetailRo);
    }

    private Set<String> jsonString2Set(String str) {
        if (StringUtil.isEmpty(str)) {
            return null;
        }
        return JSONObject.parseObject(str, new TypeReference<Set<String>>() {
        });
    }
}
