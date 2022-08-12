package com.lvmoney.frame.ops.robots.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.robots.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.core.util.ModuleUtil;
import com.lvmoney.frame.ops.robots.constant.RobotsConstant;
import com.lvmoney.frame.ops.robots.ro.RobotsRo;
import com.lvmoney.frame.ops.robots.service.RobotsService;
import com.lvmoney.frame.ops.robots.vo.Robots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.lvmoney.frame.ops.robots.constant.RobotsConstant.LINE_SEPARATOR;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 16:22
 */
@Service
public class RobotsServiceImpl implements RobotsService {
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    @Autowired
    private BaseRedisService baseRedisService;

    @Override
    public void saveRobots(RobotsRo robotsRo) {
        baseRedisService.setString(serverName + BaseConstant.CONNECTOR_UNDERLINE + RobotsConstant.ROBOTS_KEY, robotsRo);
    }

    @Override
    public void buildRobotsTxt() {
        try {
            String rootPath = ModuleUtil.getModuleRootPath();
            Path rootLocation = Paths.get(rootPath);
            //data.js是文件
            String robotsFilePath = rootPath + "\\src\\main\\resources\\" + RobotsConstant.ROBOTS_NAME_ALL;
            File file = new File(robotsFilePath);
            if (file.exists()) {
                file.delete();
            }
            Path path2 = rootLocation.resolve(robotsFilePath);
            String robotsFile = robots2String();
            byte[] strToBytes = robotsFile.getBytes();
            try {
                Files.write(path2, strToBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Robots> getRobots() {
        Object obj = baseRedisService.getByKey(serverName + BaseConstant.CONNECTOR_UNDERLINE + RobotsConstant.ROBOTS_KEY);
        RobotsRo robotsRo = JSON.parseObject(obj.toString(), new TypeReference<RobotsRo>() {
        });
        return robotsRo.getRobots();
    }

    /**
     * 从redis获得robots 转化成写入txt的内容
     *
     * @throws
     * @return: com.lvmoney.robots.ro.RobotsRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/10/29 17:03
     */
    private String robots2String() {
        StringBuilder sb = new StringBuilder();
        List<Robots> robotsList = getRobots();
        robotsList.stream().forEach(e -> {
            sb.append(RobotsConstant.USER_AGENT).append(e.getUserAgent()).append(LINE_SEPARATOR);
            e.getDisallow().stream().forEach(e1 -> {
                        sb.append(RobotsConstant.DISALLOW).append(e1.getDisallow()).append(LINE_SEPARATOR);
                    }
            );
        });
        return sb.toString();
    }
}
