package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.bo
 * 版本信息: 版本1.0
 * 日期:2022/2/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/10 17:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonHistoryDto implements Serializable {

    private static final long serialVersionUID = 2368527718251867230L;
    private String target;

    private String res;

    /**
     * 分类
     */
    private String classify;

    /**
     * 结果
     */
    private String result;

    /**
     * 得分
     */
    private BigDecimal score;
}
