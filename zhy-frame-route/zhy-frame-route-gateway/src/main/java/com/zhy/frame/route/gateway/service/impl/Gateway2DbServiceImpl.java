package com.zhy.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.zhy.k8s.gateway.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/15
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.route.gateway.service.Gateway2DbService;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/15 17:23
 */
@Service
public class Gateway2DbServiceImpl implements Gateway2DbService {
    @Override
    public List<RouteDefinition> initRouteDefinition() {
        System.out.println("test23232");
        return null;
    }
}
