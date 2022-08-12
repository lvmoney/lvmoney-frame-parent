package com.lvmoney.frame.authentication.shiro.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/23
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiroUriVo implements Serializable {
    private static final long serialVersionUID = 2147939672783988212L;
    private Set<String> role;
    private Set<String> permission;
}
