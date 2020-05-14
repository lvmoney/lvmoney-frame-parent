package com.zhy.frame.ops.robots.service;/**
 * 描述:
 * 包名:com.zhy.robots.service
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.ops.robots.ro.RobotsRo;
import com.zhy.frame.ops.robots.vo.Robots;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 16:22
 */
public interface RobotsService {
    /**
     * 保存robots
     *
     * @param robotsRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/10/29 16:24
     */
    void saveRobots(RobotsRo robotsRo);

    /**
     * 创建robots.txt
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/10/29 16:54
     */
    void buildRobotsTxt();

    /**
     * 从redis获得robots
     *
     * @throws
     * @return: com.zhy.robots.ro.RobotsRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/10/29 17:03
     */
    List<Robots> getRobots();


}
