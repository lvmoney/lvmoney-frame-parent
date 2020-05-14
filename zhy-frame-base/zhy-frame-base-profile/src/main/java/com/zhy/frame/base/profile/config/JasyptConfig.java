package com.zhy.frame.base.profile.config;/**
 * 描述:
 * 包名:com.zhy.frame.base.profile.config
 * 版本信息: 版本1.0
 * 日期:2020/5/2
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.profile.constant.ProfileConstant;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/2 17:38
 */
@Configuration
public class JasyptConfig {
    @Value("${frame.jasypt.password:default}")
    private String password;

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor pooledPBEStringEncryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(ProfileConstant.DEFAULT_TEMP.equals(password) ? ProfileConstant.DEFAULT_PASSWORD : password);
        config.setAlgorithm(ProfileConstant.ALGORITHM);
        config.setKeyObtentionIterations(ProfileConstant.KEY_OBTENTION_ITERATIONS);
        config.setPoolSize(ProfileConstant.POOL_SIZE);
        config.setProviderName(ProfileConstant.PROVIDER_NAME);
        config.setSaltGeneratorClassName(ProfileConstant.SALT_GENERATOR_CLASS_NAME);
        config.setStringOutputType(ProfileConstant.STRING_OUTPUT_TYPE);
        pooledPBEStringEncryptor.setConfig(config);
        return pooledPBEStringEncryptor;
    }
}
