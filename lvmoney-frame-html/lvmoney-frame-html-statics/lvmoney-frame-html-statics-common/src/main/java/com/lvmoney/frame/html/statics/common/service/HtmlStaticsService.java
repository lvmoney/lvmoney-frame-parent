package com.lvmoney.frame.html.statics.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.html.statics.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.html.statics.common.ro.HtmlStaticsRo;
import com.lvmoney.frame.html.statics.common.vo.HandHtmlStaticsVo;
import com.lvmoney.frame.html.statics.common.vo.HtmlStaticsVo;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 9:38
 */
public interface HtmlStaticsService {
    /**
     * 生成静态页面
     *
     * @param modelAndView:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 10:23
     */
    void buildHtmlPage(ModelAndView modelAndView);

    /**
     * 删除静态页面
     *
     * @param fileName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 10:24
     */
    void deleteHtmlPage(String fileName);

    /**
     * 保存静态请求数据
     *
     * @param htmlStaticsRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 13:29
     */
    void saveHtmlStatics(HtmlStaticsRo htmlStaticsRo);

    /**
     * 删除指定的数据
     *
     * @param handHtmlStaticsVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:35
     */
    void deleteByUrl(HandHtmlStaticsVo handHtmlStaticsVo);

    /**
     * 获得指定的数据
     *
     * @param handHtmlStaticsVo:
     * @throws
     * @return: HtmlStaticsVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:34
     */
    HtmlStaticsVo getByUrl(HandHtmlStaticsVo handHtmlStaticsVo);

    /**
     * 获取指定服务的所有静态页面资源
     *
     * @param serverName:
     * @throws
     * @return: java.util.Map
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:44
     */
    Map getByServerName(String serverName);

    /**
     * 根据服务名删除对应的静态页面数据
     *
     * @param serverName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:51
     */
    void deleteByServerName(String serverName);

    /**
     * 获得所有服务的页面静态化数据
     *
     * @throws
     * @return: java.util.List<java.util.Map>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:53
     */
    List<Map> getAll();

    /**
     * 删除所有服务的页面静态化数据
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 18:54
     */
    void deleteAll();
}
