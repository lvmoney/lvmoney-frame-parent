package com.lvmoney.frame.mq.common.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.mq.common.ro
 * 版本信息: 版本1.0
 * 日期:2019/11/21
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/21 9:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRecordRo<T> implements Serializable {
    private static final long serialVersionUID = -4043990236650268838L;
    private List<T> data;
    private String type;
    private Long expire;
}
