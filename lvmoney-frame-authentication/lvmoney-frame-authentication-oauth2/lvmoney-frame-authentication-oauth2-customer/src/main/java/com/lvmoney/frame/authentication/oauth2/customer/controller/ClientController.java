package com.lvmoney.frame.authentication.oauth2.customer.controller;

import com.lvmoney.frame.authentication.oauth2.customer.properties.OauthClientProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月16日 下午1:34:08
 */
@RestController
public class ClientController {
    @Autowired
    OauthClientProp oauthClientProp;

    @GetMapping("/user")
    public String getUser() {
        return "user";
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/index")
    public String userIndex(String code) {
        return code;
    }
}
