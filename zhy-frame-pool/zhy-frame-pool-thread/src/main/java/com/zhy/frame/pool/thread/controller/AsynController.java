package com.zhy.frame.pool.thread.controller;/**
 * 描述:
 * 包名:com.zhy.frame.base.asyn.function
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.pool.thread.service.impl.SynService;
import com.zhy.frame.pool.thread.vo.UncaughtExceptionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 10:42
 */
@RestController
public class AsynController {
    @Autowired
    SynService synService;

    @GetMapping(value = "asyn/test")
    public ApiResult<String> testJwt() {
        synService.asyncMethodWithVoidReturnType(new UncaughtExceptionVo("123", "123", null, "123"));
        return ApiResult.success("ok");
    }
}
