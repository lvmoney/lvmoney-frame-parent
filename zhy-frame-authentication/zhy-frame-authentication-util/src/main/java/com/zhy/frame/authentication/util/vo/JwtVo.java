package com.zhy.frame.authentication.util.vo;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.jwt.vo
 * 版本信息: 版本1.0
 * 日期:2020/3/3
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/3 16:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtVo implements Serializable {
    /**
     * iss: jwt签发者
     */
    private String iss;
    /**
     * sub:jwt所面向的用户
     */
    private String sub;
    /**
     * aud:接收jwt的一方
     */
    private String aud;
    /**
     * exp:jwt的过期时间，这个过期时间必须要大于签发时间
     */
    private Long exp;

    /**
     * nbf:定义在什么时间之前，该jwt都是不可用的 .
     */

    private Date nbf;
    /**
     * jti:jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
     */
    private String jti;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 系统编号
     */
    private String sysId;
    /**
     * 租户id
     */
    private String tenantId;

}
