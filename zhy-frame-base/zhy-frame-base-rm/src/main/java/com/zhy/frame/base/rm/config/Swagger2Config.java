/**
 * 描述:
 * 包名:com.zhy.common.util
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.base.rm.config;

import com.zhy.frame.base.core.constant.BaseConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：不要打开swaager的配置， 因为其使用方式可能和其他监控工具不一样会造成冲突，
 * 建议在对应有servlet的module中自行添加
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "frame.swagger.support", havingValue = "true")
public class Swagger2Config {
    /**
     * 标题
     */
    @Value("${swagger.title:swagger后台接口}")
    private String title;

    /**
     * 基本包
     */
    @Value("${swagger.base.package:com.zhy.frame}")
    private String basePackage;

    /**
     * 描述
     */
    @Value("${swagger.description:后台系统接口展示}")
    private String description;

    /**
     * URL
     */
    @Value("${swagger.url:www.baidu.com}")
    private String url;

    /**
     * 作者
     */
    @Value("${swagger.contact.name:智慧游架构组}")
    private String contactName;

    /**
     * 作者网址
     */
    @Value("${swagger.contact.url:www.baidu.com}")
    private String contactUrl;

    /**
     * 作者邮箱
     */
    @Value("${swagger.contact.email:1300515928@qq.com}")
    private String contactEmail;

    /**
     * 版本
     */
    @Value("${swagger.version:2.0}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 自行修改为自己的包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build().securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                //服务条款网址
                .termsOfServiceUrl(url)
                .version(version)
                .contact(new Contact(contactName, contactUrl, contactEmail))
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> apiKeyList = new ArrayList();
        apiKeyList.add(new ApiKey("token", "token", "header"));
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContexts = new ArrayList<>();
        securityContexts.add(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build());
        return securityContexts;
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope =
                new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> securityReferences = new ArrayList<>();
        securityReferences.add(new SecurityReference(BaseConstant.AUTHORIZATION_TOKEN_KEY, authorizationScopes));
        return securityReferences;
    }
}
