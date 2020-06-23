package com.zhy.frame.test.selenium.util;/**
 * 描述:
 * 包名:com.zhy.frame.test.selenium.util
 * 版本信息: 版本1.0
 * 日期:2020/3/24
 * Copyright XXXXXX科技有限公司
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/24 17:38
 */
public class YmlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(YmlUtil.class);

    /**
     * 读取application.yml文件
     *
     * @throws
     * @return: java.util.Map
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/24 17:46
     */
    public static Map readYml() {
        try {
            Yaml yaml = new Yaml();
            ClassPathResource classPathResource = new ClassPathResource("application.yml");
            InputStream resourceAsStream = classPathResource.getInputStream();
            //加载流,获取yaml文件中的配置数据，然后转换为Map，
            Map obj = (Map) yaml.load(resourceAsStream);
            return obj;
        } catch (Exception e) {
            LOGGER.error("读取yml文件报错:{}", e);
            return null;
        }

    }
}
