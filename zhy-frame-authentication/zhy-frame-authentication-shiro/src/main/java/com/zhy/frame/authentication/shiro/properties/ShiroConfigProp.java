/**
 * 描述:
 * 包名:com.chaoqi.springboot_shiro_redis.properties
 * 版本信息: 版本1.0
 * 日期:2019年1月6日  下午4:15:17
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.shiro.properties;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月6日 下午4:15:17
 */
@Component
@PropertySource("classpath:shiroConfig.properties")
@ConfigurationProperties(prefix = "shiro")
public class ShiroConfigProp {
    private String loginUrl;
    private String successUrl;
    private List<String> filterChainDefinitionList = new ArrayList<String>();

    public Map<String, String> getFilterChainDefinitionMap() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        filterChainDefinitionList.forEach(item -> {
            result.put(item.split("=")[0], item.split("=")[1]);
        });
        return result;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public List<String> getFilterChainDefinitionList() {
        return filterChainDefinitionList;
    }

    public void setFilterChainDefinitionList(List<String> filterChainDefinitionList) {
        this.filterChainDefinitionList = filterChainDefinitionList;
    }
}
