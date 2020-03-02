package com.zhy.frame.authentication.oauth2.center.service;

import com.zhy.frame.authentication.oauth2.center.vo.ScopeDefinitionVo;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface ScopeDefinitionService {
    /**
     * 获得路由规则
     *
     * @param scope: 范围
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.ScopeDefinitionVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:02
     */
    ScopeDefinitionVo findByScope(String scope);
}
