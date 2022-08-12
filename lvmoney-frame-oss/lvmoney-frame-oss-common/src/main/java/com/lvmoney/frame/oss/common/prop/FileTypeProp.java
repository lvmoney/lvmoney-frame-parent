package com.lvmoney.frame.oss.common.prop;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/4/8
 * Copyright 四川******科技有限公司
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月6日 下午4:15:17
 */
@Component
@PropertySource("classpath:fileHeader.properties")
@ConfigurationProperties(prefix = "file")
@Data
public class FileTypeProp {
    private List<String> fileHeader = new ArrayList();

    private List<String> urlFileType = new ArrayList();


    private List<String> fileSize = new ArrayList<>();

    public Map<String, String> getFileHeaderMap() {
        Map<String, String> result = new LinkedHashMap();
        fileHeader.forEach(item -> {
            result.put(item.split("=")[0], item.split("=")[1]);
        });
        return result;
    }


    public Map<String, String> getUrlFileTypeMap() {
        Map<String, String> result = new LinkedHashMap();
        urlFileType.forEach(item -> {
            result.put(item.split("=")[0], item.split("=")[1]);
        });
        return result;
    }

    public Map<String, String> getFileSize() {
        Map<String, String> result = new LinkedHashMap();
        fileSize.forEach(item -> {
            result.put(item.split("=")[0], item.split("=")[1]);
        });
        return result;
    }
}
