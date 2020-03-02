/**
 * 描述:
 * 包名:com.zhy.shiro.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午5:53:27
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.shiro.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月8日 下午5:53:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiroDataVo implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 173770456083286135L;
    Set<String> permissions;
    Set<String> roles;
    Long expire;

}
