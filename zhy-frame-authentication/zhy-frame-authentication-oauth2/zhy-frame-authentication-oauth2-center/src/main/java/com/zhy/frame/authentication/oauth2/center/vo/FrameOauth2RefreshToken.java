package com.zhy.frame.authentication.oauth2.center.vo;

import lombok.Data;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class FrameOauth2RefreshToken implements OAuth2RefreshToken, Serializable {
    private static final long serialVersionUID = -8111339357304925068L;
    private String value;
    private Long expiration;

}
