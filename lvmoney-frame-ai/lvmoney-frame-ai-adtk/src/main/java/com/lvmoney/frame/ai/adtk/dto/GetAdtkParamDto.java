package com.lvmoney.frame.ai.adtk.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.dto
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
 * @version:v1.0 2022/5/12 11:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdtkParamDto implements Serializable {
    private static final long serialVersionUID = 8231815543560486024L;
    /**
     * 配置key
     */
    private String adtkParamKey;
}
