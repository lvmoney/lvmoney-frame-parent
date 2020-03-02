package com.zhy.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.core.vo.ServerInfo;
import com.zhy.frame.route.gateway.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 14:34
 */
@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    BaseRedisService baseRedisService;


    @Override
    public ServerInfo getServerInfo(URI uri) {
        Object obj = baseRedisService.getByMapKey(BaseConstant.REDIS_SERVER_CROUP_KEY, uri.getHost().toString());
        try {
            ServerInfo ServerInfo = JSON.parseObject(obj.toString(), new TypeReference<ServerInfo>() {
            });
            return ServerInfo;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ServerInfo getServerInfo(String uri) {
        Object obj = baseRedisService.getMapByKey(BaseConstant.REDIS_SERVER_CROUP_KEY);
        try {
            List<ServerInfo> serverInfos = JSON.parseObject(obj.toString(), new TypeReference<List<ServerInfo>>() {
            });
            ServerInfo serverInfo = serverInfos.stream().filter(e ->
                    e.getUri().equals(uri)
            ).collect(Collectors.toList()).get(0);
            return serverInfo;
        } catch (Exception e) {
            return null;
        }
    }

}
