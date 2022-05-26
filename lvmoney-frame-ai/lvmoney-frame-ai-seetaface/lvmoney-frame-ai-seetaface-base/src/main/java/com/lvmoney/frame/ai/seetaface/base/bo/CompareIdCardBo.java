package com.lvmoney.frame.ai.seetaface.base.bo;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.bo
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
 * 2022/2/11 11:09  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareIdCardBo implements Serializable {

    private static final long serialVersionUID = 1225889646921733740L;
    /**
     * 得分
     */
    private Float score;
}
