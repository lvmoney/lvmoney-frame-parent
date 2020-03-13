package com.zhy.frame.authentication.oauth2.center.vo;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class FrameOauth2AccessToken implements OAuth2AccessToken, Serializable {
    private Map<String, Object> additionalInformation;
    private Set<String> scope;
    private FrameOauth2RefreshToken refreshToken;
    private String tokenType;
    private boolean expired;
    private String value;
    private int expiresIn;
    private Date expiration;
}
