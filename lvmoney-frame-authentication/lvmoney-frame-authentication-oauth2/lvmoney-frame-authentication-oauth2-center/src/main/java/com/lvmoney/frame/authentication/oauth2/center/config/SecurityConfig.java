package com.lvmoney.frame.authentication.oauth2.center.config;

import com.lvmoney.frame.authentication.oauth2.center.properties.CenterConfigProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    ClientAuthenticationFailureHandler clientAuthenticationFailureHandler;

    @Autowired
    ClientAuthenticationSuccessHandler clientAuthenticationSuccessHandler;

    @Autowired
    ClientAccessDeniedHandler clientAccessDeniedHandler;

    @Autowired
    AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    AuthenticationProvider customAuthenticationProvider;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    CenterConfigProp centerConfigProp;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/assets/**");
        web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall() {
        StrictHttpFirewall firewall = new StrictHttpFirewall();
        firewall.setAllowUrlEncodedSlash(true);
        return firewall;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
        String[] center = new String[centerConfigProp.getFilterChainDefinitionList().size()];
        for (int i = 0; i < center.length; i++) {
            center[i] = centerConfigProp.getFilterChainDefinitionList().get(i);
        }

        http
                .authorizeRequests()
                .mvcMatchers(center).permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .logout()
                .logoutUrl(centerConfigProp.getLogoutUrl())
                .logoutSuccessUrl(centerConfigProp.getLogoutSuccessUrl())
                .and()
                .formLogin()
                //重点
                .authenticationDetailsSource(authenticationDetailsSource)
                .failureHandler(clientAuthenticationFailureHandler)
                .successHandler(clientAuthenticationSuccessHandler)
                .loginPage(centerConfigProp.getLoginPage()).loginProcessingUrl(centerConfigProp.getLoginProcessingUrl()).permitAll();

        http.exceptionHandling().accessDeniedHandler(clientAccessDeniedHandler);

    }
}
