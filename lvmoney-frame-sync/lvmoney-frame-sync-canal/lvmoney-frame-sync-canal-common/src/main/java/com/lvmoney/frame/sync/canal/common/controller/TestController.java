package com.lvmoney.frame.sync.canal.common.controller;/**
 * 描述:
 * 包名:com.lvmoney.bigdata.canal.redis.function
 * 版本信息: 版本1.0
 * 日期:2019/7/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.vo.CorePage;
import com.lvmoney.frame.sync.canal.common.service.CanalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/21 20:43
 */
@RestController
public class TestController {
    @Autowired
    CanalService canalService;

    @RequestMapping("/list")
    public CorePage aaa(String key, int pNum, int size, boolean all) {
        CorePage corePage = new CorePage();
        corePage.setPageNo(pNum);
        corePage.setPageSize(size);
        corePage.setAll(all);
        return canalService.getData(corePage, key);
    }
}
