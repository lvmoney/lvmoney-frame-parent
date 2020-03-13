package com.zhy.frame.db.mysql.rw.vo;/**
 * 描述:
 * 包名:com.zhy.mysql.separate.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/7
 * Copyright 四川******科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/7 11:19
 */
@Data
public class FrameMasterSlaveRule implements Serializable {
    private String name;
    private String loadBalanceAlgorithm;
}
