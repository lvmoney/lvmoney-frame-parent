package com.zhy.frame.ops.robots.vo;/**
 * 描述:
 * 包名:com.zhy.robots.vo
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 16:11
 */
@Data
@AllArgsConstructor
public class RobotsDisallow implements Serializable {
    private static final long serialVersionUID = -7461384084485604369L;
    private String disallow;
}
