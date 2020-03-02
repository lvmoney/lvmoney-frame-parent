package com.zhy.frame.route.gateway.controller;

import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.route.gateway.ro.WhiteListRo;
import com.zhy.frame.route.gateway.server.AuthenticationServerConfig;
import com.zhy.frame.route.gateway.service.WhiteListService;
import com.zhy.frame.route.gateway.vo.WhiteListVo;
import com.zhy.frame.route.gateway.vo.resp.Oauth2TokenCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@RestController
public class IndexController {

    @Autowired
    private AuthenticationServerConfig authenticationServerConfig;

    @Autowired
    WhiteListService whiteListService;

    @RequestMapping("/hello")
    public String hello() {
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

    @RequestMapping("/token")
    public Oauth2TokenCheck token() {
        String token = "JWT:eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJnZW5kZXIiOm51bGwsInVzZXJfbmFtZSI6InpoYW5nc2FuIiwic2NvcGUiOlsidXNlcl9pbmZvIl0sIm5pa2VuYW1lIjpudWxsLCJleHAiOjE1NjU2ODk0NzMsImdyYW50VHlwZSI6InBhc3N3b3JkIiwiYXV0aG9yaXRpZXMiOltudWxsXSwianRpIjoiOWNiOTk1ZDQtMmUxMi00NzE5LWFjNzQtMDIzNjAxNDE4Mzg5IiwiY2xpZW50X2lkIjoiU2FtcGxlQ2xpZW50SWQiLCJ1c2VybmFtZSI6InpoYW5nc2FuIn0.h59f60l-j2vjpTwhR80H_rtWysPniIKKc0p6-D1EOZtub9ERmoJT4RQ6b2f93ojRkzYVbHstng7d-d-0KOzkTHO9zyso9vRWSVjyM0zTMiENLPsg4b8Ui-fADL9aVFPci5tsvTiasqigP-nh40gclGKH50amOlmnsUIuUXY1l3E";
        ApiResult<Oauth2TokenCheck> oauth2TokenCheck = authenticationServerConfig.authenticationServer(token).oauth2CheckToken();
        return oauth2TokenCheck.getData();
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
