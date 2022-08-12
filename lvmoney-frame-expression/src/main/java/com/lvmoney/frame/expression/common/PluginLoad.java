package com.lvmoney.frame.expression.common;
/**
 * 描述:
 * 包名:com.lvmoney.frame.expression.common
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.expression.annotations.MathFunction;
import com.lvmoney.frame.expression.eval.Function;
import com.lvmoney.frame.expression.eval.Parser;
import com.lvmoney.frame.expression.exception.ExpressionException;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @describe：自定义函数多参数父类
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class PluginLoad {
    private static final Logger LOGGER = LoggerFactory.getLogger(PluginLoad.class);

    public static void registerPluginsForPackage(String packageName) {
        new Reflections(packageName)
                .getTypesAnnotatedWith(MathFunction.class)
                .stream()
                .forEach(c -> load(c));
    }

    private static void load(Class<?> c) {
        String name = c.getAnnotation(MathFunction.class).name();
        try {
            Parser.registerFunction(name, (Function) c.newInstance());
        } catch (Exception e) {
            LOGGER.error("{} register function error:{}", name, e);
            throw new BusinessException(ExpressionException.Proxy.REGISTER_FUNCTION_ERROR);
        }
    }
}
