package com.lvmoney.frame.base.profile.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.profile.util
 * 版本信息: 版本1.0
 * 日期:2020/5/2
 * Copyright XXXXXX科技有限公司
 */


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/2 17:53
 */
public class JasyptUtil {
    /**
     * 加密
     *
     * @param password: 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value:    待加密值
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/2 17:53
     */
    public static String encryptPassword(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.encrypt(value);
        return result;
    }

    /**
     * 解密
     *
     * @param password: password 配置文件中设定的加密密码 jasypt.encryptor.password
     * @param value:    待解密密文
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/2 17:54
     */
    public static String decryptPassword(String password, String value) {
        PooledPBEStringEncryptor encryptOr = new PooledPBEStringEncryptor();
        encryptOr.setConfig(cryptOr(password));
        String result = encryptOr.decrypt(value);
        return result;
    }

    /**
     * salt
     *
     * @param password:
     * @throws
     * @return: org.jasypt.encryption.pbe.handler.SimpleStringPBEConfig
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/2 17:55
     */
    public static SimpleStringPBEConfig cryptOr(String password) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(password);
        config.setAlgorithm(StandardPBEByteEncryptor.DEFAULT_ALGORITHM);
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName(null);
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        return config;
    }

    public static void main(String[] args) {
        // 加密
        System.out.println(encryptPassword("HGho9/nfr23AlRt3pc8KGQ==", "root"));
        // 解密
//        mysql@1234
        System.out.println(decryptPassword("HGho9/nfr23AlRt3pc8KGQ==", "9vw9qFIqH79fZrnataUsclYNtNe1ynHB"));

//        root@1234
//        System.out.println(decyptPwd("EbfYkitulv73I2p0mXI50JMXoaxZTKJ7", "tdHzge8YvviOJaiV/+P6uQ9wgB44D1aH"));
    }
}
