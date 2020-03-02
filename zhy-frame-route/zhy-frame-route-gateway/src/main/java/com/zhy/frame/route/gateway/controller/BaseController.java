package com.zhy.frame.route.gateway.controller;

//import com.lvmoney.common.exceptions.BusinessException;
//import com.lvmoney.common.exceptions.CommonException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class BaseController {
    @RequestMapping("/defaultfallback")
    public void defaultfallback() {
        System.out.println("降级操作...");
//        throw new BusinessException(CommonException.Proxy.SERVER_IS_DOWNGRADE);
    }
}
