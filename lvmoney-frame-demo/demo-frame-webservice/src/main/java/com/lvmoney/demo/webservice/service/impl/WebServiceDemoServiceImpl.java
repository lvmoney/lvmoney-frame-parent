package com.lvmoney.demo.webservice.service.impl;/**
 * 描述:
 * 包名:com.chinapopin.demo.webservice.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/1/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.webservice.service.WebServiceDemoService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/1/21 10:17  
 */
@Service
@WebService(serviceName = "WebServiceDemoService", // 与接口中指定的name一致
        targetNamespace = "http://webservice.business.mixpay.com", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.lvmoney.demo.webservice.service.WebServiceDemoService" // 接口地址
)
public class WebServiceDemoServiceImpl implements WebServiceDemoService {

    @Override
    public String hello(String name) {
        return "hello"+name;
    }


}
