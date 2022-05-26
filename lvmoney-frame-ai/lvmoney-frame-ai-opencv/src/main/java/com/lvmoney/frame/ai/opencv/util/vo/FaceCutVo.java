package com.lvmoney.frame.ai.opencv.util.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.opencv.util.vo
 * 版本信息: 版本1.0
 * 日期:2021/12/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/20 21:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FaceCutVo implements Serializable {
    private static final long serialVersionUID = -2619358646636892913L;
    /**
     * 框出来的原图
     */
    private String res;
    /**
     * 拆件后图片地址
     */
    private List<String> item;
}
