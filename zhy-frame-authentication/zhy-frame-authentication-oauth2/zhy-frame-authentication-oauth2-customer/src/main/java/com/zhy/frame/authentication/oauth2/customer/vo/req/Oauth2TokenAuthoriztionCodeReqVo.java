package com.zhy.frame.authentication.oauth2.customer.vo.req;/**
 * 描述:
 * 包名:com.zhy.frame.oauth2.customer.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/11
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/11 9:10
 */
@Data
public class Oauth2TokenAuthoriztionCodeReqVo extends CommonReqVo {
    private static final long serialVersionUID = -1706255151045799445L;
    private String redirect_uri;
    private String code;
}
