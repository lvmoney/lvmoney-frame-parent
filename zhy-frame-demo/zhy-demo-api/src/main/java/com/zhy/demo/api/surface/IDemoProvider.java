package com.zhy.demo.api.surface;/**
 * 描述:
 * 包名:com.zhy.demo.api.surface
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.api.ApiResult;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:29
 */
public interface IDemoProvider {
    @GetMapping(value = "frame/provider/demo/get")
    ApiResult<String> demoGet(@RequestParam("name") String name);

}
