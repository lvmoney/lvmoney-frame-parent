package com.lvmoney.frame.dispatch.feign.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.dispatch.feign.util
 * 版本信息: 版本1.0
 * 日期:2020/3/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/12 16:56
 */
public class FrameThrowableUtil {

    public static BusinessException throwable2BusinessException(Throwable cause) {
        if (cause instanceof BusinessException) {
            return (BusinessException) cause;
        } else {
            return new BusinessException(CommonException.Proxy.OTHER_ERROR.getCode(), cause.getMessage());
        }
    }
}
