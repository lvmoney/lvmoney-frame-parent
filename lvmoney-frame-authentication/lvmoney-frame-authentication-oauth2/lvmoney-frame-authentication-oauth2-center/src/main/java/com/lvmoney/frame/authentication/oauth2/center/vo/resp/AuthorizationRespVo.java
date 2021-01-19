package com.lvmoney.frame.authentication.oauth2.center.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.oauth2.center.vo.Authentication;
import com.lvmoney.frame.authentication.oauth2.center.vo.Oauth2Request;
import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/30 21:06
 */
@Data
public class AuthorizationRespVo implements Serializable {
    private Authentication authentication;
    private Oauth2Request oAuth2Request;
}
