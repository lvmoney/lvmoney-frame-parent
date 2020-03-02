package com.zhy.frame.authentication.oauth2.center.db.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.authentication.oauth2.center.db.entity.ScopeDefinition;
import org.apache.ibatis.annotations.Select;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public interface ScopeDefinitionDao extends BaseMapper<ScopeDefinition> {
    /**
     * 获得路由规则
     *
     * @param scope: 路由范围
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.db.entity.ScopeDefinition
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 21:01
     */
    @Select("select * from scope_definition u where u.scope=#{scope}")
    ScopeDefinition findByScope(String scope);
}
