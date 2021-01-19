package com.lvmoney.frame.route.gateway.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.route.gateway.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/7/16
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.route.gateway.constant.GatewayConstant;
import com.lvmoney.frame.route.gateway.service.RepeatSubmitService;
import com.lvmoney.frame.route.gateway.vo.RepeatSubmitVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/16 9:30
 */
@Service
public class RepeatSubmitServiceImpl implements RepeatSubmitService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public List<RepeatSubmitVo> getRepeatSubmitVoByServerName(String serverName) {
        Object obj = baseRedisService.getListAll(GatewayConstant.REPEAT_SUBMIT_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName);
        List<RepeatSubmitVo> repeatSubmitVoList = JSON.parseObject(obj.toString(), new TypeReference<List<RepeatSubmitVo>>() {
        });
        return repeatSubmitVoList;
    }
}
