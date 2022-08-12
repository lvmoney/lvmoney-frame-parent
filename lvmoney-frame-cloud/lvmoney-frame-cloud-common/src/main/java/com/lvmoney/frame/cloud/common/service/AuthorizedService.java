package com.lvmoney.frame.cloud.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.cloud.common.service
 * 版本信息: 版本1.0
 * 日期:2020/3/7
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.cloud.common.ro.AuthorizedRo;
import com.lvmoney.frame.cloud.common.vo.AuthorizedVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/7 22:33
 */
public interface AuthorizedService {
    /**
     * 创建某个服务被授权调用的sysId
     *
     * @param authorizedRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/7 22:34
     */
    void addAuthorized2Server(AuthorizedRo authorizedRo);

    /**
     * 删除指定服务被授权调用的sysId
     *
     * @param serverName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/7 22:36
     */
    void deleteAuthorizedByServer(String serverName);

    /**
     * 获得指定系统的被授权调用的系统id列表
     *
     * @param serverName:
     * @throws
     * @return: com.lvmoney.frame.cloud.common.vo.AuthorizedVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/7 22:47
     */
    AuthorizedVo getSysIdByServer(String serverName);

}
