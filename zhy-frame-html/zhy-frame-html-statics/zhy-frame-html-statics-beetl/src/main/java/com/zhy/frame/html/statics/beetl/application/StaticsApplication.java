package com.zhy.frame.html.statics.beetl.application;/**
 * 描述:
 * 包名:com.zhy.frame.html.statics.application
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.html.statics.beetl.config.BeetlConfig;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 10:22
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class StaticsApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaticsApplication.class, args);
    }

    @Bean(initMethod = "init", name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        //获取Spring Boot 的ClassLoader
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if (loader == null) {
            loader = BeetlConfig.class.getClassLoader();
        }
        //beetlGroupUtilConfiguration.setConfigProperties(extProperties);//额外的配置，可以覆盖默认配置，一般不需要
        ClasspathResourceLoader cploder = new ClasspathResourceLoader(loader,
                "templates/");
        beetlGroupUtilConfiguration.setResourceLoader(cploder);
        beetlGroupUtilConfiguration.init();
        //如果使用了优化编译器，涉及到字节码操作，需要添加ClassLoader
        beetlGroupUtilConfiguration.getGroupTemplate().setClassLoader(loader);
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        //设置后缀后即可在controller中使用 return "/hello" 而不必 return "hello.html"
        beetlSpringViewResolver.setSuffix(".html");
        return beetlSpringViewResolver;
    }


}
