package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class Base64Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Base64Util.class);

    /**
     * 加密
     *
     * @param: [b]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX科技有限公司
     * 2019/3/5
     */
    public static String encode(byte[] b) {
        String s = null;
        if (b != null) {
            String result = Base64.getEncoder().encodeToString(b);
            return result;
        }
        throw new BusinessException(CommonException.Proxy.BASE64_PARAM_IS_REQUIRED);

    }

    /**
     * 加密
     *
     * @param: [src]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX科技有限公司
     * 2019/3/5
     */
    public static String encode(String src) {
        if (!StringUtils.isBlank(src)) {
            return encode(src.getBytes());
        }
        throw new BusinessException(CommonException.Proxy.BASE64_PARAM_IS_REQUIRED);
    }

    /**
     * 解密
     *
     * @param: [s]
     * @return: byte[]
     * @author： lvmoney /XXXXXX科技有限公司
     * 2019/3/5
     */
    public static byte[] decode(String s) {
        byte[] result = Base64.getDecoder().decode(s);
        return result;
    }

    /**
     * 解密
     *
     * @param: [s]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX科技有限公司
     * 2019/3/5
     */
    public static String decodeStr(String s) {
        byte[] b = decode(s);
        try {
            return b != null ? (new String(b, BaseConstant.CHARACTER_ENCODE_UTF8_UPPER)) : null;
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("base解密报错{}", e);
            throw new BusinessException(CommonException.Proxy.BASE64_ENCODING_ERROR);
        }
    }

}
