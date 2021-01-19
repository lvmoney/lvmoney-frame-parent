package com.lvmoney.frame.route.gateway.controller;

import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.route.gateway.ro.WhiteListRo;
import com.lvmoney.frame.route.gateway.service.WhiteListService;
import com.lvmoney.frame.route.gateway.vo.WhiteListVo;
import com.lvmoney.frame.route.gateway.vo.resp.Oauth2TokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class IndexController {

    @Autowired
    WhiteListService whiteListService;

    @RequestMapping("/hello")
    public String hello(ServerWebExchange exchange) {
        return "hello world";
    }

    @RequestMapping("/timeout")
    public String timeout() {
        try {
            //睡5秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(6000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "timeout";
    }


    /**
     * @describe:初始化模拟数据，以便测试
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 11:51
     */
    @GetMapping("/init")
    public void init() {

        String serverName = "www.provider.com";
        WhiteListRo whiteListRo = new WhiteListRo();
        Set<String> whiteIp = new HashSet() {{
            add("10.20.10.0/16");
        }};
        WhiteListVo whiteListVo1 = new WhiteListVo();
        whiteListVo1.setNetworkSegment(whiteIp);
        whiteListVo1.setServerName(serverName);
        whiteListRo.setData(new HashMap(BaseConstant.MAP_DEFAULT_SIZE) {
            {
                put(serverName, whiteListVo1);
            }
        });
        whiteListService.saveWhiteList2Redis(whiteListRo);
    }
}
