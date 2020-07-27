package com.zhy.frame.operate.burypoint.vo;/**
 * 描述:
 * 包名:com.zhy.frame.operate.burypoint.vo
 * 版本信息: 版本1.0
 * 日期:2020/7/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/14 14:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ButtonVo implements Serializable {
    /**
     * 按钮id
     */
    private String buttonId;
    /**
     * 按钮编号
     */
    private String buttonNum;


}
