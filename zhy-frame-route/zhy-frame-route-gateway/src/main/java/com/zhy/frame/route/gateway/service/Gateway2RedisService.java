package com.zhy.frame.route.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.route.gateway.ro.RouteDefinitionRo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 9:47
 */
public interface Gateway2RedisService {

    /**
     * 从redis中获得路由规则
     *
     * @throws
     * @return: java.util.List<org.springframework.cloud.gateway.route.RouteDefinition>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:38
     */
    List<RouteDefinition> getRouteDefinition();

    /**
     * 存储路由规则
     *
     * @param routeDefinitionRo: 路由规则实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:38
     */
    void saveRouteDefinitions(RouteDefinitionRo routeDefinitionRo);

    /**
     * 判断路由规则id是否存在
     *
     * @param routeId: 规则id
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:38
     */
    boolean routeIdExist(String routeId);

    /**
     * 删除指定路由规则
     *
     * @param routeId: 规则id
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:39
     */
    void deleteRouteId(String routeId);

    /**
     * 根据路由id获得路由规则
     *
     * @param routeId: 规则id
     * @throws
     * @return: org.springframework.cloud.gateway.route.RouteDefinition
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:39
     */
    RouteDefinition getRouteDefinition(String routeId);
}
