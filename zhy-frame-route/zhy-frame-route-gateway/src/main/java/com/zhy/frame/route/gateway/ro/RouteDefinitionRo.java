package com.zhy.frame.route.gateway.ro;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.ro
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：路由信息redis对象
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 10:04
 */
@Data
public class RouteDefinitionRo implements Serializable {
    private static final long serialVersionUID = -7601694631190949568L;
    private Long expire;
    Map<String, RouteDefinition> routeDefinitions;
}
