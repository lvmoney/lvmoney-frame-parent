package com.lvmoney.frame.authentication.oauth2.center.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/7/28
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:42
 */
@Data
public class CaptchaGraphBase64RespVo implements Serializable {
    private static final long serialVersionUID = 3437524899338143520L;
    private int status;
    private String base64EncodedGraph;
}
