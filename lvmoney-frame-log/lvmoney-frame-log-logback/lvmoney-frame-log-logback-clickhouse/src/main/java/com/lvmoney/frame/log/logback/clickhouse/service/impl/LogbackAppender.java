package com.lvmoney.frame.log.logback.clickhouse.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/22
 * Copyright XXXXXX科技有限公司
 */


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.core.util.DateUtil;
import com.lvmoney.frame.core.util.IpUtil;
import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
import com.lvmoney.frame.log.logback.common.vo.LogVo;

import static com.lvmoney.frame.base.core.constant.BaseConstant.COMMON_DATE_FORMAT;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/22 17:40
 */
public class LogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        ClickhouseLogbackServiceImpl logbackService = SpringBeanUtil.getBean(ClickhouseLogbackServiceImpl.class);

        if (logbackService != null) {
            if (BaseConstant.SUPPORT_FALSE.equals(logbackService.getLogbackProp().getSupport())) {
                //如果不支持就直接返回
                return;
            }
            LogVo logVo = new LogVo();
            logVo.setIp(IpUtil.getLocalhostIp());
            logVo.setLevel(eventObject.getLevel().toString());
            logVo.setMsg(eventObject.getFormattedMessage());
            logVo.setThread(eventObject.getThreadName());
            logVo.setExeDate(DateUtil.localDateTimeFormatter(DateUtil.timestamp2LocalDateTime(eventObject.getTimeStamp()), COMMON_DATE_FORMAT));
            logVo.setSysName(logbackService.getSystemName());
            Long num = SnowflakeIdFactoryUtil.nextId();
            logVo.setId(num.toString());
            logbackService.saveLog(logVo);
        }
    }


}