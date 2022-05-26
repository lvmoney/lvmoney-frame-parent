package com.lvmoney.frame.ai.seetaface.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.common.vo
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
 * @version:v1.0 2022/2/11 15:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceStr implements Serializable {

    private static final long serialVersionUID = 3431891188935924626L;
    private String fileId;
    private String fileName;
    private String fileType;
}
