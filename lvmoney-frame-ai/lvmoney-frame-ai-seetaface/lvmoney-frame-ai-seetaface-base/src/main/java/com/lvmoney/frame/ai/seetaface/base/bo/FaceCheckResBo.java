package com.lvmoney.frame.ai.seetaface.base.bo;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.bo
 * 版本信息: 版本1.0
 * 日期:2022/2/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/16 16:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaceCheckResBo implements Serializable {
    private static final long serialVersionUID = 4621585614705965669L;
    /**
     * 高
     */
    private Double height;
    /**
     * 分
     */
    private Double score;
    /**
     * 宽
     */
    private Double width;
    /**
     * x轴坐标
     */
    private Double x;
    /**
     * y轴坐标
     */
    private Double y;
}
