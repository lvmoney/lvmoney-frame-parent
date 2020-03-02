package com.zhy.frame.dubbo.provider.impl;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.provider.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/12/27
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.dubbo.api.ICommonService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/27 14:11
 */
@Service
@Component
@RestController
public class ICommonServiceImpl implements ICommonService {

    @Override
    public String common() {
        return "hello dubbo & springcloud";
    }
}

