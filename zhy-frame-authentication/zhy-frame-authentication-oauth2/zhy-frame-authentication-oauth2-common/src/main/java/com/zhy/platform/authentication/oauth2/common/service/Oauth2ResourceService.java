package com.zhy.platform.authentication.oauth2.common.service;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.resource.service
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.platform.authentication.oauth2.common.ro.ProtectResrouceRo;
import com.zhy.platform.authentication.oauth2.common.vo.ProtectResource;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/12 14:17
 */
public interface Oauth2ResourceService {
    /**
     * 存储受保护的地址到redis
     *
     * @param protectResrouceRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/12 14:26
     */
    void saveProtectResource2Redis(ProtectResrouceRo protectResrouceRo);

    /**
     * 从缓存中获得受保护的资源
     *
     * @throws
     * @return: java.util.List<com.zhy.frame.authentication.oauth2.resource.vo.ProtectResource>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/12 14:26
     */
    List<ProtectResource> getProtectResource();
}
