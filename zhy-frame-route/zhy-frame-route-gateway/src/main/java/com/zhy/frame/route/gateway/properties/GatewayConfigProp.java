package com.zhy.frame.route.gateway.properties;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.properties
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
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
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 14:28
 */
@Component
@PropertySource("classpath:gatewayConfig.properties")
@ConfigurationProperties(prefix = "gateway")
@Data
public class GatewayConfigProp {
    private List<String> filterChainDefinitionList = new ArrayList<String>();

    public Map<String, String> getfilterChainDefinitionMap() {
        Map<String, String> result = new LinkedHashMap<String, String>();
        filterChainDefinitionList.forEach(item -> {
            result.put(item.split("=")[0], item.split("=")[1]);
        });
        return result;
    }
}
