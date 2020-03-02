package com.zhy.demo.user.vo.resp;/**
 * 描述:
 * 包名:com.zhy.tmc.api.vo.resp
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 16:59
 */
@Data
public class GetUserRespVo implements Serializable {
    private static final long serialVersionUID = -1129319803622175147L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 性别
     */
    private String sex;
}
