package com.zhy.frame.authentication.oauth2.center.vo.resp;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 15:23
 */
@Data
public class CaptchaGraphRespVo implements Serializable {
    private static final long serialVersionUID = 339214345623157504L;
    private int status;

    private long expire;

    private String graphId;

    private String graphUrl;

}
