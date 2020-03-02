/**
 * 描述:
 * 包名:com.chaoqi.springboot_shiro_redis.properties
 * 版本信息: 版本1.0
 * 日期:2019年1月6日  下午4:15:17
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.oauth2.resource.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月6日 下午4:15:17
 */
@Component
@PropertySource("classpath:oauth2ResourceConfig.properties")
@ConfigurationProperties(prefix = "resource")
public class ResourceConfigProp {
    private List<String> filterChainDefinitionList = new ArrayList<String>();

    public List<String> getFilterChainDefinitionList() {
        return filterChainDefinitionList;
    }

    public void setFilterChainDefinitionList(List<String> filterChainDefinitionList) {
        this.filterChainDefinitionList = filterChainDefinitionList;
    }
}
