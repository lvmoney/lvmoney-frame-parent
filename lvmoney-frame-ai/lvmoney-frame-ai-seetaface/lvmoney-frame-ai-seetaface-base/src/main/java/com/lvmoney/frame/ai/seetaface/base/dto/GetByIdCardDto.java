package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.vo
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
 * @version:v1.0 2022/2/11 17:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetByIdCardDto implements Serializable {

    private static final long serialVersionUID = 8784507160814982677L;
    /**
     * 加密后身份证号
     */
    private String idCard;
    /**
     * 隐私身份证号
     */
    private String idCardPrivacy;
}
