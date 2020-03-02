package com.zhy.demo.user.vo.req;/**
 * 描述:
 * 包名:com.zhy.tmc.user.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 17:45
 */
@Data
public class UserLoginReqVo implements Serializable {
    private static final long serialVersionUID = 7301576770964529695L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 系统编号
     */
    private String sysId;

}
