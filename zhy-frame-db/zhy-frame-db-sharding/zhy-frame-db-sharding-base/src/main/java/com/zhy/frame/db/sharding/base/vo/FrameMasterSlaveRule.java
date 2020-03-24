package com.zhy.frame.db.sharding.base.vo;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/7 11:19
 */
@Data
public class FrameMasterSlaveRule implements Serializable {
    private String name;
    private String loadBalanceAlgorithm;
}
