package com.zhy.frame.log.server.service.impl;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.core.ro.UserRo;
import com.zhy.frame.log.server.service.LogService;
import com.zhy.frame.log.server.vo.LogVo;
import com.zhy.frame.log.server.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public abstract class AbstractLogService implements LogService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public UserVo getUser(UserVo userVo) {
        String token = userVo.getToken();
        String jwtString = baseRedisService.getByKey(token).toString();
        if (jwtString != null) {
            UserRo userRo = JSON.parseObject(jwtString, new TypeReference<UserRo>() {
            });
            if (userRo != null) {
                userVo.setUsername(userRo.getUsername());
                userVo.setUserId(userRo.getUserId());
                return userVo;
            }
        }
        return null;
    }

    @Override
    public void saveLog(LogVo logVo) {
        //TODO 把日志保存到数据库，暂未实现
        System.out.println(JsonUtil.t2JsonString(logVo));
    }
}
