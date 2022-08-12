package com.lvmoney.frame.authentication.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.api.ao
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
 * @version:v1.0 2020/3/8 9:29
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ShiroCheckAo implements Serializable {
    /**
     * 请求地址
     */
    private String path;
    /**
     * token
     */
    private String token;
}
