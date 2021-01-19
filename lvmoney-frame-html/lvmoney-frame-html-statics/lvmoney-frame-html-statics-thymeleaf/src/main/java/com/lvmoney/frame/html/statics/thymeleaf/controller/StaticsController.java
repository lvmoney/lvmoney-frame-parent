package com.lvmoney.frame.html.statics.thymeleaf.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.html.statics.function
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.html.statics.common.service.HtmlStaticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 10:22
 */
@RestController
public class StaticsController {
    @Autowired
    HtmlStaticsService htmlStaticsService;

    @GetMapping(value = "frame/static/hello/{name}")
    public ModelAndView hello(@RequestParam("name") String name) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("hello", name);
        ModelAndView modelAndView = new ModelAndView("hello", model);
        htmlStaticsService.buildHtmlPage(modelAndView);
        return modelAndView;
    }

}
