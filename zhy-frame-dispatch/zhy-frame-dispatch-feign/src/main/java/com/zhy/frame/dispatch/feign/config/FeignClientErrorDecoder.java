package com.zhy.frame.dispatch.feign.config;/**
 * 描述:
 * 包名:com.zhy.frame.dispatch.feign.handler
 * 版本信息: 版本1.0
 * 日期:2020/3/6
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.dispatch.common.exception.DispatchException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;


/**
 * @describe：当rpc远程返回错误时进入，获得错误码和错误信息并向下传递方便业务接口拿到处理
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/6 17:08
 */
@Configuration
public class FeignClientErrorDecoder implements ErrorDecoder {
    private static final String RESULT_CODE = "code";
    private static final String RESULT_MSG = "msg";

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "";
        try {
            body = Util.toString(response.body().asReader());
        } catch (IOException e) {
            throw new BusinessException(DispatchException.Proxy.FEIGN_BODY_ERROR);
        }
        JSONObject obj = (JSONObject) JSON.parse(body);
        Integer code = (Integer) obj.get(RESULT_CODE);
        String msg = (String) obj.get(RESULT_MSG);
        throw new BusinessException(code, msg);
    }
}
