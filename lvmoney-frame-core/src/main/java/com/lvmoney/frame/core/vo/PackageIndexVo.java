package com.lvmoney.frame.core.vo;/**
 * 描述:
 * 包名:com.chdriver.frame.core.vo
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
 * 2021/9/23 20:04  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageIndexVo implements Serializable {
    private static final long serialVersionUID = 4610611103590944873L;
    /**
     * 第几个重量
     */
    private Integer num;
}
