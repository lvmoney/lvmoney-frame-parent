package com.zhy.frame.cloud.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.cloud.base.service.CloudBaseService;
import com.zhy.frame.core.ro.ServerBaseInfoRo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:52
 */
@Service
public class CloudBaseServiceImpl implements CloudBaseService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void saveServerInfo(ServerBaseInfoRo serverBaseInfoRo) {
        baseRedisService.addMap(BaseConstant.REDIS_SERVER_CROUP_KEY, serverBaseInfoRo.getData(), serverBaseInfoRo.getExpired());
    }

    @Override
    public void deleteServerInfoByServer(String serverName) {
        baseRedisService.deleteByMapKey(BaseConstant.REDIS_SERVER_CROUP_KEY, serverName);
    }
}
