package com.lvmoney.frame.blockchain.webase.wallet.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 20:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Wallet implements Serializable {
    private static final long serialVersionUID = -7258077206912936867L;
    /**
     * 群组ID
     */
    private Long groupId;
    /**
     * 用户编号
     */
    private String signUserId;
    /**
     * 是否使用cns调用
     */
    private Boolean useCns;
    /**
     * cns名称
     */
    private String cnsName;
    /**
     * cns版本
     */
    private String version;

}
