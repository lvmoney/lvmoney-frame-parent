package com.lvmoney.frame.oss.minio.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.minio.config
 * 版本信息: 版本1.0
 * 日期:2021/2/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.oss.minio.prop.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/2/8 17:07
 */
@Configuration
@EnableConfigurationProperties({MinioProperties.class})
public class MinioConfiguration {

    @Autowired
    private MinioProperties properties;

    @Bean
    MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(properties.getEndpoint(), properties.getPort(), properties.getSecure())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
    }
}
