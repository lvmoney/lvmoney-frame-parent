package com.lvmoney.demo.db.controller;


import com.lvmoney.demo.db.entity.DictionariesInfo;
import com.lvmoney.demo.db.service.DictionariesInfoService;
import com.lvmoney.frame.base.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会用相关字典表 前端控制器
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@RestController
public class DictionariesInfoController {
    @Autowired
    DictionariesInfoService dictionariesInfoService;

    @PostMapping("/save")
    public void save() {
        for (int i = 0; i < 10; i++) {
            DictionariesInfo d = new DictionariesInfo();
            d.setCode("test");
            d.setCreateUid("1");
            d.setRemark("test");
            d.setGroupCode("test");
            d.setLevel(1);
            d.setName("test");
            d.setSysStatus(1);
            d.setUpdateUid("1");
            dictionariesInfoService.save(d);
        }

    }

    @GetMapping("/get")
    public String get() {
        DictionariesInfo d = new DictionariesInfo();
        d.setCode("test");
        d.setCreateUid("1");
        d.setRemark("test");
        d.setGroupCode("test");
        d.setLevel(1);
        d.setName("test");
        d.setSysStatus(1);
        d.setUpdateUid("1");
        d.setCreateUid("1");
//        dictionariesInfoService.saveOrUpdate(d);

//        dictionariesInfoService.getById("test");
        dictionariesInfoService.getDataByName("test");
        return "succ";
    }


    @GetMapping("/error")
    public String error() {
        DictionariesInfo d = new DictionariesInfo();
        d.setCode("test");
        d.setCreateUid("1");
        d.setRemark("test");
        d.setGroupCode("test12");
        d.setLevel(1);
        d.setName("test");
        d.setSysStatus(1);
        d.setUpdateUid("1");
        d.setCreateUid("1");
        dictionariesInfoService.errorTest("test", d);
        return "succ";
    }


}
