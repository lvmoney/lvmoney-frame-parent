package com.zhy.frame.authentication.jwt.controller;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.jwt.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.zhy.frame.authentication.api.surface.IJwtCheck;
import com.zhy.frame.authentication.api.vo.JwtCheckVo;
import com.zhy.frame.authentication.jwt.service.JwtRedisService;
import com.zhy.frame.authentication.util.service.UserCommonService;
import com.zhy.frame.authentication.util.util.JwtUtil;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 9:17
 */
@RestController
public class IJwtCheckController implements IJwtCheck {
    @Autowired
    JwtRedisService jwtRedisService;
    @Autowired
    UserCommonService userCommonService;

    @Override
    public ApiResult<JwtCheckVo> checkToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        if (StringUtils.isEmpty(token)) {
            return ApiResult.success(new JwtCheckVo(false));
        }
        String userId = "";
        try {
            String verifyToken = JwtUtil.getRealToken(token);
            UserVo userVo = JwtUtil.getUserVo(verifyToken);
            userId = userVo.getUserId();
        } catch (JWTDecodeException j) {
            return ApiResult.success(new JwtCheckVo(false));
        }
        UserVo userVo = userCommonService.getUserVo(token);
        if (userVo == null) {
            return ApiResult.success(new JwtCheckVo(false));
        }
        if (!userId.equals(userVo.getUserId())) {
            return ApiResult.success(new JwtCheckVo(false));
        }
        if (!JwtUtil.checkPassword(token, userVo.getPassword(), userId)) {
            return ApiResult.success(new JwtCheckVo(false));
        }
        return ApiResult.success(new JwtCheckVo(true));
    }
}
