package com.lvmoney.frame.sync.uniformity.unique.dto;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.unique.dto
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
 * @version:v1.0 2020/10/10 10:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniqueCodeGetDto implements Serializable {
    private static final long serialVersionUID = 7881069992162520573L;
    /**
     * 系统id
     */
    private String clientId;

    private Integer size;
}
