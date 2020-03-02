package com.zhy.frame.authentication.oauth2.customer.vo;/**
 * 描述:
 * 包名:com.zhy.frame.oauth2.customer.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/10 18:44
 */
@Data
public class Oauth2Token implements Serializable {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private String scope;
    private String sub;
    private String grantType;
    private String jti;
    private int expires_in;
    private String username;
    private String nikename;
    private String gender;
}
