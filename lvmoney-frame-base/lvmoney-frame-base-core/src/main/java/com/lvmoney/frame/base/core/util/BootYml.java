package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2020/6/15
 * Copyright XXXXXX科技有限公司
 */

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.text.StrSpliter;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DECIMAL_POINT;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/15 14:04
 */
public class BootYml extends Yaml {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootYml.class);

    /**
     * 环境配置路径的键值
     */
    private String active;
    /**
     * 引入yml的键值
     */
    private String include;
    /**
     * 配置文件的前缀
     */
    private String prefix;

    /**
     * 根据application.yml转化为LinkedHashMap
     *
     * @param path:
     * @throws
     * @return: java.util.LinkedHashMap
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:30
     */
    public LinkedHashMap loadAs(String path) {
        // 组合一个map，把启用的配置，引入的文件组合起来
        LinkedHashMap<String, Object> mapAll = new LinkedHashMap<>();
        LinkedHashMap<String, Object> mainMap = yml2Map(path);
        // 读取启用的配置
        Object active = mainMap.get(this.active);
        if (!ObjectUtils.isEmpty(active)) {
            mapAll.putAll(yml2Map(StrUtil.format("{}-{}.yml", this.prefix, active)));
        }
        // 加载引入的yml
        Object include = mainMap.get(this.include);
        // include是使用逗号分隔开的，需要切割一下
        List<String> split = StrSpliter.split(Convert.toStr(include), StrUtil.C_COMMA, true, true);
        for (String inc : split) {
            mapAll.putAll(yml2Map(StrUtil.format("{}-{}.yml", this.prefix, inc)));
        }
        // 主配置覆盖其他配置
        mapAll.putAll(mainMap);
        // 把map转化为字符串
        String mapString = MapUtil.joinIgnoreNull(mapAll, "\n", "=");
        // 再把map字符串转化为yamlStr字符串
        String yamlStr = properties2YamlStr(mapString);
        // 使用Yaml构建LinkedHashMap
        return super.loadAs(yamlStr, LinkedHashMap.class);
    }

    /**
     * Yml 格式转 LinkedHashMap
     *
     * @param path:
     * @throws
     * @return: java.util.LinkedHashMap<java.lang.String, java.lang.Object>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:30
     */
    public LinkedHashMap<String, Object> yml2Map(String path) {
        final String dot = DECIMAL_POINT;
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        ClassPathResource resource = new ClassPathResource(path);
        // 文件不存在，直接返回
        if (ObjectUtils.isEmpty(resource)) {
            return map;
        }
        BufferedReader reader = resource.getReader(Charset.defaultCharset());
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLParser parser = yamlFactory.createParser(reader);
            StringBuilder key = new StringBuilder();
            String value;
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (!JsonToken.START_OBJECT.equals(token)) {
                    if (JsonToken.FIELD_NAME.equals(token)) {
                        if (key.length() > 0) {
                            key.append(dot);
                        }
                        key.append(parser.getCurrentName());

                        token = parser.nextToken();
                        if (JsonToken.START_OBJECT.equals(token)) {
                            continue;
                        }
                        value = parser.getText();
                        map.put(key.toString(), value);

                        int dotOffset = key.lastIndexOf(dot);
                        if (dotOffset > 0) {
                            key = new StringBuilder(key.substring(0, dotOffset));
                        }
                    } else if (JsonToken.END_OBJECT.equals(token)) {
                        int dotOffset = key.lastIndexOf(dot);
                        if (dotOffset > 0) {
                            key = new StringBuilder(key.substring(0, dotOffset));
                        } else {
                            key = new StringBuilder();
                        }
                    }
                }
                token = parser.nextToken();
            }
            parser.close();
            return map;
        } catch (Exception e) {
            LOGGER.error("yml文件:{}转map报错:{}", path, e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Properties内容转化为yaml内容
     *
     * @param content:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 15:31
     */
    public String properties2YamlStr(String content) {
        // 临时生成yml
        String filePath = FileUtil.getTmpDirPath() + "/temp.yml";
        JsonParser parser;
        JavaPropsFactory factory = new JavaPropsFactory();
        try {
            parser = factory.createParser(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            YAMLFactory yamlFactory = new YAMLFactory();
            YAMLGenerator generator = yamlFactory.createGenerator(FileUtil.getOutputStream(filePath));
            JsonToken token = parser.nextToken();
            while (token != null) {
                if (JsonToken.START_OBJECT.equals(token)) {
                    generator.writeStartObject();
                } else if (JsonToken.FIELD_NAME.equals(token)) {
                    generator.writeFieldName(parser.getCurrentName());
                } else if (JsonToken.VALUE_STRING.equals(token)) {
                    generator.writeString(parser.getText());
                } else if (JsonToken.END_OBJECT.equals(token)) {
                    generator.writeEndObject();
                }
                token = parser.nextToken();
            }
            parser.close();
            generator.flush();
            generator.close();
            // 读取临时生成yml的内容
            String ymlContent = FileUtil.readUtf8String(filePath);
            // 删除临时生成yml
            FileUtil.del(filePath);
            return ymlContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getInclude() {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
