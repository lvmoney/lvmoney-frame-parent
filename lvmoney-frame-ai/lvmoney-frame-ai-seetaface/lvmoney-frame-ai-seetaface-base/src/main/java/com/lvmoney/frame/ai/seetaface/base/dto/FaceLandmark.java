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


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/20 20:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceLandmark extends SeetaRect {
    private static final long serialVersionUID = -6380118654047277753L;
    public SeetaPoint[] points;

    public FaceLandmark(SeetaRect rect) {
        this.x = rect.x;
        this.y = rect.y;
        this.width = rect.width;
        this.height = rect.height;
        this.score = rect.score;
    }

}
