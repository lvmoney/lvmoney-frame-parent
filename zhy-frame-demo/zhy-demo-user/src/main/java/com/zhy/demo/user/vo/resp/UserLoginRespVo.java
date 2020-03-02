package com.zhy.demo.user.vo.resp;/**
 * 描述:
 * 包名:com.zhy.tmc.user.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 17:46
 */
@Data
public class UserLoginRespVo implements Serializable {
    private static final long serialVersionUID = 5952249131814467056L;
    private String token;
}
