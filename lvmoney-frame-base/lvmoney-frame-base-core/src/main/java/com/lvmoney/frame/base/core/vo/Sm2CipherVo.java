package com.lvmoney.frame.base.core.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.vo
 * 版本信息: 版本1.0
 * 日期:2021/8/31
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/8/31 8:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sm2CipherVo implements Serializable {
    private static final long serialVersionUID = 1814770127919141113L;
    /**
     * 16进制私钥
     */
    private String privateKeyHex;
    /**
     * 16进制公钥
     */
    private String publicKeyHex;
}
