package com.lvmoney.frame.core.web;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.web
 * 版本信息: 版本1.0
 * 日期:2019/11/29
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：错误处理，当不引入shiro的时候会有用，引入shiro会使用另一种方式，详见FrameExceptionHandlerAdvice
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/29 9:29
 */
@RestController
public class FrameErrorController implements ErrorController {
    /**
     * 错误code
     */
    private final static String STATUS_CODE = "javax.servlet.error.status_code";

    /**
     * 系统级别错误，按照HttpStatus的方式返回错误和对应的错误码
     *
     * @param request:
     * @throws
     * @return: com.lvmoney.frame.core.api.ApiResult<?>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 14:25
     */
    @RequestMapping("error")
    public ApiResult<?> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(STATUS_CODE);
        return ApiResult.error(HttpStatus.valueOf(statusCode).value(), HttpStatus.valueOf(statusCode).getReasonPhrase());
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
