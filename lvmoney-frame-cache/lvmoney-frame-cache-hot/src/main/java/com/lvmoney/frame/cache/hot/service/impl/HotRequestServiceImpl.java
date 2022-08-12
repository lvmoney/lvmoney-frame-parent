package com.lvmoney.frame.cache.hot.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.caffeine.service.CaffeineService;
import com.lvmoney.frame.cache.hot.ro.HotRequestRo;
import com.lvmoney.frame.cache.hot.service.HotRequestService;
import com.lvmoney.frame.cache.lock.constant.LockConstant;
import com.lvmoney.frame.cache.lock.service.DistributedLockerService;
import com.lvmoney.frame.cache.lock.utils.ParamUtil;
import com.lvmoney.frame.cache.redis.constant.RedisConstant;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 16:51
 */
@Service
public class HotRequestServiceImpl implements HotRequestService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotRequestServiceImpl.class);

    @Autowired
    BaseRedisService baseRedisService;

    @Autowired
    DistributedLockerService distributedLockerService;
    /**
     * 服务名
     */
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    /**
     * 秒和毫秒的转化
     */
    private static final Integer SEC_CONVERT = 1000;
    @Autowired
    CaffeineService caffeineService;


    @Override
    public void save(HotRequestRo hotRequestRo) {
        Map<String, String> reqVo = ParamUtil.buildRequestMap(hotRequestRo.getQ());
        baseRedisService.setString(RedisConstant.HOT_REQUEST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + hotRequestRo.getUrl() + BaseConstant.CONNECTOR_UNDERLINE + ParamUtil.buildParam(reqVo), hotRequestRo, hotRequestRo.getExpired());
    }

    @Override
    public HotRequestRo getHotRequestRo(String url, Map<String, String> reqVo) {
        try {
            Object obj = baseRedisService.getByKey(RedisConstant.HOT_REQUEST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + url + BaseConstant.CONNECTOR_UNDERLINE + ParamUtil.buildParam(reqVo));
            HotRequestRo hotRequestRo = JSON.parseObject(obj.toString(), new TypeReference<HotRequestRo>() {
            });
            return hotRequestRo;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public void update(HotRequestRo hotRequestRo, long threshold, int section) {
        distributedLockerService.lock(RedisConstant.HOT_REQUEST_UPDATE_LOCK_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + hotRequestRo.getUrl(), TimeUnit.SECONDS, LockConstant.LOCK_TIME);
        long counter = hotRequestRo.getCounter();
        long now = System.currentTimeMillis();
        int seq = (int) ((now - hotRequestRo.getStart()) / SEC_CONVERT);
        if (seq > section && counter < threshold) {
            //如果间隔时间超过了检查区间且阀值没有达到，则重置数据
            hotRequestRo.setCounter(1L);
            hotRequestRo.setStart(now);
        } else if (counter >= threshold) {
            //如果达到阀值，保存数据到caffeine
            Map<String, String> reqVo = ParamUtil.buildRequestMap(hotRequestRo.getQ());
            caffeineService.save(LockConstant.HOT_REQUEST_CAFFEINE_CACHE_NAME, RedisConstant.HOT_REQUEST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + hotRequestRo.getUrl() + BaseConstant.CONNECTOR_UNDERLINE + ParamUtil.buildParam(reqVo), hotRequestRo.getR());
        } else {
            //其他情况增加次数
            hotRequestRo.setCounter(counter + 1);
        }
        save(hotRequestRo);
        distributedLockerService.unlock(RedisConstant.HOT_REQUEST_UPDATE_LOCK_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + hotRequestRo.getUrl());
    }


}
