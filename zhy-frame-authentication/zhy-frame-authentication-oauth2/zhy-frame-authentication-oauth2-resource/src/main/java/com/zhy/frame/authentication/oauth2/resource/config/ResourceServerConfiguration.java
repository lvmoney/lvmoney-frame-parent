package com.zhy.frame.authentication.oauth2.resource.config;

import com.zhy.frame.authentication.oauth2.resource.properties.ResourceConfigProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.http.HttpServletResponse;


/**
 * @describe：
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    ResourceConfigProp resourceConfigProp;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //将web登录和oauth登录的manager共享，不然只能有一方生效
//        http
////                .antMatcher("/test")
//                .antMatcher("/user/info")
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();

        String[] resource = new String[resourceConfigProp.getFilterChainDefinitionList().size()];
        for (int i = 0; i < resource.length; i++) {
            resource[i] = resourceConfigProp.getFilterChainDefinitionList().get(i);
        }
        http.csrf().disable().exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and().authorizeRequests()
                .antMatchers(resource)
                .permitAll().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }


}
