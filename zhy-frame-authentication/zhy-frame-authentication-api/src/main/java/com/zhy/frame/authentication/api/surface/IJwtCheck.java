package com.zhy.frame.authentication.api.surface;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.api.surface
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.api.vo.JwtCheckVo;
import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 9:03
 */
public interface IJwtCheck {
    /**
     * 校验token
     *
     * @throws
     * @return: com.zhy.frame.base.core.api.ApiResult<com.zhy.frame.authentication.api.vo.JwtCheckVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 23:40
     */
    @GetMapping("/user/token/check")
    ApiResult<JwtCheckVo> checkToken();
}
