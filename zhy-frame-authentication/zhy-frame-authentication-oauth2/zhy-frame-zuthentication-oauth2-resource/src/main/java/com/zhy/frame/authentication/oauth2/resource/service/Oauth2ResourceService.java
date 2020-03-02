package com.zhy.frame.authentication.oauth2.resource.service;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.resource.service
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.oauth2.resource.ro.ProtectResrouceRo;
import com.zhy.frame.authentication.oauth2.resource.vo.ProtectResource;

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
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/12 14:26
     */
    boolean saveProtectResrouce2Redis(ProtectResrouceRo protectResrouceRo);

    /**
     * 从缓存中获得受保护的资源
     *
     * @throws
     * @return: java.util.List<com.zhy.frame.authentication.oauth2.resource.vo.ProtectResource>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/12 14:26
     */
    List<ProtectResource> getProtectResrouce();
}
