package com.lvmoney.frame.authentication.oauth2.customer.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.oauth2.customer.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/10 19:16
 */
@Data
public class Oauth2UserMe implements Serializable {
    private static final long serialVersionUID = -8883052758633153674L;
    private String username;
    private String gender;
    private String nickname;
    private String grantType;
}
