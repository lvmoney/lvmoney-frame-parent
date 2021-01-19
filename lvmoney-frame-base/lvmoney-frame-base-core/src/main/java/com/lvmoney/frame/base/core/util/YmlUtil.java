package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2020/6/15
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/15 14:03
 */
public class YmlUtil {


    private static final Logger LOGGER = LoggerFactory.getLogger(YmlUtil.class);

    private LinkedHashMap prop;
    private static YmlUtil ymlUtil = new YmlUtil();
    private static final String YAML_ACTIVE = "spring.profiles.active";
    private static final String YAML_INCLUDE = "spring.profiles.include";
    private static final String YAML_PREFIX = "application";
    private static final String YAML_LOAD_AS = "application.yml";

    /**
     * 私有构造，禁止直接创建
     *
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:44
     */
    private YmlUtil() {
        BootYml yaml = new BootYml();
        yaml.setActive(YAML_ACTIVE);
        yaml.setInclude(YAML_INCLUDE);
        yaml.setPrefix(YAML_PREFIX);
        prop = yaml.loadAs(YAML_LOAD_AS);
    }

    /**
     * 单例
     *
     * @throws
     * @return: com.lvmoney.frame.base.core.util.YmlUtil
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:32
     */
    public static YmlUtil getInstance() {
        if (ymlUtil == null) {
            ymlUtil = new YmlUtil();
        }
        return ymlUtil;
    }

    /**
     * 根据属性名读取值
     * 先去主配置查询，如果查询不到，就去启用配置查询
     *
     * @param name:
     * @throws
     * @return: java.lang.Object
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:31
     */
    public Object getProperty(String name) {
        try {
            LinkedHashMap param = prop;
            List<String> split = StrSpliter.split(name, StrUtil.C_DOT, true, true);
            for (int i = 0; i < split.size(); i++) {
                if (i == split.size() - 1) {
                    return param.get(split.get(i));
                }
                param = Convert.convert(LinkedHashMap.class, param.get(split.get(i)));
            }
        } catch (Exception e) {
            LOGGER.warn("获取yml中的{}属性报错:{}", name, e);
            return null;
        }
        return null;
    }
}
