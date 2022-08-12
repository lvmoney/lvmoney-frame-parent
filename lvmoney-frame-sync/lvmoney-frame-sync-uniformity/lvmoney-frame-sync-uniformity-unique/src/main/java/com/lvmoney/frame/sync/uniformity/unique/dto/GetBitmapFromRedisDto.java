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

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/30 15:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBitmapFromRedisDto implements Serializable {

    private static final long serialVersionUID = -6318220303011652291L;
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
