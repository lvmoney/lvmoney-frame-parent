package com.lvmoney.frame.dispatch.feign.server;

import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@FeignClient(name = "fegin1", url = "http://localhost:8070", configuration = FeignConfig.class)
public interface FeginJaegerServer {

    /**
     * 测试方法
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:35
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    String jaeger();
}
