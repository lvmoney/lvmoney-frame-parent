package com.zhy.frame.authentication.shiro.filter;

import com.zhy.frame.authentication.common.exception.AuthorityException;
import com.zhy.frame.authentication.service.service.UserCommonService;
import com.zhy.frame.authentication.shiro.constant.ShiroConstant;
import com.zhy.frame.authentication.shiro.properties.ShiroConfigProp;
import com.zhy.frame.authentication.shiro.service.ShiroRedisService;
import com.zhy.frame.authentication.shiro.vo.ShiroUriVo;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.ExceptionType;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.core.util.FilterMapUtil;
import com.zhy.frame.core.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FrameShiroFilter extends FormAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrameShiroFilter.class);


    @Autowired
    ShiroRedisService shiroRedisService;
    @Autowired
    ShiroConfigProp shiroConfigProp;
    @Value("${frame.shiro.support:false}")
    String frameSupport;
    @Autowired
    UserCommonService userCommonService;

    @Value("${frame.requestType.support:false}")
    boolean requestTypeSupport;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        if (!SupportUtil.support(frameSupport)) {
            responsePrint(response, AuthorityException.Proxy.SHIRO_SUPPORT_ERROR);
            return false;
        } else if (BaseConstant.SUPPORT_FALSE.equals(frameSupport)) {
            return true;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String servletPath = httpServletRequest.getServletPath();
        // 先判断是否是系统配置的不需要校验的访问，例如登录请求，或者其他静态资源
        Map<String, String> filterChainDefinition = shiroConfigProp.getFilterChainDefinitionMap();
        if (FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, servletPath, ShiroConstant.SHIRO_REQUEST_IGNORE)) {
            // 在这里做判断
            return true;
        }
        // 从 http 请求头中取出
        String token = httpServletRequest.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        if (token == null) {
            responsePrint(response, AuthorityException.Proxy.TOKEN_IS_REQUIRED);
            return false;
        }
        UserVo userVo = userCommonService.getUserVo(token);
        if (userVo == null) {
            responsePrint(response, AuthorityException.Proxy.TOKEN_USER_NOT_EXIST);
            return false;
        }
        String userId = userVo.getUserId();
        String password = userVo.getPassword();
        String sysId = userVo.getSysId();
        //考虑到多个系统的权限角色，需要区分不同的用户和系统，做如下处理
        String username = sysId + BaseConstant.CONNECTOR_UNDERLINE + userId;
        try {
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(shiroToken);
            if (servletPath.endsWith(ShiroConstant.SERVLET_END_WITH)) {
                servletPath = servletPath.substring(0, servletPath.lastIndexOf(ShiroConstant.SERVLET_END_WITH));
            }
            String requestUrl = sysId + BaseConstant.CONNECTOR_UNDERLINE + servletPath;
            if (requestTypeSupport) {
                String requestType = httpServletRequest.getMethod().toUpperCase();
                requestUrl = requestUrl + BaseConstant.CONNECTOR_UNDERLINE + requestType;
            }
            //考虑到到多系统需要用sysId做如下处理
            ShiroUriVo shiroUriVo = shiroRedisService.getShiroUriData(requestUrl);
            if (shiroUriVo != null) {
                List<String> roleList = new ArrayList<>(shiroUriVo.getRole());
                boolean[] roles = subject.hasRoles(roleList);
                boolean isRole = isPass(roles);
                if (isRole) {
                    return true;
                } else {
                    Set<String> permissionSet = shiroUriVo.getPermission();
                    boolean isPermitted = false;
                    for (String permission : permissionSet) {
                        if (subject.isPermitted(permission)) {
                            isPermitted = true;
                            break;
                        }
                    }
                    if (isPermitted) {
                        return true;
                    }

                    responsePrint(response, AuthorityException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
                    return false;
                }
            } else {
                responsePrint(response, AuthorityException.Proxy.SHIRO_URI_EXCEPTIONT);
                return false;
            }
        } catch (Exception e) {
            responsePrint(response, AuthorityException.Proxy.SHIRO_AUTHORIZATION_EXCEPTIONT);
            return false;
        }
    }

    private boolean isPass(boolean[] booleans) {
        boolean result = false;
        for (boolean bool : booleans) {
            if (bool) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void responsePrint(ServletResponse response, ExceptionType exceptionType) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(exceptionType.getCode());
        apiResult.setSuccess(false);
        apiResult.setMsg(exceptionType.getDescription());
        apiResult.setDate(new Date());
        String json = JsonUtil.t2JsonString(apiResult);
        try {
            httpResponse.getWriter().print(json);
        } catch (IOException e) {
            LOGGER.error("shiro报错构造返回值报错{}", e);
        }
    }

}
