package com.lvmoney.demo.webservice.service;/**
 * 描述:
 * 包名:com.chinapopin.demo.webservice.service
 * 版本信息: 版本1.0
 * 日期:2021/1/21
 * Copyright XXXXXX科技有限公司
 */


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/1/21 10:17  
 */
@WebService
public interface WebServiceDemoService {

    @WebMethod
    String hello(@WebParam(name = "name")String name);

}
