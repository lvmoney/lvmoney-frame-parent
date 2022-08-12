package com.lvmoney.demo.sharding.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.controller
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.sharding.entity.Toca;
import com.lvmoney.demo.sharding.service.TocaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 11:07
 */
@RestController
public class ShardingDemoController {
    @Autowired
    TocaService tocaService;

    @PostMapping("demo/sharding/save")
    public void save() {
        Toca t = new Toca();
        t.setCode("test");
//        t.setId(1000L);
        t.setName("test");
        t.setPhone("test");
        t.setNum(100);
        t.setTenantId(4000L);
        tocaService.batchSave(t);
    }

    @GetMapping("demp/sharding/get")
    public String get() {
        return "succ";
    }
}
