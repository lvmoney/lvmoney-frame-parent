package com.lvmoney.frame.ai.pyod.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 11:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PyodParamItem implements Serializable {
    private static final long serialVersionUID = -2121107991666496898L;
    /**
     * 异常率
     */
    private Float errorFraction;

    /**
     * 空值默认值
     */
    private String defValue;
    /**
     * 数量
     */
    private Integer num;
}
