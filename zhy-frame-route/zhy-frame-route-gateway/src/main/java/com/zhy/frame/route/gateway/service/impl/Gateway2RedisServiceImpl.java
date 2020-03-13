package com.zhy.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.route.gateway.constant.GatewayConstant;
import com.zhy.frame.route.gateway.ro.RouteDefinitionRo;
import com.zhy.frame.route.gateway.service.Gateway2RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 9:47
 */
@Service
public class Gateway2RedisServiceImpl implements Gateway2RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(Gateway2RedisServiceImpl.class);

    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public List<RouteDefinition> getRouteDefinition() {
        Object obj = baseRedisService.getMapByKey(GatewayConstant.REDIS_GATEWAY_ROUTE_KEY);

        try {
            List<RouteDefinition> result =
                    JSON.parseObject(obj.toString(), new TypeReference<List<RouteDefinition>>() {
                    });
            return result;
        } catch (Exception e) {
            LOGGER.error("从redis中获得路由信息报错:{}", e.getMessage());
            return null;
        }
    }

    @Override
    public void saveRouteDefinitions(RouteDefinitionRo routeDefinitionRo) {
        baseRedisService.addMap(GatewayConstant.REDIS_GATEWAY_ROUTE_KEY, routeDefinitionRo.getRouteDefinitions(), routeDefinitionRo.getExpire());
    }

    @Override
    public boolean routeIdExist(String routeId) {
        return baseRedisService.isExistMapKey(GatewayConstant.REDIS_GATEWAY_ROUTE_KEY, routeId);
    }

    @Override
    public void deleteRouteId(String routeId) {
        baseRedisService.deleteByMapKey(GatewayConstant.REDIS_GATEWAY_ROUTE_KEY, routeId);
    }

    @Override
    public RouteDefinition getRouteDefinition(String routeId) {
        Object obj = baseRedisService.getByMapKey(GatewayConstant.REDIS_GATEWAY_ROUTE_KEY, routeId);
        try {
            RouteDefinition result = JSON.parseObject(obj.toString(), new TypeReference<RouteDefinition>() {
            });
            return result;
        } catch (Exception e) {
            LOGGER.error("从redis中获得路由信息报错:{}", e.getMessage());
            return null;
        }
    }
}
