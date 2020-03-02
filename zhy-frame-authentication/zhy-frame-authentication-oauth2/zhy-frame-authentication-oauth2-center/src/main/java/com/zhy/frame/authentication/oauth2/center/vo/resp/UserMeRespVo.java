package com.zhy.frame.authentication.oauth2.center.vo.resp;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/7/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/29 10:16
 */
@Data
public class UserMeRespVo implements Serializable {
    private static final long serialVersionUID = -3247925047012134706L;
    private String username;
    private String gender;
    private String nickname;
    private String grantType;
    // private String userId;
// private String authorities;
// private int status;
}
