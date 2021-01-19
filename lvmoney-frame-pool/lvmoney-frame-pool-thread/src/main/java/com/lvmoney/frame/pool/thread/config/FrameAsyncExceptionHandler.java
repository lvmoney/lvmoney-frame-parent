package com.lvmoney.frame.pool.thread.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.asyn.handler
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.pool.thread.service.AysnService;
import com.lvmoney.frame.pool.thread.vo.UncaughtExceptionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 10:05
 */
@Component
public class FrameAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(FrameAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
        String msg = throwable.getMessage();
        String methodName = method.getName();
        LOGGER.error("Exception message :{}", JsonUtil.t2JsonString(throwable));
        LOGGER.error("Method name :{}", method.getName());
        String clazz = method.getDeclaringClass().getName();
        LOGGER.error("Class name :{}", clazz);
        List<String> parameter = new ArrayList<>();
        for (Object param : obj) {
            LOGGER.error("Parameter value :{}", param);
            parameter.add(param.toString());
            LOGGER.error("Parameter value :{}", param);
        }
        AysnService aysnService = SpringBeanUtil.getBean(AysnService.class);
        aysnService.saveUncaughtException(new UncaughtExceptionVo(msg, methodName, parameter, clazz));
    }

}
