package com.lvmoney.frame.authentication.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.api.vo
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 9:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiroCheckVo implements Serializable {
    private boolean result;
}
