package com.lvmoney.frame.pool.disruptor.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.vo
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 15:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgEvent implements Serializable {
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private String data;
    /**
     * 时间
     */
    private Long date;
}
