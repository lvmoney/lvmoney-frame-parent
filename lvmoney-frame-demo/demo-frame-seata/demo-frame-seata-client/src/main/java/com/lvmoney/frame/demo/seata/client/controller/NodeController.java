package com.lvmoney.frame.demo.seata.client.controller;

import com.lvmoney.frame.demo.seata.client.service.TestService;
import com.lvmoney.frame.demo.seata.client.vo.req.UpdateReqVo;
import com.lvmoney.frame.demo.seata.client.vo.req.UserReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
@RequestMapping("/fegin")
public class NodeController {
    @Autowired
    TestService testService;

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public int test2(UserReqVo userReqVo) {
        return testService.save(userReqVo);
    }

    @RequestMapping(value = "/test3", method = RequestMethod.GET)
    public boolean test3(UpdateReqVo updateReqVo) {
        return testService.seataService(updateReqVo);
    }
}