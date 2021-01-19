package com.lvmoney.frame.authentication.oauth2.center.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/29
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/29 23:23
 */
@Data
public class AuthorizationVo implements Serializable {

    private OAuth2Request oAuth2Request;
    private Authentication authentication;
}
