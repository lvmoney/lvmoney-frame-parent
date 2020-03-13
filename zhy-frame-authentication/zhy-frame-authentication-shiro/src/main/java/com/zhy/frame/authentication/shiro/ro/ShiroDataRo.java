/**
 * 描述:
 * 包名:com.zhy.shiro.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午5:38:49
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.shiro.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月8日 下午5:38:49
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ShiroDataRo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = -2658664610839100361L;
    Set<String> permissions;
    private long expire;
    private String username;
    Set<String> roles;
    private String sysId;
}
