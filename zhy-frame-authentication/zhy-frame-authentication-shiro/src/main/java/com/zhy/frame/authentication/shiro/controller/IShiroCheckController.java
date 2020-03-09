package com.zhy.frame.authentication.shiro.controller;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.shiro.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.api.ao.ShiroCheckAo;
import com.zhy.frame.authentication.api.surface.IShiroCheck;
import com.zhy.frame.authentication.api.vo.ShiroCheckVo;
import com.zhy.frame.authentication.shiro.constant.ShiroConstant;
import com.zhy.frame.authentication.shiro.service.ShiroRedisService;
import com.zhy.frame.authentication.shiro.vo.ShiroUriVo;
import com.zhy.frame.authentication.util.service.UserCommonService;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.vo.UserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 9:24
 */
@RestController
public class IShiroCheckController implements IShiroCheck {
    @Autowired
    ShiroRedisService shiroRedisService;
    @Autowired
    UserCommonService userCommonService;

    @Override
    public ApiResult<ShiroCheckVo> checkAuthority(ShiroCheckAo shiroCheckAo) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        String servletPath = shiroCheckAo.getPath();
        if (StringUtils.isEmpty(token)) {
            return ApiResult.success(new ShiroCheckVo(false));
        }
        UserVo userVo = userCommonService.getUserVo(token);
        if (userVo == null) {
            return ApiResult.success(new ShiroCheckVo(false));
        }
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        try {
            UsernamePasswordToken shiroToken = new UsernamePasswordToken(username, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(shiroToken);
            if (servletPath.endsWith(ShiroConstant.SERVLET_END_WITH)) {
                servletPath = servletPath.substring(0, servletPath.lastIndexOf(ShiroConstant.SERVLET_END_WITH));
            }
            ShiroUriVo shiroUriVo = shiroRedisService.getShiroUriData(servletPath);
            if (shiroUriVo != null) {
                List<String> roleList = new ArrayList<>(shiroUriVo.getRole());
                boolean[] roles = subject.hasRoles(roleList);
                boolean isRole = isPass(roles);
                if (isRole) {
                    return ApiResult.success(new ShiroCheckVo(true));
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
                        return ApiResult.success(new ShiroCheckVo(true));
                    }
                    return ApiResult.success(new ShiroCheckVo(false));
                }
            } else {
                return ApiResult.success(new ShiroCheckVo(false));
            }
        } catch (Exception e) {
            return ApiResult.success(new ShiroCheckVo(false));
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
}
