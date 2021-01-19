package com.lvmoney.frame.authentication.oauth2.customer.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.oauth2.customer.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/8/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/10 10:46
 */
@Data
public class Oauth2AuthorizationCode implements Serializable {
    private static final long serialVersionUID = 2226213418743185623L;
    private String code;
    private String state;
}
