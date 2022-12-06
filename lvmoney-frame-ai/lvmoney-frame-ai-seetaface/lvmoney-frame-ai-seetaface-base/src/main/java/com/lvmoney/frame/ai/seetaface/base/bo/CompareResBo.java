package com.lvmoney.frame.ai.seetaface.base.bo;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.bo
 * 版本信息: 版本1.0
 * 日期:2022/2/11
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2022/2/11 10:50  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareResBo implements Serializable {
    private static final long serialVersionUID = -5367386062272068012L;
    /**
     * 得分
     */
    private Float score;
}
