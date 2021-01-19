package com.lvmoney.frame.sync.canal.common.vo;
/**
 * 描述:
 * 包名:com.lvmoney.common.handler
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.sync.canal.common.annotation.ListenPoint;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe：监听切点信息
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
public class ListenerPointVo {
    /**
     * 目标
     */
    private Object target;

    /**
     * 监听的方法和节点
     */
    private Map<Method, ListenPoint> invokeMap = new HashMap<>();

    /**
     * 构造方法，设置目标，方法以及注解类型
     *
     * @param target 目标
     * @param method 方法
     * @param anno   注解类型
     * @return
     */
    public ListenerPointVo(Object target, Method method, ListenPoint anno) {
        this.target = target;
        this.invokeMap.put(method, anno);
    }

    /**
     * 返回目标类
     *
     * @param
     * @return
     */
    public Object getTarget() {
        return target;
    }

    /**
     * 获取监听的操作方法和节点
     *
     * @param
     * @return
     */
    public Map<Method, ListenPoint> getInvokeMap() {
        return invokeMap;
    }
}
