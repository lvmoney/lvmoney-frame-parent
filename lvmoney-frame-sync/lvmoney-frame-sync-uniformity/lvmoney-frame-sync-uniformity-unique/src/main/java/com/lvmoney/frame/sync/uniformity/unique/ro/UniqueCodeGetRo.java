package com.lvmoney.frame.sync.uniformity.unique.ro;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.unique.ro
 * 版本信息: 版本1.0
 * 日期:2020/10/10
 * Copyright 成都XXXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/成都XXXXXXX科技有限公司
 * @version:v1.0 2020/10/10 10:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniqueCodeGetRo implements Serializable {
    /**
     * redis key
     */
    private String codeKey;
    /**
     * code
     */
    private Long code;
    /**
     * 失效时间
     */
    private Long expired;

}
