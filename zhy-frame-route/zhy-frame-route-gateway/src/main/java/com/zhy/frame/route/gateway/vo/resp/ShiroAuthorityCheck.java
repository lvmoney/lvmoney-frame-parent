package com.zhy.frame.route.gateway.vo.resp;/**
 * 描述:
 * 包名:com.zhy.k8s.login.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 11:22
 */
@Data
public class ShiroAuthorityCheck implements Serializable {
    private static final long serialVersionUID = 5204068855314740562L;
    private boolean pass;
}
