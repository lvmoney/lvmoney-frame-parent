package com.lvmoney.frame.sync.uniformity.unique.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.dto
 * 版本信息: 版本1.0
 * 日期:2021/12/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.sync.uniformity.unique.enums.UniformityTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/30 14:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBitmapDto implements Serializable {

    private static final long serialVersionUID = 8060650032816386283L;
    /**
     * 客户端id
     */
    private String clientId;
    /**
     * 分类
     */
    private String classify;

    /**
     * 数据日期 yyyy-MM-dd
     */
    private String synDate;
    /**
     * 唯一码
     */
    List<Long> uniqueIds;

    /**
     * 发出或者接收
     */
    private UniformityTypeEnum uniformityTypeEnum;

    /**
     * 表用到的sharding 策略
     */
    private String tableLogicTable;
    /**
     * 库用到的sharding 策略
     */
    private String dbLogicTable;
}
