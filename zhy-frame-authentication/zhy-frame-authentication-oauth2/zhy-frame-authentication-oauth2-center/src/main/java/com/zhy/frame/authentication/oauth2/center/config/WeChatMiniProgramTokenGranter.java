package com.zhy.frame.authentication.oauth2.center.config;//package com.zhy.frame.authentication.oauth2.center.config;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.zhy.frame.authentication.oauth2.center.db.dao.ThirdPartyAccountDao;
//import com.zhy.frame.authentication.oauth2.center.vo.RoleEnum;
//import com.zhy.frame.authentication.oauth2.center.vo.UserInfo;
//import com.zhy.frame.authentication.oauth2.center.db.entity.ThirdPartyAccount;
//import com.zhy.frame.common.util.JacksonUtil;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
//import org.springframework.security.oauth2.provider.*;
//import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
//import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.util.*;
//
///**
// * 微信小程序登陆，返回JWT
// */
//public class WeChatMiniProgramTokenGranter extends AbstractTokenGranter {
//
//    private static final String GRANT_TYPE = "wechat_mini";
//    private String weChatCodeUrl = "https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={secret}&js_code={code}&grant_type=authorization_code";
//    private String appId;
//    private String secret;
//
//    ThirdPartyAccountDao thirdPartyAccountDao;
//    UserDetailsService userDetailsService;
//
//    RestTemplate restTemplate = new RestTemplate();
//
//    public WeChatMiniProgramTokenGranter(UserDetailsService userDetailsService,
//                                         ThirdPartyAccountDao thirdPartyAccountDao,
//                                         AuthorizationServerTokenServices authorizationServerTokenServices,
//                                         ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,
//                                         String appId,
//                                         String secret,
//                                         String weChatCodeUrl) {
//        super(authorizationServerTokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
//        this.userDetailsService = userDetailsService;
//        this.thirdPartyAccountDao = thirdPartyAccountDao;
//        this.appId = appId;
//        this.secret = secret;
//        if (StringUtils.isNoneBlank(weChatCodeUrl)) {
//            this.weChatCodeUrl = weChatCodeUrl;
//        }
//    }
//
//    @Override
//    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
//
//        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
//        String code = parameters.get("code"); // 客户端提交的用户名
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("appId", appId);
//        params.put("secret", secret);
//        params.put("code", code);
//        String result = restTemplate.getForObject(weChatCodeUrl, String.class, params);
//
//        try {
//            Map<String, String> openIdMap = JacksonUtil.JSONStringToObject(result, new TypeReference<Map<String, String>>() {
//            });
//            if (openIdMap.containsKey("openid")) {
//                String openId = openIdMap.get("openid");
//                ThirdPartyAccount thirdPartyAccount = thirdPartyAccountDao.findByThirdPartyAndThirdPartyAccountId(GRANT_TYPE, openId);
//                if (thirdPartyAccount == null) {
//                    thirdPartyAccount = new ThirdPartyAccount();
//                    thirdPartyAccount.setClientId(client.getClientId());
//                    thirdPartyAccount.setThirdParty(GRANT_TYPE);
//                    thirdPartyAccount.setThirdPartyAccountId(openId);
//                    thirdPartyAccount.setAccountOpenCode(UUID.randomUUID().toString());
//                    thirdPartyAccountDao.save(thirdPartyAccount);
//                }
//
//                UserInfo user = new UserInfo(1, openId, "123", getAuthorities(RoleEnum.ROLE_USER.name()));
//                Authentication userAuth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//                // 关于user.getAuthorities(): 我们的自定义用户实体是实现了
//                // org.springframework.security.core.userdetails.UserDetails 接口的, 所以有
//                // user.getAuthorities()
//                // 当然该参数传null也行
//                ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
//
//                OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
//                return new OAuth2Authentication(storedOAuth2Request, userAuth);
//            } else {
//                throw new InvalidGrantException("获取openid失败");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new InvalidGrantException("获取openid失败");
//        }
//
//
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
//        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
//    }
//
//
//}
