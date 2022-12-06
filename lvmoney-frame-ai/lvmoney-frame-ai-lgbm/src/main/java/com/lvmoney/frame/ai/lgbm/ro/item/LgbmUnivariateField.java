package com.lvmoney.frame.ai.lgbm.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/6/30
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/30 11:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LgbmUnivariateField implements Serializable {
    private static final long serialVersionUID = -3639158216107603587L;
    /**
     * 预测的字段
     */
    private String field;
}
