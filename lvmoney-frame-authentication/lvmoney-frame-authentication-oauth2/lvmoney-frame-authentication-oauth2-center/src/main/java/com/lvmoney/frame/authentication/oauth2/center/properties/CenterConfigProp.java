/**
 * 描述:
 * 包名:com.chaoqi.springboot_shiro_redis.properties
 * 版本信息: 版本1.0
 * 日期:2019年1月6日  下午4:15:17
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.authentication.oauth2.center.properties;

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
@PropertySource("classpath:oauth2CenterConfig.properties")
@ConfigurationProperties(prefix = "center")
public class CenterConfigProp {
    private List<String> filterChainDefinitionList = new ArrayList<String>();
    /**
     * 登出地址
     */
    private String logoutUrl;
    /**
     * 登出成功地址
     */
    private String logoutSuccessUrl;

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getLogoutSuccessUrl() {
        return logoutSuccessUrl;
    }

    public void setLogoutSuccessUrl(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public String getLoginProcessingUrl() {
        return loginProcessingUrl;
    }

    public void setLoginProcessingUrl(String loginProcessingUrl) {
        this.loginProcessingUrl = loginProcessingUrl;
    }

    /**
     * 登录页面
     */
    private String loginPage;
    /**
     * 登录通过地址
     */
    private String loginProcessingUrl;

    public List<String> getFilterChainDefinitionList() {
        return filterChainDefinitionList;
    }

    public void setFilterChainDefinitionList(List<String> filterChainDefinitionList) {
        this.filterChainDefinitionList = filterChainDefinitionList;
    }
}
