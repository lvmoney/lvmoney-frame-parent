package com.zhy.frame.ops.fuse.sentinel.client.config;/**
 * 描述:
 * 包名:com.zhy.frame.ops.fuse.sentinel.client.config
 * 版本信息: 版本1.0
 * 日期:2020/4/10
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.ops.fuse.common.exception.SentinelException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/10 22:14
 */
@Component
public class FrameUrlBlockHandler implements UrlBlockHandler {

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        ApiResult apiResult = new ApiResult();
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
            apiResult = buildErrorResult(SentinelException.Proxy.SENTINEL_FLOW_ERROR);
        } else if (e instanceof DegradeException) {
            apiResult = buildErrorResult(SentinelException.Proxy.SENTINEL_DEGRADED_ERROR);
        } else if (e instanceof ParamFlowException) {
            apiResult = buildErrorResult(SentinelException.Proxy.SENTINEL_PARAM_FLOW_ERROR);
        } else if (e instanceof SystemBlockException) {
            apiResult = buildErrorResult(SentinelException.Proxy.SENTINEL_SYSTEM_CLOCK_ERROR);
        } else if (e instanceof AuthorityException) {
            apiResult = buildErrorResult(SentinelException.Proxy.SENTINEL_AUTHORITY_ERROR);
        }
        response.setStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value());
        response.setCharacterEncoding(BaseConstant.CHARACTER_ENCODE_UTF8_LOWER);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        new ObjectMapper().writeValue(response.getWriter(), apiResult);
    }

    private ApiResult buildErrorResult(SentinelException.Proxy proxy) {
        ApiResult apiResult = new ApiResult();
        apiResult.setCode(proxy.getCode());
        apiResult.setMsg(proxy.getDescription());
        apiResult.setSuccess(false);
        apiResult.setDate(new Date());
        return apiResult;
    }
}
