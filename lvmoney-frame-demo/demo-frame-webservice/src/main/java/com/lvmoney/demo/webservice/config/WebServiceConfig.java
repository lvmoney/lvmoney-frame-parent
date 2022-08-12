package com.lvmoney.demo.webservice.config;/**
 * 描述:
 * 包名:com.chinapopin.demo.webservice.service.config
 * 版本信息: 版本1.0
 * 日期:2021/1/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.webservice.service.WebServiceDemoService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.xml.ws.Endpoint;
import org.apache.cxf.jaxws.EndpointImpl;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/1/21 10:19  
 */
@Configuration
public class WebServiceConfig {

    @Autowired
    private WebServiceDemoService webServiceDemoService;

    /**
     * 注入servlet  bean name不能dispatcherServlet 否则会覆盖dispatcherServlet
     * @return
     */
    @Bean(name = "cxfServlet")
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/webservice/*");
    }


    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    /**
     * 注册WebServiceDemoService接口到webservice服务
     * @return
     */
    @Bean(name = "WebServiceDemoEndpoint")
    public Endpoint sweptPayEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(springBus(), webServiceDemoService);
        endpoint.publish("/webservice");
        return endpoint;
    }

}
