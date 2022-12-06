package com.lvmoney.frame.ai.adtk.ro.item;/**
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
public class AdtkParamItem implements Serializable {
    private static final long serialVersionUID = 2032336834897429622L;
    /**
     * 最大值
     */
    private Float max;

    /**
     * 最小值
     */
    private Float min;

    /**
     * 窗口长度
     */
    private Integer window;
    /**
     * 空值设置默认值
     */
    private String defValue;
    /**
     * c：分位距倍数，用于确定上下限范围
     */
    private Float c;
}
