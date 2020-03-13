package com.zhy.frame.log.server.vo;/**
 * 描述:
 * 包名:com.zhy.log.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/6 10:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControllerVo implements Serializable {
    /**
     * 描述
     */
    private String descrption;
    /**
     * 类型
     */
    private String actionType;
}
