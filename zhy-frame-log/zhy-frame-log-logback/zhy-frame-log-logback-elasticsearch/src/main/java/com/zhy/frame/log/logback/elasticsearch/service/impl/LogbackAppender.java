package com.zhy.frame.log.logback.elasticsearch.service.impl;/**
 * 描述:
 * 包名:com.zhy.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.zhy.frame.core.util.SpringBeanUtil;
import com.zhy.frame.log.logback.common.vo.LogVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 17:40
 */
public class LogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        EsLogbackServiceImpl logbackService = SpringBeanUtil.getBean(EsLogbackServiceImpl.class);
        if (logbackService != null) {
            LogVo logVo = new LogVo();
            logVo.setLevel(eventObject.getLevel().toString());
            logVo.setLogger(eventObject.getLoggerName());
            logVo.setMessage(eventObject.getFormattedMessage());
            logVo.setThread(eventObject.getThreadName());
            logVo.setTimeStamp(eventObject.getTimeStamp());
            logVo.setSystemName(logbackService.getSystemName());
            logbackService.saveLog(logVo);
        }
    }

}