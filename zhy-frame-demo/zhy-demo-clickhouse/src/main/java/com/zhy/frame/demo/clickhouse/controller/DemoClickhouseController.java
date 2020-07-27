package com.zhy.frame.demo.clickhouse.controller;/**
 * 描述:
 * 包名:com.zhy.frame.demo.clickhouse.controller
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.demo.clickhouse.entity.TestTableEntity;
import com.zhy.frame.demo.clickhouse.service.TestTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:33
 */
@RestController
public class DemoClickhouseController {
    @Autowired
    TestTableService testTableService;


    @GetMapping("/save")
    public void save() {
        TestTableEntity testTableEntity = new TestTableEntity();
        testTableEntity.setId(1000L);
        testTableEntity.setCreateDate(new Date());
        testTableEntity.setValue("lvmoney");
        testTableService.save(testTableEntity);
    }
}
