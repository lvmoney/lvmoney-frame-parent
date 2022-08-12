package com.lvmoney.frame.base.serializer.interceptor;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.interceptor
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.base.core.util.CoreStringUtil;
import com.lvmoney.frame.base.interceptor.annotations.InterceptorBean;
import com.lvmoney.frame.base.interceptor.prop.InterceptorOrderProp;
import com.lvmoney.frame.base.serializer.config.SerializerConfig;
import com.lvmoney.frame.base.serializer.vo.InterceptorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 15:01
 */
@Configuration
public class CoreInterceptorConfig extends SerializerConfig {
    @Autowired
    InterceptorOrderProp interceptorOrderProp;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(addInterceptor(registry));
    }

    /**
     * 通过自定义注解的方式把自定义拦截器注入到容器托管
     *
     * @param registry:
     * @throws
     * @return: org.springframework.web.servlet.config.annotations.InterceptorRegistry
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/8/20 12:44
     */

    public InterceptorRegistry addInterceptor(InterceptorRegistry registry) {
        Map<String, Object> data = SpringBeanUtil.getBeansWithAnnotation(InterceptorBean.class);
        List<InterceptorVo> list = new ArrayList<>();
        data.forEach((k, v) -> {
            if (v instanceof HandlerInterceptorAdapter) {
                Class<? extends HandlerInterceptorAdapter> clazz = (Class<? extends HandlerInterceptorAdapter>) v.getClass();
                if (clazz.isAnnotationPresent(InterceptorBean.class)) {
                    InterceptorBean interceptorBean = clazz.getAnnotation(InterceptorBean.class);
                    InterceptorVo interceptorVo = new InterceptorVo();
                    interceptorVo.setHandlerInterceptorAdapter((HandlerInterceptorAdapter) v);
                    Object obj = interceptorOrderProp.getInterceptorOrderMap().get(CoreStringUtil.lowerFirstCode(k));
                    if (ObjectUtils.isEmpty(obj)) {
                        interceptorVo.setOrder(0);
                    } else {
                        int order = Integer.parseInt(obj.toString());
                        interceptorVo.setOrder(order);
                    }

                    interceptorVo.setPathPatterns(interceptorBean.pathPatterns());
                    list.add(interceptorVo);
                }
            }
        });
        sortDesc(list).forEach(e -> {
            registry.addInterceptor((HandlerInterceptorAdapter) e.getHandlerInterceptorAdapter())
                    .addPathPatterns(e.getPathPatterns());
        });
        return registry;
    }

    /**
     * 排序，决定拦截器执行顺序
     *
     * @param list:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.core.vo.InterceptorVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/8/20 12:44
     */

    private List<InterceptorVo> sortDesc(List<InterceptorVo> list) {
        Collections.sort(list, new Comparator<InterceptorVo>() {
            @Override
            public int compare(InterceptorVo o1, InterceptorVo o2) {
                return new Integer(o2.getOrder()).compareTo(o1.getOrder());
            }
        });
        return list;
    }

}
