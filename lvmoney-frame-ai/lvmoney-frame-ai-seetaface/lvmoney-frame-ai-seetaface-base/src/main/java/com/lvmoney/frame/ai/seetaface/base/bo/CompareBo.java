package com.lvmoney.frame.ai.seetaface.base.bo;/**
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

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/10 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareBo implements Serializable {
    private static final long serialVersionUID = -6540499711244324752L;
    /**
     * 得分
     */
    private Float score;
}
