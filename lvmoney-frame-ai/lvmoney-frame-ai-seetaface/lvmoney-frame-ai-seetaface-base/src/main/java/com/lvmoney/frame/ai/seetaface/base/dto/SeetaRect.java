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
 * @describe：图片信息
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/20 20:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeetaRect implements Serializable {
    public int x;
    public int y;
    public int width;
    public int height;
    public float score;

    @Override
    public String toString() {
        return "SeetaRect{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", score=" + score +
                '}';
    }
}
