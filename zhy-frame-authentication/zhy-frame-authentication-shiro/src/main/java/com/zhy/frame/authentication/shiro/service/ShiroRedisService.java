/**
 * 描述:
 * 包名:com.zhy.shiro.service
 * 版本信息: 版本1.0
 * 日期:2019年1月8日  下午4:49:11
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.shiro.service;


import com.zhy.frame.core.ro.UserRo;
import com.zhy.frame.authentication.shiro.ro.ShiroDataRo;
import com.zhy.frame.authentication.shiro.ro.ShiroServerRo;
import com.zhy.frame.authentication.shiro.ro.ShiroUriRo;
import com.zhy.frame.authentication.shiro.vo.ShiroDataVo;
import com.zhy.frame.authentication.shiro.vo.ShiroUriVo;
import com.zhy.frame.authentication.shiro.vo.SysServiceDataVo;
import com.zhy.frame.authentication.shiro.vo.UserVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月8日 下午4:49:11
 */

public interface ShiroRedisService {
    /**
     * 获得shiro数据
     *
     * @param username: 用户名
     * @throws
     * @return: ShiroDataVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:06
     */
    ShiroDataVo getShiroData(String username);

    /**
     * 通过token获得用户
     *
     * @param token: token
     * @throws
     * @return: UserVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:06
     */
    UserVo getUser(String token);

    /**
     * 存储shiro数据
     *
     * @param shiroDataRo: shiro数据实体
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveShiroData(ShiroDataRo shiroDataRo);

    /**
     * 把系统所有访问的接口数据保存到redis中
     *
     * @param shiroServerRo: 所有的访问接口数据
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveShiroServerData(ShiroServerRo shiroServerRo);

    /**
     * 删除市容数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void deleteShiroServerData(String serverName);

    /**
     * 获得所有的市容数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: java.util.List<SysServiceDataVo>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    List<SysServiceDataVo> getShiroServerAll(String serverName);

    /**
     * 存储shiro url数据
     *
     * @param shiroUriRo:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    void saveShiroUriData(ShiroUriRo shiroUriRo);

    /**
     * 通过uri获得shiro数据
     *
     * @param uri:
     * @throws
     * @return: ShiroUriVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    ShiroUriVo getShiroUriData(String uri);

    /**
     * 存储用户数据
     *
     * @param userRo: 用户数据
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:09
     */
    void saveToken2Redis(UserRo userRo);


}
