/**
 * 描述:
 * 包名:com.lvmoney.shiro.service
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午4:49:11
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.authentication.shiro.service;


import com.lvmoney.frame.authentication.shiro.ro.ShiroDataRo;
import com.lvmoney.frame.authentication.shiro.ro.ShiroUriRo;
import com.lvmoney.frame.authentication.shiro.vo.ShiroDataVo;
import com.lvmoney.frame.authentication.shiro.vo.ShiroUriVo;
import com.lvmoney.frame.core.vo.UserVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月8日 下午4:49:11
 */

public interface ShiroRedisService {
    /**
     * 获得用户的shiro数据
     *
     * @param username: 用户名
     * @throws
     * @return: ShiroDataVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:06
     */
    ShiroDataVo getShiroData(String username);


    /**
     * 存储用户的角色权限信息到redis数据
     *
     * @param shiroDataRo: shiro数据实体
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveShiroData(ShiroDataRo shiroDataRo);




    /**
     * 存储url 能被那些角色或者权限访问
     *
     * @param shiroUriRo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    void saveShiroUriData(ShiroUriRo shiroUriRo);

    /**
     * 通过uri获得能被哪些角色和权限访问
     *
     * @param uri:
     * @throws
     * @return: ShiroUriVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    ShiroUriVo getShiroUriData(String uri);



}
