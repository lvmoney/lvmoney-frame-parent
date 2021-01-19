package com.lvmoney.frame.html.statics.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.html.statics.ao
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 13:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HandHtmlStaticsVo implements Serializable {

    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 转化后的请求地址，主要是某些请求地址里面有通配符需要特殊处理
     */
    private String requestUrl;
}
