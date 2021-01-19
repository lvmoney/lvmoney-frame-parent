package com.lvmoney.frame.authentication.oauth2.center.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Data
public class Details implements Serializable {
    private static final long serialVersionUID = -305412935653682903L;
    private String graphId;
    private String inputVerificationCode;
    private String remoteAddress;
    private String sessionId;
}
