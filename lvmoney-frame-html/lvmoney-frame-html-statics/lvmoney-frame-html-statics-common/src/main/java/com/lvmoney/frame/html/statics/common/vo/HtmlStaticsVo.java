package com.lvmoney.frame.html.statics.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.html.statics.vo
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
 * @version:v1.0 2020/4/21 10:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlStaticsVo implements Serializable {
    private static final long serialVersionUID = 1430406720734394489L;
    /**
     * 请求地址
     */
    private String uri;
    /**
     * 静态文件路径
     */
    private String path;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 服务名称
     */
    private String serverName;
}
