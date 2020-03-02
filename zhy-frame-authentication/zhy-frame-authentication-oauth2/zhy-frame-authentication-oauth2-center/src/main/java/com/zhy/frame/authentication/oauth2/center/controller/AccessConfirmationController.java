package com.zhy.frame.authentication.oauth2.center.controller;

import com.zhy.frame.authentication.oauth2.center.db.dao.OauthClientDao;
import com.zhy.frame.authentication.oauth2.center.db.entity.OauthClient;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.service.ScopeDefinitionService;
import com.zhy.frame.authentication.oauth2.center.vo.ScopeDefinitionVo;
import com.zhy.frame.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Controller
@RequestMapping("/oauth")
@SessionAttributes("authorizationRequest")
public class AccessConfirmationController {

    @Autowired
    ScopeDefinitionService scopeDefinitionService;
    @Autowired
    OauthClientDao oauthClientDao;

    @RequestMapping("/confirm_access")
    public String getAccessConfirmation(@ModelAttribute AuthorizationRequest clientAuth,
                                        ModelMap model,
                                        @RequestParam(value = "redirect_uri", required = false) String redirectUri) {
        OauthClient oauthClient = oauthClientDao.findByClientId(clientAuth.getClientId());
        model.put("auth_request", clientAuth);
        model.put("applicationName", oauthClient.getApplicationName());
        if (StringUtils.isNotEmpty(redirectUri)) {
            model.put("from", getHost(redirectUri));
        }
        Map<String, String> scopes = new LinkedHashMap<>();
        for (String scope : clientAuth.getScope()) {
            ScopeDefinitionVo scopeDefinitionVo = scopeDefinitionService.findByScope(scope);
            if (scopeDefinitionVo != null) {
                scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, scopeDefinitionVo.getDefinition());
            } else {
                scopes.put(OAuth2Utils.SCOPE_PREFIX + scope, scope);
            }
        }
        model.put("scopes", scopes);
        return "accessConfirmation";
    }


    @RequestMapping("/error")
    public void handleError(Map<String, Object> model, HttpServletRequest request) {
        throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_SERVER_ERROR);
    }

    private URI getHost(String url) {
        URI uri = URI.create(url);
        URI effectiveUri = null;
        try {
            effectiveUri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        } catch (Throwable var4) {
            effectiveUri = null;
        }
        return effectiveUri;
    }
}
