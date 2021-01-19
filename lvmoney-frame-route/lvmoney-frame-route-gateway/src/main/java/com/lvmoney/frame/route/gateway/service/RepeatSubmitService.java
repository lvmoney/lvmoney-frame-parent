package com.lvmoney.frame.route.gateway.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.route.gateway.service
 * 版本信息: 版本1.0
 * 日期:2020/7/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.route.gateway.vo.RepeatSubmitVo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/16 9:29
 */
public interface RepeatSubmitService {
    /**
     * 根据服务名称获得重复提交数据
     *
     * @param serverName:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.route.gateway.vo.RepeatSubmitVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/16 13:40
     */
    List<RepeatSubmitVo> getRepeatSubmitVoByServerName(String serverName);
}
