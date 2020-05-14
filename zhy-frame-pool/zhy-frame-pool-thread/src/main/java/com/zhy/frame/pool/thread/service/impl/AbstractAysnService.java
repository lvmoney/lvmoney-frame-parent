package com.zhy.frame.pool.thread.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.service
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.pool.thread.service.AysnService;
import com.zhy.frame.pool.thread.vo.UncaughtExceptionVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 11:07
 */
public abstract class AbstractAysnService implements AysnService {
    @Override
    public void saveUncaughtException(UncaughtExceptionVo uncaughtExceptionVo) {
        System.out.println(JsonUtil.t2JsonString(uncaughtExceptionVo));
    }
}
