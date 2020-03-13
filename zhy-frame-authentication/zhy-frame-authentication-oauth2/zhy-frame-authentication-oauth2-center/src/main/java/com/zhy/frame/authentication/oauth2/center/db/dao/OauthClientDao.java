package com.zhy.frame.authentication.oauth2.center.db.dao;/**
 * 描述:
 * 包名:com.zhy.frame.authentication.oauth2.center.db.dao
 * 版本信息: 版本1.0
 * 日期:2019/8/5
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.frame.authentication.oauth2.center.db.entity.OauthClient;
import org.apache.ibatis.annotations.Select;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/5 17:45
 */
public interface OauthClientDao extends BaseMapper<OauthClient> {
    /**
     * 通过客户端id获得客户端数据
     *
     * @param clientId:
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.db.entity.OauthClient
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:58
     */
    @Select("select * from oauth_client u where u.client_id=#{clientId}")
    OauthClient findByClientId(String clientId);
}
