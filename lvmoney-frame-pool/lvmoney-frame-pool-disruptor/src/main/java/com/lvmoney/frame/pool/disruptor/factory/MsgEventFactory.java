package com.lvmoney.frame.pool.disruptor.factory;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.factory
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.EventFactory;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 15:38
 */
public class MsgEventFactory implements EventFactory<MsgEvent> {
    @Override
    public MsgEvent newInstance() {
        return new MsgEvent();
    }
}
