package com.lvmoney.frame.authentication.oauth2.center.exception;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.center.exception
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/8 9:35
 */
@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = CustomOauthExceptionSerializer.class)
@org.codehaus.jackson.map.annotate.JsonSerialize(using = OAuth2ExceptionJackson1Serializer.class)
@org.codehaus.jackson.map.annotate.JsonDeserialize(using = OAuth2ExceptionJackson1Deserializer.class)
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = OAuth2ExceptionJackson2Deserializer.class)
public class CustomOauthException extends OAuth2Exception {

    public CustomOauthException(String msg) {
        super(msg);
    }
}
