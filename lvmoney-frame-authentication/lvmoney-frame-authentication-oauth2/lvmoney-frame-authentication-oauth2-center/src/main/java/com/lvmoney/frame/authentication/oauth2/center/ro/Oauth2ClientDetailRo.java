package com.lvmoney.frame.authentication.oauth2.center.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2018年10月30日
 * Copyright xxxx科技有限公司
 */


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@NoArgsConstructor
public class Oauth2ClientDetailRo implements Serializable {
    private static final long serialVersionUID = -2881019011190253183L;
    /**
     * 失效时间
     */

    private Long expire;
    /**
     * 把oauth2的user信息统一放到redis中
     */
    private Map<String, BaseClientDetails> data;
}
