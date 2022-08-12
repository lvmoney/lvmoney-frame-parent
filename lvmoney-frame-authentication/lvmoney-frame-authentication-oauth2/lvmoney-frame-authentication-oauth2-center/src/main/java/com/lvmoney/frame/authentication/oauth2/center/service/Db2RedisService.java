package com.lvmoney.frame.authentication.oauth2.center.service;/**
 * 描述:
 * 包名:com.lvmoney.oauth2.center.feign.service
 * 版本信息: 版本1.0
 * 日期:2019/8/6
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/6 14:14
 */
public interface Db2RedisService {

    /**
     * 从数据库获得用户的userdetails信息并加载到redis
     *
     * @param username: 用户名
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:18
     */
    void loadUserByUsername(String username);

    /**
     * 从数据库中获得clientdetails信息并加载到redis
     *
     * @param clientId: 客户端id
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:18
     */
    void loadClientDetailsByClientId(String clientId);
}
