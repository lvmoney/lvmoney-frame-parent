package com.zhy.frame.authentication.oauth2.center.service;

import com.zhy.frame.authentication.oauth2.center.ro.AuthorizationCodeRo;
import com.zhy.frame.authentication.oauth2.center.ro.Oauth2ClientDetailRo;
import com.zhy.frame.authentication.oauth2.center.vo.AuthorizationVo;
import com.zhy.platform.authentication.oauth2.common.ro.Oauth2UserRo;
import com.zhy.platform.authentication.oauth2.common.vo.UserInfo;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月18日 下午2:17:09
 */
public interface Oauth2RedisService {


    /**
     * 通过clientid获得client信息
     *
     * @param clientId: 客户端id
     * @throws
     * @return: org.springframework.security.oauth2.provider.client.BaseClientDetails
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    BaseClientDetails getClientDetailsByClientId(String clientId);

    /**
     * 保存client信息
     *
     * @param oauth2ClientDetailRo: 客户端信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void clientDetails2Redis(Oauth2ClientDetailRo oauth2ClientDetailRo);

    /**
     * 用户权限信息存到redis
     *
     * @param authorizationCodeRo: 权限信息
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void authorizationCode2Redis(AuthorizationCodeRo authorizationCodeRo);

    /**
     * 删除权限code
     *
     * @param code: code
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    void deleteAuthorizationCode(String code);

    /**
     * 获得权限code
     *
     * @param code: code
     * @throws
     * @return: com.zhy.frame.authentication.oauth2.center.vo.AuthorizationVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:56
     */
    AuthorizationVo getAuthorizationCodeByCode(String code);


}
