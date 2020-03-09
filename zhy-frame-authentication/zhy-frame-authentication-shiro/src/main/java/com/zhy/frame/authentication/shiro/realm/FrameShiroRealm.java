package com.zhy.frame.authentication.shiro.realm;

import com.zhy.frame.authentication.shiro.ro.ShiroDataRo;
import com.zhy.frame.authentication.shiro.service.ShiroRedisService;
import com.zhy.frame.authentication.shiro.vo.ShiroDataVo;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.core.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FrameShiroRealm extends AuthorizingRealm {
    @Autowired
    ShiroRedisService shiroRedisService;

    /**
     * 认证信息.(身份验证) : Authentication 是用来验证用户身份
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String name = token.getUsername();
        String password = String.valueOf(token.getPassword());
        if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
            throw new BusinessException(CommonException.Proxy.SHIRO_UNAUTHORIZED_EXCEPTIONT);
        }
        UserVo user = new UserVo();
        user.setUsername(name);
        user.setPassword(password);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                // 用户
                user,
                // 密码
                user.getPassword(),
                // realm name
                getName()
        );
        return authenticationInfo;
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        if (principal instanceof UserVo) {
            UserVo userLogin = (UserVo) principal;
            String userName = userLogin.getUsername();
            ShiroDataVo shiroDataVo = shiroRedisService.getShiroData(userName);
            if (shiroDataVo == null) {
                throw new BusinessException(CommonException.Proxy.SHIRO_REDIS_NOT_EXSIT);
            }
            Set<String> roles = shiroDataVo.getRoles();
            if (ObjectUtils.allNotNull(roles)) {
                authorizationInfo.addRoles(roles);
            }
            Set<String> permissions = shiroDataVo.getPermissions();
            if (ObjectUtils.allNotNull(permissions)) {
                authorizationInfo.addStringPermissions(permissions);
            }
            ShiroDataRo shiroDataRo = new ShiroDataRo();
            shiroDataRo.setUsername(userName);
            shiroDataRo.setRoles(roles);
            shiroDataRo.setPermissions(permissions);
            shiroDataRo.setExpire(shiroDataVo.getExpire());
            shiroRedisService.saveShiroData(shiroDataRo);
        }
        return authorizationInfo;
    }

}
