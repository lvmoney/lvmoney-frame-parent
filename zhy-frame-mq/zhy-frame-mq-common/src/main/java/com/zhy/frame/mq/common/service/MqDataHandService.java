/**
 * 描述:
 * 包名:com.zhy.hotel.service
 * 版本信息: 版本1.0
 * 日期:2018年11月8日  下午2:59:09
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.mq.common.service;


import com.zhy.frame.mq.common.vo.MessageVo;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年11月8日 下午2:59:09
 */

public interface MqDataHandService {
    /**
     * 消息处理
     *
     * @param messageVo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:41
     */
    void handData(MessageVo messageVo);
}
