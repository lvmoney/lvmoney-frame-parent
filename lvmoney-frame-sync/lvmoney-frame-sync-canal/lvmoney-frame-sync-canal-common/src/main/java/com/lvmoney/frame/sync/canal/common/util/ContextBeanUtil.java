package com.lvmoney.frame.sync.canal.common.util;/**
 * 描述:
 * 包名:com.lvmoney.bigdata.canal.redis.util
 * 版本信息: 版本1.0
 * 日期:2019/7/18
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.sync.canal.common.annotation.CanalEventListener;
import com.lvmoney.frame.sync.canal.common.annotation.ListenPoint;
import com.lvmoney.frame.sync.canal.common.listener.DataHandEventListener;
import com.lvmoney.frame.sync.canal.common.spring.HandListenerContext;
import com.lvmoney.frame.sync.canal.common.vo.ListenerPointVo;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/18 9:54
 */
public class ContextBeanUtil {
    /**
     * 通过spring上下文获得监听点对象list
     *
     * @param handListenerContext:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.sync.canal.common.vo.ListenerPointVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/15 17:34
     */
    public static List<ListenerPointVo> getDataHandEventListener(HandListenerContext handListenerContext) {
        List<ListenerPointVo> result = new ArrayList<>();
        Map<String, DataHandEventListener> map = handListenerContext.getStrategyMap();
        for (Map.Entry<String, DataHandEventListener> entry : map.entrySet()) {
            Object clazz = entry.getValue();
            if (clazz.getClass().isAnnotationPresent(CanalEventListener.class)) {
                Method[] methods = clazz.getClass().getDeclaredMethods();
                if (methods != null && methods.length > 0) {
                    for (Method method : methods) {
                        //获取监听的节点
                        ListenPoint listenPoint = AnnotationUtils.findAnnotation(method, ListenPoint.class);
                        if (listenPoint != null) {
                            result.add(new ListenerPointVo(clazz, method, listenPoint));
                        }
                    }
                }
            }
        }
        return result;
    }
}
