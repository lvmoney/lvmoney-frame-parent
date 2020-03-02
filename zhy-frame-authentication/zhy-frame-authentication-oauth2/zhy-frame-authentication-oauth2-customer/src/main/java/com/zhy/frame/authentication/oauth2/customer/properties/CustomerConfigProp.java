/**
 * 描述:
 * 包名:com.chaoqi.springboot_shiro_redis.properties
 * 版本信息: 版本1.0
 * 日期:2019年1月6日  下午4:15:17
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.oauth2.customer.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月6日 下午4:15:17
 */
@Component
@PropertySource("classpath:oauth2CustomerConfig.properties")
@ConfigurationProperties(prefix = "customer")
public class CustomerConfigProp {
    private List<String> filterChainDefinitionList = new ArrayList<String>();

    public List<String> getFilterChainDefinitionList() {
        return filterChainDefinitionList;
    }

    public void setFilterChainDefinitionList(List<String> filterChainDefinitionList) {
        this.filterChainDefinitionList = filterChainDefinitionList;
    }
}
