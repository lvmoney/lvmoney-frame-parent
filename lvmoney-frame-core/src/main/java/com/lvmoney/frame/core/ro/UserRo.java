/**
 * 描述:
 * 包名:com.lvmoney.jwt.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:49:22
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.core.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月7日 下午5:49:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4541709436582146541L;
    private String userId;
    private String username;
    private String password;
    private long expire;
    private String token;
    /**
     * new 考虑多系统，新增系统编号
     */
    private String sysId;
    /**
     * 租户id
     */
    private String tenantId;
    /**
     * new 拓展字段存储为序列化后的json
     */
    private String other;

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
     * 组织id
     */
    private String orgId;
}
