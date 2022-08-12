package com.lvmoney.frame.authentication.oauth2.customer.config;

import com.lvmoney.frame.authentication.oauth2.customer.properties.CustomerConfigProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: lvmoney
 * @date: 2018/4/10
 */
@EnableOAuth2Sso
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    CustomerConfigProp customerConfigProp;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] customer = new String[customerConfigProp.getFilterChainDefinitionList().size()];
        for (int i = 0; i < customer.length; i++) {
            customer[i] = customerConfigProp.getFilterChainDefinitionList().get(i);
        }
        http.authorizeRequests()
                .antMatchers(customer)
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
