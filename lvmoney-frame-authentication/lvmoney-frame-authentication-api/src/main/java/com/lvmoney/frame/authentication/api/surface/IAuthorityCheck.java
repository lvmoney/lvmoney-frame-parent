package com.lvmoney.frame.authentication.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.api.surface
 * 版本信息: 版本1.0
 * 日期:2020/3/15
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.api.ao.ShiroCheckAo;
import com.lvmoney.frame.authentication.api.vo.JwtCheckVo;
import com.lvmoney.frame.authentication.api.vo.ShiroCheckVo;
import com.lvmoney.frame.base.core.api.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2020/3/15 13:29  
 */
public interface IAuthorityCheck {
    /**
     * 校验token
     *
     * @param token:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.authentication.api.vo.JwtCheckVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/15 14:14
     */
    @GetMapping("/user/token/check")
    ApiResult<JwtCheckVo> checkToken(@RequestParam("token") String token);

    /**
     * 校验权限
     *
     * @param shiroCheckAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.authentication.api.vo.ShiroCheckVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 23:31
     */
    @PostMapping("/user/autority/check")
    ApiResult<ShiroCheckVo> checkAuthority(@RequestBody ShiroCheckAo shiroCheckAo);
}
