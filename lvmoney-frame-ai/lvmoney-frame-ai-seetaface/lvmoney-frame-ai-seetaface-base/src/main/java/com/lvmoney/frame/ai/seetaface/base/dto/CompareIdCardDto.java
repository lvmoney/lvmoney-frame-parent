package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.vo
 * 版本信息: 版本1.0
 * 日期:2022/2/11
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/11 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompareIdCardDto implements Serializable {
    private static final long serialVersionUID = -162597117322328180L;
    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 对比图片流
     */
    private FileBaseVo target;
    /**
     * 是否记录对比记录
     */
    private Boolean record;
}
