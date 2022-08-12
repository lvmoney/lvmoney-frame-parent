package com.lvmoney.frame.cloud.base.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/21 16:01
 */
@Data
public class SshCommonVo implements Serializable {
    private static final long serialVersionUID = 3080428197712910826L;
    private String password;
    private String username;
    private int port;
    private String host;
}
