package com.zhy.frame.authentication.oauth2.resource.vo;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.resource.vo
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/12 14:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtectResource implements Serializable {
    /**
     * 请求地址，可用通配符
     */
    private String url;
}
