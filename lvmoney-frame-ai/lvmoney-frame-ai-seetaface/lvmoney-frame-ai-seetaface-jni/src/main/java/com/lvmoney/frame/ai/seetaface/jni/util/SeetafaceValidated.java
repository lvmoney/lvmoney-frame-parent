package com.lvmoney.frame.ai.seetaface.jni.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.base.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @describe：断言
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class SeetafaceValidated {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeetafaceValidated.class);

    /**
     * 校验入参以及类是否完整
     *
     * @param isClose: 是否关闭
     * @param objects: 入参对象
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:49
     */
    public static void validate(boolean isClose, Object... objects) {
        if (isClose) {
            throw new BusinessException(SeetafaceException.Proxy.RESOURCE_IS_CLOSED);
        }
        for (Object o : objects) {
            if (o == null) {
                throw new BusinessException(SeetafaceException.Proxy.PARAM_IS_NULL);
            }
        }
    }

}
