package com.lvmoney.frame.authentication.oauth2.center.config;

import com.lvmoney.frame.authentication.common.exception.AuthorityException;
import com.lvmoney.frame.authentication.oauth2.center.constant.Oauth2ServerConstant;
import com.lvmoney.frame.authentication.oauth2.center.exception.CustomOauthException;
import com.lvmoney.frame.captcha.common.service.CaptchaService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Component
public class ClientAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientAuthenticationProvider.class);

    @Value("${oauth2.granttype.password.captcha:false}")
    private boolean passwordCaptcha;

    @Autowired
    UserDetailsService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CaptchaService captchaService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            LOGGER.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        } else {
            String presentedPassword = authentication.getCredentials().toString();
            if (!this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                LOGGER.debug("Authentication failed: password does not match stored value");
                throw new BadCredentialsException(this.messages
                        .getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        // 添加额外处理，如验证码等
        Object details = authentication.getDetails();
        if (details instanceof ClientWebAuthenticationDetails) {
            ClientWebAuthenticationDetails clientWebAuthenticationDetails = (ClientWebAuthenticationDetails) details;
            String captcha = captchaService.getValidate(clientWebAuthenticationDetails.getGraphId()).getValue();
            if (!StringUtils.equalsIgnoreCase(clientWebAuthenticationDetails.getInputVerificationCode(), captcha)) {
                throw new CustomOauthException(AuthorityException.Proxy.VERIFICATION_ERROR.getDescription());
            }
            captchaService.deleteValidate(clientWebAuthenticationDetails.getGraphId());
        } else if (details instanceof LinkedHashMap<?, ?>) {

            if (passwordCaptcha) {
                @SuppressWarnings("unchecked")
                Map<String, String> map = (Map<String, String>) details;
                if (map.containsKey(Oauth2ServerConstant.GRANT_TYPE) && StringUtils.equals(Oauth2ServerConstant.PASSWORD, map.get(Oauth2ServerConstant.GRANT_TYPE))) {

                    if (map.containsKey(Oauth2ServerConstant.GRAPH_ID) && map.containsKey(Oauth2ServerConstant.VERIFICATION_CODE)) {
                        String graphId = map.get(Oauth2ServerConstant.GRAPH_ID);
                        String captcha = captchaService.getValidate(graphId).getValue();

                        if (!StringUtils.equalsIgnoreCase(map.get(Oauth2ServerConstant.VERIFICATION_CODE), captcha)) {
                            throw new CustomOauthException(AuthorityException.Proxy.VERIFICATION_ERROR.getDescription());
                        }
                        captchaService.deleteValidate(graphId);
                    } else {
                        throw new CustomOauthException(AuthorityException.Proxy.VERIFICATION_ERROR.getDescription());
                    }
                }
            }

        }

        try {
            UserDetails loadedUser = userService.loadUserByUsername(username);
            if (loadedUser == null) {
                throw new InternalAuthenticationServiceException(
                        "UserDetailsService returned null, which is an interface contract violation");
            }

            return loadedUser;
        } catch (UsernameNotFoundException ex) {
            throw ex;
        } catch (InternalAuthenticationServiceException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException("客户端权限报错:{}", ex);
        }
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
}
