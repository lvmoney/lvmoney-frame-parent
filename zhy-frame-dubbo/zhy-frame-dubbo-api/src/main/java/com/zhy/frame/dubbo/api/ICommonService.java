package com.zhy.frame.dubbo.api;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.provider.service
 * 版本信息: 版本1.0
 * 日期:2019/12/27
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.web.bind.annotation.PostMapping;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/27 14:06
 */
public interface ICommonService {
    /**
     * dubbo通用模板接口
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/31 9:53
     */
    @PostMapping(value = "common")
    String common();
}
