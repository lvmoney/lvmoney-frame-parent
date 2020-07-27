package com.zhy.frame.route.gateway.vo;/**
 * 描述:
 * 包名:com.zhy.frame.route.gateway.vo
 * 版本信息: 版本1.0
 * 日期:2020/7/16
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.route.gateway.enums.NoRepeatSubmitEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/16 9:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepeatSubmitVo implements Serializable {
    /**
     * 路径
     */
    private String path;
    /**
     * 策略
     */
    private NoRepeatSubmitEnum repeatSubmitEnum;
    /**
     * 重复时间
     */
    private Integer repeatSubmitTime;
}
