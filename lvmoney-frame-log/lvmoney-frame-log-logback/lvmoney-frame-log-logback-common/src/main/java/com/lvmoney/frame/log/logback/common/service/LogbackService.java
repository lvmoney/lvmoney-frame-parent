package com.lvmoney.frame.log.logback.common.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.log.logback.common.vo.LogVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:51
 */
public interface LogbackService {
    /**
     * 存储日志
     *
     * @param logVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:51
     */
    void saveLog(LogVo logVo);

    /**
     * 获得系统名称
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/14 17:45
     */
    String getSystemName();
}
