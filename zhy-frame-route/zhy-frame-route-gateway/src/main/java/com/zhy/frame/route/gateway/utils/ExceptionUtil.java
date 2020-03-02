package com.zhy.frame.route.gateway.utils;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.util
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.core.util.JsonUtil;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;

import java.nio.charset.StandardCharsets;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 14:15
 */
public class ExceptionUtil {
    public static DataBuffer filterExceptionHandle(ServerHttpResponse serverHttpResponse, BusinessException ex) {
        ApiResult resultData = ApiResult.error(ex.getCode(), ex.getDescription());
        byte[] bytes = JsonUtil.t2JsonString(resultData).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverHttpResponse.bufferFactory().wrap(bytes);
        return buffer;
    }
}
