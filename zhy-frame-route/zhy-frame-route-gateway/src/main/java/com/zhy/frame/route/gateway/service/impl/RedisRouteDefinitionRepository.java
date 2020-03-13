package com.zhy.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.route.gateway.ro.RouteDefinitionRo;
import com.zhy.frame.route.gateway.service.Gateway2RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 9:44
 */
@Service
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {


    @Autowired
    private Gateway2RedisService gateway2RedisService;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions =
                gateway2RedisService.getRouteDefinition();
        return Flux.fromIterable(routeDefinitions);
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route
                .flatMap(routeDefinition -> {
                    RouteDefinitionRo routeDefinitionRo = new RouteDefinitionRo();
                    routeDefinitionRo.setRouteDefinitions(new HashMap(BaseConstant.MAP_DEFAULT_SIZE) {{
                        put(routeDefinition.getId(), routeDefinition);
                    }});
                    gateway2RedisService.saveRouteDefinitions(routeDefinitionRo);
                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (gateway2RedisService.routeIdExist(routeId.toString())) {
                gateway2RedisService.deleteRouteId(routeId.toString());
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
        });
    }


}