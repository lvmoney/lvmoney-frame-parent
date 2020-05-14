package com.zhy.frame.ops.robots.vo;/**
 * 描述:
 * 包名:com.zhy.robots.vo
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 17:39
 */
@Data
public class Robots implements Serializable {
    private String userAgent;
    private List<RobotsDisallow> disallow;
}
