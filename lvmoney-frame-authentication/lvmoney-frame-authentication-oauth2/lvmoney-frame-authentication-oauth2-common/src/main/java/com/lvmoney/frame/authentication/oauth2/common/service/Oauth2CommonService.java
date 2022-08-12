package com.lvmoney.frame.authentication.oauth2.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.common.service
 * 版本信息: 版本1.0
 * 日期:2020/2/18
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.oauth2.common.vo.UserInfo;
import com.lvmoney.frame.authentication.oauth2.common.ro.Oauth2UserRo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/2/18 19:09
 */
public interface Oauth2CommonService {
    /**
     * userdetail 初始化到redis中
     *
     * @param oauth2UserRo: 用户数据
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    void userDetails2Redis(Oauth2UserRo oauth2UserRo);


    /**
     * 通过用户名获得用户数据
     *
     * @param username: 用户名
     * @throws
     * @return: com.lvmoney.frame.authentication.oauth2.center.vo.UserInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:55
     */
    UserInfo getOauth2UserVo(String username);


}
