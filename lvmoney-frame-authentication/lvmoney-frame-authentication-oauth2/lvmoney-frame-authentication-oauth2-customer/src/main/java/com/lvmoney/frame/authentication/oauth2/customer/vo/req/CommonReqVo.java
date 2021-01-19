package com.lvmoney.frame.authentication.oauth2.customer.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.frame.oauth2.customer.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/11
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/11 9:13
 */
@Data
public class CommonReqVo implements Serializable {
    private String client_id;
    private String client_secret;
    private String grant_type;
}
