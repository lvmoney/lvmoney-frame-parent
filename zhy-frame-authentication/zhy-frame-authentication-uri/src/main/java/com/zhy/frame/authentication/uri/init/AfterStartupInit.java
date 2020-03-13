package com.zhy.frame.authentication.uri.init;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/22
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.authentication.uri.annotation.SysResouce;
import com.zhy.frame.authentication.uri.ro.SysUriRo;
import com.zhy.frame.authentication.uri.service.UriService;
import com.zhy.frame.authentication.uri.vo.SysUriVo;
import com.zhy.frame.base.core.constant.BaseConstant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class AfterStartupInit implements InitializingBean {
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    UriService uriService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    @Override
    public void afterPropertiesSet() throws Exception {
        List<SysUriVo> sysUriVoList = new ArrayList<SysUriVo>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            SysUriVo sysServiceDataVo = new SysUriVo();
            RequestMappingInfo info = m.getKey();
            HandlerMethod handlerMethod = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                sysServiceDataVo.setUri(url);
            }
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(SysResouce.class)) {
                SysResouce sysResouce = method.getAnnotation(SysResouce.class);
                sysServiceDataVo.setDescrption(sysResouce.describe());
            }
            sysUriVoList.add(sysServiceDataVo);
        }
        SysUriRo shiroServerRo = new SysUriRo();
        Map<String, List<SysUriVo>> data = new HashMap(BaseConstant.MAP_DEFAULT_SIZE) {{
            put(serverName, sysUriVoList);
        }};
        shiroServerRo.setData(data);
        uriService.deleteSysUriByServerName(serverName);
        uriService.saveSysUri(shiroServerRo);
    }


}
