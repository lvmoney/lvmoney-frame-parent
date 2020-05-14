package com.zhy.demo.skywalking.controller;

import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoSkywalkingController {
    @GetMapping(value = "frame/skywalking/test")
    public ApiResult<String> testJwt(@RequestParam("name") String name) {
        return ApiResult.success(name + "jwt");
    }
}
