package com.zhy.frame.cache.queue.vo;/**
 * 描述:
 * 包名:com.zhy.frame.cache.queue.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageVo<T> implements Serializable {
    private static final long serialVersionUID = -3743500376960871063L;
    private String type;
    private T data;
}
