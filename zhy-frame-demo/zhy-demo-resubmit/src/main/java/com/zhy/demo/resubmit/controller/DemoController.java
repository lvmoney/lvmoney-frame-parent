package com.zhy.demo.resubmit.controller;/**
 * 描述:
 * 包名:com.zhy.demo.provider.function
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.resubmit.ao.TestAo;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.repeatsubmit.annotation.NoRepeatSubmit;
import com.zhy.frame.cache.repeatsubmit.enums.NoRepeatSubmitEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 17:40
 */
@RestController
public class DemoController {

    @NoRepeatSubmit(required = NoRepeatSubmitEnum.ALLOW)
    @GetMapping(value = "frame/resubmit/allow")
    public ApiResult<String> allow() {
        return ApiResult.success("allow");
    }

    @NoRepeatSubmit(required = NoRepeatSubmitEnum.NOT_ALLOW)
    @GetMapping(value = "frame/resubmit/notAllow")
    public ApiResult<String> notAllow() {
        return ApiResult.success("notAllow");
    }

    @NoRepeatSubmit(required = NoRepeatSubmitEnum.PARTLY)
    @GetMapping(value = "frame/resubmit/file")
    public ApiResult<String> file() {
        return ApiResult.success("file");
    }


    @NoRepeatSubmit(required = NoRepeatSubmitEnum.NOT_ALLOW)
    @GetMapping(value = "frame/resubmit/input")
    public ApiResult<String> input(TestAo testAo) {
        return ApiResult.success("file");
    }
}
