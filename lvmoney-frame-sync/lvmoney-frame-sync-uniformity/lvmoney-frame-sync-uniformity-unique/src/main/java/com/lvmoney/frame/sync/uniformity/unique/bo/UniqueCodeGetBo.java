package com.lvmoney.frame.sync.uniformity.unique.bo;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.unique.bo
 * 版本信息: 版本1.0
 * 日期:2020/10/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/10 16:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniqueCodeGetBo implements Serializable {
    private static final long serialVersionUID = 3742654006451042232L;
    private Long code;
}
