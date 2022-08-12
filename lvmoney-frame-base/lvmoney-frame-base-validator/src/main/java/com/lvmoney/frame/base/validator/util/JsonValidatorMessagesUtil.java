package com.lvmoney.frame.base.validator.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2021/12/14
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.core.io.ClassPathResource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/14 16:43
 */
public class JsonValidatorMessagesUtil {
    public static Properties props;
    private static final String VALIDATION_MESSAGES = "JsonValidatorMessages.properties";

    static {
        try {
            readPropertiesFile(VALIDATION_MESSAGES);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties readPropertiesFile(String filePath) throws FileNotFoundException, IOException {
        try {
            ClassPathResource classPathResource = new ClassPathResource(filePath);
            InputStream inputStream = classPathResource.getInputStream();
            props = new Properties();
            props.load(new InputStreamReader(inputStream, "UTF-8"));
            return props;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getProperty(String key) {
        String methodName = props.getProperty(key);
        return methodName;
    }
}
