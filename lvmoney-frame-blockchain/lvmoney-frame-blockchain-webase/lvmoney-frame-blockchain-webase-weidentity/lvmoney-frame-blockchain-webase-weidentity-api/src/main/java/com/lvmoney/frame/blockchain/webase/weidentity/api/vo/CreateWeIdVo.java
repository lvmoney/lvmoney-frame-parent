package com.lvmoney.frame.blockchain.webase.weidentity.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 9:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateWeIdVo implements Serializable {
    private static final long serialVersionUID = 329500449603243207L;
    /**
     * WeIdentity DID
     */
    private String weId;
}
