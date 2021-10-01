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
 * @version:v1.0 2021/7/5 16:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryCptAo implements Serializable {
    private static final long serialVersionUID = -4278696402688231572L;
    /**
     * cptId
     */
    private Integer cptId;
}
