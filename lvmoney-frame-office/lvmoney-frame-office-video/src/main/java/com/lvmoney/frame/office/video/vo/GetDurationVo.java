package com.lvmoney.frame.office.video.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.office.video.vo
 * 版本信息: 版本1.0
 * 日期:2021/10/12
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/10/12 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDurationVo implements Serializable {
    private static final long serialVersionUID = -5448906713531131552L;
    /**
     * 小时
     */
    private Integer hour;
    /**
     * 分
     */
    private Integer min;

    /**
     * 秒
     */
    private Integer sec;

    /**
     * 总时长
     */
    private Long duration;
}
