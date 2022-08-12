package com.lvmoney.frame.dispatch.feign.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.dispatch.feign.handler
 * 版本信息: 版本1.0
 * 日期:2020/3/6
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.enums.ExceptionCodeLevel;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.dispatch.common.exception.DispatchException;
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
    private static final String RESULT_ERROR_MSG = "errorMessage";

    private static final String DEFAULT_ERROR_MSG = "rpc server error";

    private static final String RESULT_ERROR = "error";

    private static final String ERROR_STATUS = "status";

    private static final Integer DEFAULT_ERROR_CODE = ExceptionCodeLevel.OTHER.getValue();

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "";
        try {
            body = Util.toString(response.body().asReader());
        } catch (IOException e) {
            throw new BusinessException(DispatchException.Proxy.FEIGN_BODY_ERROR);
        }
        JSONObject obj = (JSONObject) JSON.parse(body);
        Integer code;
        if (ObjectUtil.isNotEmpty(obj.get(ERROR_STATUS))) {
            code = obj.getInteger(ERROR_STATUS);
        } else {
            code = (Integer) obj.getOrDefault(RESULT_CODE, DEFAULT_ERROR_CODE);
        }
        String msg = "";
        if (ObjectUtil.isNotEmpty(obj.get(RESULT_ERROR_MSG))) {
            msg = obj.getString(RESULT_ERROR_MSG);
        } else if (ObjectUtil.isNotEmpty(obj.get(RESULT_ERROR))) {
            msg = obj.getString(RESULT_ERROR);
        } else {
            msg = (String) obj.getOrDefault(RESULT_MSG, DEFAULT_ERROR_MSG);
        }
        throw new BusinessException(code, msg);
    }
}