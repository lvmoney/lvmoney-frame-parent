package com.lvmoney.frame.pool.disruptor.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.service
 * 版本信息: 版本1.0
 * 日期:2020/4/28
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/28 11:40
 */
public interface MsgHandlerService {
    /**
     * 数据处理
     *
     * @param msgEvent:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/28 11:42
     */
    void onEvent(MsgEvent msgEvent);
}
