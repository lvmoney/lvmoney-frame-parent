package com.lvmoney.frame.pool.thread.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.service
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.pool.thread.vo.UncaughtExceptionVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 11:07
 */
public interface AysnService {
    /**
     * 存储线程池的错误信息
     *
     * @param uncaughtExceptionVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 12:06
     */
    void saveUncaughtException(UncaughtExceptionVo uncaughtExceptionVo);
}
