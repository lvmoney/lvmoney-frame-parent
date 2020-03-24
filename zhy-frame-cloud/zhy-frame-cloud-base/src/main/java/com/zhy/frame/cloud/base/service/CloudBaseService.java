package com.zhy.frame.cloud.base.service;/**
 * 描述:
 * 包名:com.zhy.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.ro.ServerBaseInfoRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:52
 */
public interface CloudBaseService {
    /**
     * 存储服务基本信息到redis中
     *
     * @param serverBaseInfoRo: 服务基础信息实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:49
     */
    void saveServerInfo(ServerBaseInfoRo serverBaseInfoRo);

    /**
     * 删除指定系统的基本信息
     *
     * @param serverName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/7 22:43
     */
    void deleteServerInfoByServer(String serverName);
}
