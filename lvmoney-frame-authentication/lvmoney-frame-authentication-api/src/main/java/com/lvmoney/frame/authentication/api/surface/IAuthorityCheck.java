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
import com.lvmoney.frame.prefix.constant.FramePrefixConstant;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/15 13:29
 */
@RequestMapping(FramePrefixConstant.FRAME_AUTHENTICATION_PREFIX)
public interface IAuthorityCheck {
    /**
     * 校验token
     * z
     *
     * @param token:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.authentication.api.vo.JwtCheckVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/15 14:14
     */
    @GetMapping("/token/check")
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
    @PostMapping("/autority/check")
    ApiResult<ShiroCheckVo> checkAuthority(@RequestBody ShiroCheckAo shiroCheckAo);
}
