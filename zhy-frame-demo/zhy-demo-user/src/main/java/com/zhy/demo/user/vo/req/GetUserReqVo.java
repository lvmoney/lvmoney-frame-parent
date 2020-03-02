package com.zhy.demo.user.vo.req;/**
 * 描述:
 * 包名:com.zhy.tmc.api.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 16:57
 */
@Data
public class GetUserReqVo implements Serializable {
    private static final long serialVersionUID = 1119621488895385327L;
    /**
     * 用户电话号码
     */
    @NotBlank(message = "{user.login.username}")
    private String username;
    @NotBlank(message = "{user.password.username}")
    private String password;
}
