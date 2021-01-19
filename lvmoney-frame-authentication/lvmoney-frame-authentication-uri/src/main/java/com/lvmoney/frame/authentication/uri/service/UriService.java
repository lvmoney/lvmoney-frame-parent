package com.lvmoney.frame.authentication.uri.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.uri.service
 * 版本信息: 版本1.0
 * 日期:2020/3/4
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.uri.ro.SysUriRo;
import com.lvmoney.frame.authentication.uri.vo.SysUriVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/4 11:26
 */
public interface UriService {
    /**
     * 删除指定系统的所有的访问url数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void deleteSysUriByServerName(String serverName);

    /**
     * 获得指定系统所有的shiro url数据
     *
     * @param serverName: 服务名称
     * @throws
     * @return: java.util.List<SysUriVo>
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:08
     */
    List<SysUriVo> getSysUriByServerName(String serverName);


    /**
     * 把系统所有访问的接口数据保存到redis中
     *
     * @param shiroServerRo: 所有的访问接口数据
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:07
     */
    void saveSysUri(SysUriRo shiroServerRo);
}
