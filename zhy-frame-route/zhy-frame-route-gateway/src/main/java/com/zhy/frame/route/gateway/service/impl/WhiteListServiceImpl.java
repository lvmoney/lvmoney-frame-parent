package com.zhy.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.route.gateway.constant.GatewayConstant;
import com.zhy.frame.route.gateway.ro.WhiteListRo;
import com.zhy.frame.route.gateway.service.WhiteListService;
import com.zhy.frame.route.gateway.vo.WhiteListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:52
 */
@Service
public class WhiteListServiceImpl implements WhiteListService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void saveWhiteList2Redis(WhiteListRo whiteListRo) {
        baseRedisService.addMap(GatewayConstant.SERVER_WHILTE_LIST, whiteListRo.getData(), whiteListRo.getExpired());
    }

    @Override
    public WhiteListVo getWhiteList(String serverName) {
        Object obj = baseRedisService.getByMapKey(GatewayConstant.SERVER_WHILTE_LIST, serverName);
        WhiteListVo whiteListVo = JSON.parseObject(obj.toString(), new TypeReference<WhiteListVo>() {
        });
        return whiteListVo;
    }

    @Override
    public boolean isExist(String serverName) {
        return baseRedisService.isExistMapKey(GatewayConstant.SERVER_WHILTE_LIST, serverName);
    }
}
