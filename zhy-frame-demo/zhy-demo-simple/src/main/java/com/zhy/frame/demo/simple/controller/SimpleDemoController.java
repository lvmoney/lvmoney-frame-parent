package com.zhy.frame.demo.simple.controller;/**
 * 描述:
 * 包名:com.zhy.frame.demo.simple.controller
 * 版本信息: 版本1.0
 * 日期:2020/6/28
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/28 14:54
 */
@RestController
public class SimpleDemoController {

    @GetMapping(value = "frame/simple/demo")
    public ApiResult<String> test(String test) {
        return ApiResult.success("simple demo" + test);
    }

    @GetMapping(value = "frame/simple/error")
    public ApiResult<String> error() {
        int n = 100 / 0;
        throw new BusinessException(CommonException.Proxy.BASE64_ENCODING_ERROR);

    }


}
