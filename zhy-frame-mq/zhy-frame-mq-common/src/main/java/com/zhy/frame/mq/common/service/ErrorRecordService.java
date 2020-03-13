package com.zhy.frame.mq.common.service;/**
 * 描述:
 * 包名:com.zhy.frame.mq.common.service
 * 版本信息: 版本1.0
 * 日期:2019/11/21
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.mq.common.ro.ErrorRecordRo;
import com.zhy.frame.mq.common.vo.MsgErrorVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/21 9:24
 */
public interface ErrorRecordService<T> {

    /**
     * 往redis中写入errorRecord的数据
     *
     * @param errorRecordRo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 9:26
     */
    void errorRecord2Redis(ErrorRecordRo errorRecordRo);

    /**
     * 获取错误信息
     *
     * @throws
     * @return: java.util.List<T>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/21 9:26
     */
    List<T> getAllErrorRecord();
}
