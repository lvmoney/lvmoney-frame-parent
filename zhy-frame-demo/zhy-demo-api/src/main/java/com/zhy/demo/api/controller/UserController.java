package com.zhy.demo.api.controller;/**
 * 描述:
 * 包名:com.zhy.tmc.api.controller
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.user.exception.UserException;
import com.zhy.frame.authentication.jwt.annotation.NotToken;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.cache.common.annation.CacheImpl;
import com.zhy.frame.cache.common.service.CacheCommonService;
import com.zhy.demo.user.service.UserService;
import com.zhy.demo.user.vo.req.GetUserReqVo;
import com.zhy.demo.user.vo.req.UserLoginReqVo;
import com.zhy.demo.user.vo.resp.GetUserRespVo;
import com.zhy.demo.user.vo.resp.UserLoginRespVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 16:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @CacheImpl
    CacheCommonService baseRedisService;


    @GetMapping("getJ")
    @NotToken
    @ApiOperation(value = "通过用户名获得用户信息", notes = "通过用户名获得用户信息")
    public ApiResult<GetUserRespVo> getUser(@Validated @RequestBody GetUserReqVo getUserReqVo) {
        GetUserRespVo getUserRespVo = userService.getUser(getUserReqVo);
        return ApiResult.success(getUserRespVo);
    }


    @GetMapping("get")
    @NotToken
    @ApiOperation(value = "通过用户名获得用户信息", notes = "通过用户名获得用户信息")
    public ApiResult<GetUserRespVo> getUserDetail(@Validated GetUserReqVo getUserReqVo) {
        GetUserRespVo getUserRespVo = userService.getUser(getUserReqVo);
        return ApiResult.success(getUserRespVo);
    }

    @GetMapping("login")
    @NotToken
    public ApiResult<UserLoginRespVo> userLogin(UserLoginReqVo userLoginReqVo) {
        UserLoginRespVo userLoginRespVo = userService.userLogin(userLoginReqVo);
        return ApiResult.success(userLoginRespVo);
    }

    @GetMapping("error")
    @NotToken
    public void error() {
        LOGGER.warn("用户名或者密码不匹配");
        throw new BusinessException(UserException.Proxy.USER_LOGIN_CHECK_ERROR);
    }
}
