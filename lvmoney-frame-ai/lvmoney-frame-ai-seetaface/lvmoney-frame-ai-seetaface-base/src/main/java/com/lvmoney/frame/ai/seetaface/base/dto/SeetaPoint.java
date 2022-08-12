package com.lvmoney.frame.ai.seetaface.base.dto;/**
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

/**
 * @describe：坐标
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/20 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeetaPoint implements Serializable {
    private static final long serialVersionUID = 8243039452263952159L;
    public double x;
    public double y;

    @Override
    public String toString() {
        return "{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
