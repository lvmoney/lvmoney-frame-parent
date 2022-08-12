package com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item
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
 * @version:v1.0 2021/7/5 16:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaData implements Serializable {
    private static final long serialVersionUID = -1584185889542159646L;
    private String cptPublisher;
    private String cptSignature;
    private Long updated;
    private Long created;
}
