package com.lvmoney.frame.oss.common.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.common.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/7/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.oss.common.service.OssBeanService;
import com.lvmoney.frame.oss.common.service.OssService;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/8 11:36
 */
@Service
public class OssBeanServiceImpl implements OssBeanService {
    @Override
    public OssService getOssHandService(String name) {
        OssService ossHandService = (OssService) SpringBeanUtil.getBean(name);
        return ossHandService;
    }
}
