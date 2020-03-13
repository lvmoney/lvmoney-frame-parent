package com.zhy.frame.authentication.oauth2.center.ro;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.ro
 * 版本信息: 版本1.0
 * 日期:2019/7/29
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.oauth2.center.vo.AuthorizationVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/29 17:44
 */
@Data
@NoArgsConstructor
public class AuthorizationCodeRo implements Serializable {

    private static final long serialVersionUID = -6411618662491549910L;
    /**
     * 失效时间
     */

    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, AuthorizationVo> data;
}
