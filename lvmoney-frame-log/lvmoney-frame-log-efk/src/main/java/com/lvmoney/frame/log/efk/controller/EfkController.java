package com.lvmoney.frame.log.efk.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.log.efk.function
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 10:15
 */
@RestController
public class EfkController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EfkController.class);

    @GetMapping(value = "frame/efk/test")
    public ApiResult<String> testJwt() {
        LOGGER.info("this is a test");
        return ApiResult.success("success");
    }
}
