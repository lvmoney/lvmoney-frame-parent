package com.zhy.frame.route.gateway.server;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.server
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.route.gateway.vo.resp.Oauth2TokenCheck;
import com.zhy.frame.route.gateway.vo.resp.ShiroAuthorityCheck;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 8:41
 */
public interface AuthenticationServer {
    /**
     * 用户token校验
     *
     * @return: com.lvmoney.common.util.vo.ResultData<Oauth2TokenCheck>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:21
     */
    @GetMapping("/user/token/check")
    @ResponseBody
    ApiResult<Oauth2TokenCheck> oauth2CheckToken();

    /**
     * 用户权限校验
     *
     * @param servletPath:servlet地址
     * @return: com.lvmoney.common.util.vo.ResultData<ShiroAuthorityCheck>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:22
     */
    @GetMapping("/user/authority/check")
    @ResponseBody
    ApiResult<ShiroAuthorityCheck> shiroCheckAuthority(@RequestParam(value = "servletPath") String servletPath);
}
