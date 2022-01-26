package com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item
 * 版本信息: 版本1.0
 * 日期:2021/7/6
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/7/6 13:41  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Salt implements Serializable {
    /**
     *
     */
    private String content;
    /**
     *
     */
    private String receiver;
    /**
     *
     */
    private String weid;
}
