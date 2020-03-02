package com.zhy.frame.authentication.oauth2.resource.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.resource.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.oauth2.resource.ro.ProtectResrouceRo;
import com.zhy.frame.authentication.oauth2.resource.service.Oauth2ResourceService;
import com.zhy.frame.authentication.oauth2.resource.vo.ProtectResource;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/12 14:23
 */
public class Oauth2ResourceServiceImpl implements Oauth2ResourceService {
    @Override
    public boolean saveProtectResrouce2Redis(ProtectResrouceRo protectResrouceRo) {
        return false;
    }

    @Override
    public List<ProtectResource> getProtectResrouce() {
        return null;
    }
}
