package com.zhy.frame.db.sharding.base.controller;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.controller
 * 版本信息: 版本1.0
 * 日期:2019/9/10
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.db.sharding.base.dao.UserDao;
import com.zhy.frame.db.sharding.base.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/10 15:35
 */
@RestController
public class UserController {
    @Autowired
    private UserDao userMapper;

    @GetMapping("/user/save")
    @ResponseBody
    public String save() {
        int length = 10;
        for (int i = 0; i < length; i++) {
            User user = new User();
            user.setName("test" + i);
            user.setNum(i % 2 == 0 ? 1 : 2);
            user.setPhone("11111111" + i);
            user.setCode("xxxxx");
            user.setCode("eeeeeeeeeeee");
            userMapper.insert(user);
        }

        return "success";
    }

    @GetMapping("/user/get/{id}")
    @ResponseBody
    public User get(@PathVariable Long id) {
        User user = userMapper.selectById(id);
        System.out.println(user.getId());
        return user;
    }
}
