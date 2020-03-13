package com.zhy.frame.route.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.vo.ServerInfo;

import java.net.URI;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 14:33
 */
public interface ServerService {

    /**
     * 获得需要访问的服务的服务信息
     *
     * @param uri:例如：www.provider.com
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 18:04
     */
    ServerInfo getServerInfo(URI uri);

    /**
     * 获得需要访问的服务的服务信息，主要是基于服务本地化测试的需要
     *
     * @param uri: 例如：http://10.20.10.69:9901/
     * @throws
     * @return: com.lvmoney.common.vo.ServerInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/18 8:57
     */
    ServerInfo getServerInfo(String uri);
}
