package com.zhy.frame.authentication.oauth2.center.vo;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.vo
 * 版本信息: 版本1.0
 * 日期:2019/7/30
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/30 20:55
 */
@Data
public class Oauth2Request implements Serializable {
    private boolean approved;
    private String clientId;
    private String redirectUri;
    private boolean refresh;
    private Set<String> responseTypes;
    private Set<String> scope;
    private Map<String, String> requestParameters;

}
