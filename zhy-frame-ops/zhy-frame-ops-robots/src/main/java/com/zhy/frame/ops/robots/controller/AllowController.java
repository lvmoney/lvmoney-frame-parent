package com.zhy.frame.ops.robots.controller;/**
 * 描述:
 * 包名:com.zhy.robots.controller
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.ops.robots.constant.RobotsConstant;
import com.zhy.frame.ops.robots.ro.RobotsRo;
import com.zhy.frame.ops.robots.service.RobotsService;
import com.zhy.frame.ops.robots.vo.Robots;
import com.zhy.frame.ops.robots.vo.RobotsDisallow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static com.zhy.frame.ops.robots.constant.RobotsConstant.LINE_SEPARATOR;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 15:48
 */
@RestController
public class AllowController {
    @Autowired
    RobotsService robotsService;

    @GetMapping("/robots.txt")
    public void robotsTxt(HttpServletResponse response) throws IOException {
        Writer writer = response.getWriter();
        List<Robots> robotsList = robotsService.getRobots();
        robotsList.stream().forEach(e -> {
            try {
                writer.append(RobotsConstant.USER_AGENT).append(e.getUserAgent()).append(LINE_SEPARATOR);
                e.getDisallow().stream().forEach(e1 -> {
                            try {
                                writer.append(RobotsConstant.DISALLOW).append(e1.getDisallow()).append(LINE_SEPARATOR);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

    }

    @GetMapping("/robots/create")
    public void createRobotsTxt() {
        robotsService.buildRobotsTxt();
    }

    /**
     * 这是一个测试，真实场景可以通过mysql获得并维护到redis中
     */
    @GetMapping("/robots/save")
    public void saveRobots() {
        Robots robots = new Robots();
        robots.setUserAgent("*");
        List<RobotsDisallow> disallow = new ArrayList() {{
            add(new RobotsDisallow("/question/tag/"));
            add(new RobotsDisallow("/answer/*/edit"));
        }};
        robots.setDisallow(disallow);
        RobotsRo robotsRo1 = new RobotsRo();
        robotsRo1.setRobots(new ArrayList() {{
            add(robots);
        }});
        robotsService.saveRobots(robotsRo1);
    }

}
