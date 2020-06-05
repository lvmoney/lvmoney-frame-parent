package com.zhy.frame.core.vo;/**
 * 描述:
 * 包名:com.zhy.frame.core.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/29 8:55
 */
@Data
public class SplitFileVo implements Serializable {
    /**
     * 开始位置
     */
    private long begin;
    /**
     * 结束位置
     */
    private long end;
}
