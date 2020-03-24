package com.zhy.frame.route.gateway.service;/**
 * 描述:
 * 包名:com.zhy.k8s.gateway.service
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.route.gateway.ro.WhiteListRo;
import com.zhy.frame.route.gateway.vo.WhiteListVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:42
 */
public interface WhiteListService {
    /**
     * 白名单数据存入redis
     *
     * @param whiteListRo: 白名单实体
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:59
     */
    void saveWhiteList2Redis(WhiteListRo whiteListRo);

    /**
     * 通过服务名获得对应的白名单
     *
     * @param serverName: 服务名
     * @throws
     * @return: WhiteListVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:59
     */
    WhiteListVo getWhiteList(String serverName);

    /**
     * 判断serverName 在 redis是否存在
     *
     * @param serverName: 服务名
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/10 8:59
     */
    boolean isExist(String serverName);

}
