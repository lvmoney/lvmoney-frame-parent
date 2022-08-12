package com.lvmoney.frame.sync.uniformity.unique.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.ro
 * 版本信息: 版本1.0
 * 日期:2021/12/30
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/30 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyRecordRo implements Serializable {
    private static final long serialVersionUID = -7427031740628971611L;
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


}
