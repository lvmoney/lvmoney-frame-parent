package com.lvmoney.frame.authentication.shiro.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/23
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.authentication.shiro.vo.ShiroUriVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiroUriRo implements Serializable {
    private static final long serialVersionUID = 66817079523606816L;
    private String uri;
    private long expire;
    private ShiroUriVo shiroUriVo;
    private String sysId;

}
