package com.lvmoney.frame.sync.netty.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.netty.common.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/1
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/1 14:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo<T> implements Serializable {
    /**
     * 消息类型匹配
     */
    private String type;
    /**
     * 消息内容
     */
    private T data;
    /**
     * 发送时间
     */
    private Long date;
    /**
     * 用户token
     */
    private String token;
}
