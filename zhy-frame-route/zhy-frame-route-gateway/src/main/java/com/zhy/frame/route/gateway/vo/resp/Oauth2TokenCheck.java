package com.zhy.frame.route.gateway.vo.resp;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/8/13
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/13 17:09
 */
@Data
public class Oauth2TokenCheck implements Serializable {
    private static final long serialVersionUID = -3078535076922423440L;
    private Set<String> scope;
    private String exp;
    private String jti;
    private String client_id;
    private boolean adopt;
}
