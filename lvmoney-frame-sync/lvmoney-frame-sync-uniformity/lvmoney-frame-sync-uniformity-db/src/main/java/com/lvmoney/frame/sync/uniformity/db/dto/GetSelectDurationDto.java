package com.lvmoney.frame.sync.uniformity.db.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.dto
 * 版本信息: 版本1.0
 * 日期:2022/1/6
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
 * 2022/1/6 17:22  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSelectDurationDto implements Serializable {

    private static final long serialVersionUID = 841029306483131192L;
    /**
     * 表用到的sharding 策略
     */
    private String tableLogicTable;
    /**
     * 分类
     */
    private String classify;
    /**
     * 库用到的sharding 策略
     */
    private String dbLogicTable;
}
