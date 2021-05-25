package com.lvmoney.frame.demo.seata.test.controller;

import com.lvmoney.frame.demo.seata.test.po.User;
import com.lvmoney.frame.demo.seata.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class HelloController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public String printDate() {
        return "this is v1";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public int save(User user) {
        return userService.insertUser(user);
    }

    @RequestMapping(value = "/updateStage/{id}", method = RequestMethod.PUT)
    public int update(@PathVariable(value = "id") String id) {
        return userService.updateStage(id);
    }


}