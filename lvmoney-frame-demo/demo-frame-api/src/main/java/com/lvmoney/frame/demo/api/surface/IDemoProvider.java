package com.lvmoney.frame.demo.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.demo.api.surface
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:29
 */
public interface IDemoProvider {
    @GetMapping(value = "frame/provider/fallback")
    ApiResult<String> fallback(@RequestParam("name") String name);

    @GetMapping(value = "frame/provider/hystrix")
    ApiResult<String> hystrix(@RequestParam("name") String name);


    @GetMapping(value = "frame/provider/test/{name}")
    ApiResult<String> test(@PathVariable("name") String name);
}
