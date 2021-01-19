package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.internationalize.core.util
 * 版本信息: 版本1.0
 * 日期:2020/6/15
 * Copyright XXXXXX科技有限公司
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/15 10:25
 */
public class I18nUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(I18nUtil.class);

    /**
     * 通过code 返回对应的提示信息
     *
     * @param code:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 10:54
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * 返回带参数的提示信息
     *
     * @param code:
     * @param args:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 10:54
     */
    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }


    /**
     * 根据语种查询信息
     *
     * @param code:
     * @param args:
     * @param defaultMessage:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 10:54
     */
    public static String getMessage(String code, Object[] args, String defaultMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        String content;
        try {
            content = messageSource.getMessage(code, args, locale);
        } catch (Exception e) {
            LOGGER.info("获取{}的提示消息失败：{}", code, e);
            content = defaultMessage;
        }
        return content;
    }
}
