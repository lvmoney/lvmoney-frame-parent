package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.platform.pay.wechat.util
 * 版本信息: 版本1.0
 * 日期:2021/7/19
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/19 16:30
 */
public class RestTemplateUtil {
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * 设置为utf-8
     *
     * @throws
     * @return: org.springframework.web.client.RestTemplate
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/22 17:39
     */
    public static RestTemplate getRestTemplate() {
        //RestTemplate设置编码
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}