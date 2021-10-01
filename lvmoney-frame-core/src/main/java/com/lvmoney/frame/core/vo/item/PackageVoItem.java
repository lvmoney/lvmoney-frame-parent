package com.lvmoney.frame.core.vo.item;/**
 * 描述:
 * 包名:com.chdriver.frame.core.vo.item
 * 版本信息: 版本1.0
 * 日期:2021/9/23
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
 * 2021/9/23 19:49  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageVoItem implements Serializable {
    private static final long serialVersionUID = -9141577800325742266L;
    /**
     * 重量
     */
    private int weight;
    /**
     *数量
     */
    private int number;
    /**
     * 价值
     */
    private int value;
}
