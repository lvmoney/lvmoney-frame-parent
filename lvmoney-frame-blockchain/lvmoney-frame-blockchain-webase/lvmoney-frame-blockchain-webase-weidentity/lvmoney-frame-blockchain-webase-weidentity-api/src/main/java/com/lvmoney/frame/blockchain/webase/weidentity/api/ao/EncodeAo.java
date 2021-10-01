package com.lvmoney.frame.blockchain.webase.weidentity.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/30
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/30 19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EncodeAo implements Serializable {
    private static final long serialVersionUID = 3233480263766414882L;
    /**
     *
     */
    private String publicKey;
}
