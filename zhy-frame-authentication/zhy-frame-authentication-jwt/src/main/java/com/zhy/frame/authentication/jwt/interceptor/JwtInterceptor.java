/**
 * 描述:
 * 包名:com.zhy.jwt.interceptor
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:35:10
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.jwt.interceptor;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.zhy.frame.authentication.common.constant.AuthConstant;
import com.zhy.frame.authentication.common.exception.AuthorityException;
import com.zhy.frame.authentication.jwt.annotation.NotToken;
import com.zhy.frame.authentication.jwt.constant.JwtConstant;
import com.zhy.frame.authentication.jwt.properties.JwtConfigProp;
import com.zhy.frame.authentication.jwt.service.JwtRedisService;
import com.zhy.frame.authentication.jwt.util.FilterMapUtil;
import com.zhy.frame.authentication.util.service.UserCommonService;
import com.zhy.frame.authentication.util.util.JwtUtil;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.core.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:35:10
 */

public class JwtInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    JwtRedisService jwtRedisService;
    @Value("${frame.jwt.support:false}")
    String frameSupport;
    @Autowired
    JwtConfigProp jwtConfigProp;
    @Autowired
    UserCommonService userCommonService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        if (!SupportUtil.support(frameSupport)) {
            throw new BusinessException(AuthorityException.Proxy.TOKEN_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE.equals(frameSupport)) {
            return super.preHandle(httpServletRequest, httpServletResponse, object);

        }
        String servletPath = httpServletRequest.getServletPath();
        Map<String, String> filterChainDefinition = jwtConfigProp.getFilterChainDefinitionMap();
        if (filterChainDefinition != null && FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, servletPath, JwtConstant.JWT_REQUEST_IGNORE)) {
            // 在这里做判断
            return super.preHandle(httpServletRequest, httpServletResponse, object);
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotToken注释，有则跳过认证
        if (method.isAnnotationPresent(NotToken.class)) {
            NotToken passToken = method.getAnnotation(NotToken.class);
            if (passToken.required()) {
                return super.preHandle(httpServletRequest, httpServletResponse, object);
            }
        } else {
            // 正常情况，不需要认证的少数，如果不配置不认证，那么默认需要认证
            // 执行认证

            // 获取 token 中的 user id
            // 从 http 请求头中取出
            String token = httpServletRequest.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
            if (token == null) {
                throw new BusinessException(AuthorityException.Proxy.TOKEN_IS_REQUIRED);
            }
            if (token.startsWith(AuthConstant.TOKEN_OAUTH2_PREFIX)) {
                return super.preHandle(httpServletRequest, httpServletResponse, object);
            }
            // 如果不是映射到方法直接通过
            if (!(object instanceof HandlerMethod)) {
                return super.preHandle(httpServletRequest, httpServletResponse, object);
            }


            String userId;
            try {
                String verifyToken = JwtUtil.getRealToken(token);
                UserVo userVo = JwtUtil.getUserVo(verifyToken);
                userId = userVo.getUserId();
            } catch (JWTDecodeException j) {
                throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_ID_ERROR);
            }
            UserVo userVo = userCommonService.getUserVo(token);
            if (userVo == null) {
                throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_NOT_EXIST);
            }
            if (!userId.equals(userVo.getUserId())) {
                throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_NOT_EXIST);
            }
            if (!JwtUtil.checkPassword(token, userVo.getPassword(), userId)) {
                throw new BusinessException(AuthorityException.Proxy.TOKEN_VERIFY_ERROR);
            }
            return super.preHandle(httpServletRequest, httpServletResponse, object);
        }
        return super.preHandle(httpServletRequest, httpServletResponse, object);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
