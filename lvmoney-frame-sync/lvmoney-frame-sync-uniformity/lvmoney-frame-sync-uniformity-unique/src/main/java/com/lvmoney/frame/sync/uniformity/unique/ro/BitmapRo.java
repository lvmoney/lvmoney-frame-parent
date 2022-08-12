package com.lvmoney.frame.sync.uniformity.unique.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.ro
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
 * @version:v1.0 2021/12/30 14:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BitmapRo implements Serializable {
    private static final long serialVersionUID = 4325172731010866670L;
    /**
     * bitmap
     */
    private String bitmap;

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
     * 表用到的sharding 策略
     */
    private String tableLogicTable;
    /**
     * 库用到的sharding 策略
     */
    private String dbLogicTable;

    /**
     * 发出或者接收
     */
    private UniformityTypeEnum uniformityTypeEnum;
}
