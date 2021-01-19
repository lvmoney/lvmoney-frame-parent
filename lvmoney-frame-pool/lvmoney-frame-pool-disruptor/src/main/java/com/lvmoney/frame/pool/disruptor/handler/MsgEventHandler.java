package com.lvmoney.frame.pool.disruptor.handler;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.handler
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.lvmoney.frame.pool.disruptor.factory.MsgHandlerServiceContext;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 15:41
 */
@Component
public class MsgEventHandler implements EventHandler<MsgEvent>, WorkHandler<MsgEvent> {
    @Override
    public void onEvent(MsgEvent event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(MsgEvent msgEvent) throws Exception {
        String msgType = msgEvent.getType();
        MsgHandlerServiceContext msgHandlerServiceContext = SpringBeanUtil.getBean(MsgHandlerServiceContext.class);
        msgHandlerServiceContext.getData(msgType, msgEvent);
    }

}
