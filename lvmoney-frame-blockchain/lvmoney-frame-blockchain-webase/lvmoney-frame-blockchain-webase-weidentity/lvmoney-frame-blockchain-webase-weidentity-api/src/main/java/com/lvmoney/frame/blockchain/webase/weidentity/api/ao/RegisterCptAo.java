package com.lvmoney.frame.blockchain.webase.weidentity.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.ao
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
 * @version:v1.0 2021/7/5 16:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterCptAo<T> implements Serializable {
    private static final long serialVersionUID = -5461921447562558578L;
    /**
     * weid
     */
    private String weId;
    /**
     * json格式
     */
    private T cptJsonSchema;
}
