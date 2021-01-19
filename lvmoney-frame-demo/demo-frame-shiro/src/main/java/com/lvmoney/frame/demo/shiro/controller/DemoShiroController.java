package com.lvmoney.frame.demo.shiro.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.shiro.function
 * 版本信息: 版本1.0
 * 日期:2020/3/4
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.Mapper;
import com.lvmoney.frame.authentication.jwt.annotation.NotToken;
import com.lvmoney.frame.authentication.jwt.service.JwtRedisService;
import com.lvmoney.frame.authentication.uri.annotation.SysServer;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.authentication.util.vo.JwtVo;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.core.ro.UserRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/4 19:50
 */
@RestController
public class DemoShiroController {
    @Autowired
    Mapper mapper;
    @Autowired
    JwtRedisService jwtRedisService;

    @GetMapping("shiro/demo/")
    public ApiResult<String> postUserInfo() {
        return ApiResult.success("shiro demo");
    }

    @GetMapping("shiro/test/")
    public ApiResult<String> demo() {
        return ApiResult.success("shiro test");
    }

    @PostMapping("shiro/token/get")
    @NotToken
    @SysServer(describe = "test")
    public ApiResult<String> getToken(JwtVo jwtVo) {
        String token = JwtUtil.getToken(jwtVo);
        boolean succ = JwtUtil.checkPassword(token, jwtVo.getPassword(), jwtVo.getUserId());
        System.out.println(succ);
        UserRo userRo = mapper.map(jwtVo, UserRo.class);
        userRo.setToken(token);
        jwtRedisService.saveToken2Redis(userRo);
        return ApiResult.success(token);
    }
}
