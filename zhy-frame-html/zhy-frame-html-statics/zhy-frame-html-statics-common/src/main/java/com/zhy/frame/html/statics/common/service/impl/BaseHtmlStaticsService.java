package com.zhy.frame.html.statics.common.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.html.statics.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.html.statics.common.constant.StaticsConstant;
import com.zhy.frame.html.statics.common.ro.HtmlStaticsRo;
import com.zhy.frame.html.statics.common.service.HtmlStaticsService;
import com.zhy.frame.html.statics.common.vo.HandHtmlStaticsVo;
import com.zhy.frame.html.statics.common.vo.HtmlStaticsVo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 9:39
 */
public abstract class BaseHtmlStaticsService implements HtmlStaticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseHtmlStaticsService.class);
    @Autowired
    BaseRedisService baseRedisService;
    /**
     * 这是Spring容器上下文
     */
    @Autowired
    private ApplicationContext appContext;
    @Value("${frame.html.statics.path:/home}")
    private String htmlPath;

    @Value("${spring.application.name:lvmoney}")
    private String serverName;

    /**
     * 生成静态页面
     *
     * @param modelAndView:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 10:23
     */
    @Override
    public abstract void buildHtmlPage(ModelAndView modelAndView);

    @Override
    public void deleteHtmlPage(String fileName) {
        try {
            fileName = htmlPath + fileName;
            File file = new File(fileName);
            //删除文件或目录
            FileUtils.deleteQuietly(file);
            LOGGER.info("已删除静态文件：" + fileName);
        } catch (Exception e) {
            LOGGER.error("删除静态文件报错：{}", e);
        }
    }

    @Override
    public void saveHtmlStatics(HtmlStaticsRo htmlStaticsRo) {
        baseRedisService.addMap(serverName + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY, htmlStaticsRo.getData(), htmlStaticsRo.getExpired());
    }

    @Override
    public void deleteByUrl(HandHtmlStaticsVo handHtmlStaticsVo) {
        baseRedisService.deleteByMapKey(handHtmlStaticsVo.getServerName() + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY, handHtmlStaticsVo.getRequestUrl());
    }

    @Override
    public HtmlStaticsVo getByUrl(HandHtmlStaticsVo handHtmlStaticsVo) {

        try {
            Object obj = baseRedisService.getByMapKey(handHtmlStaticsVo.getServerName() + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY, handHtmlStaticsVo.getRequestUrl());
            HtmlStaticsVo htmlStaticsVo = JSON.parseObject(obj.toString(), new TypeReference<HtmlStaticsVo>() {
            });
            return htmlStaticsVo;
        } catch (Exception e) {
            LOGGER.error("获得指定服务请求地址的页面静态化数据报错:{}", e);
            return null;
        }
    }

    @Override
    public Map getByServerName(String serverName) {
        try {
            Object obj = baseRedisService.getByKey(serverName + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY);
            Map<String, HtmlStaticsVo> result = JSON.parseObject(obj.toString(), new TypeReference<Map<String, HtmlStaticsVo>>() {
            });
            return result;
        } catch (Exception e) {
            LOGGER.error("获得指定服务的页面静态化数据报错:{}", e);
            return null;
        }
    }

    @Override
    public void deleteByServerName(String serverName) {
        baseRedisService.deleteByKey(serverName + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY);
    }

    @Override
    public List<Map> getAll() {
        List<Map> result = new ArrayList<Map>();
        Set<String> keys = baseRedisService.getKeysByWildcard("*" + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY);
        keys.stream().forEach(e -> {
            Map map = this.getByServerName(e);
            result.add(map);
        });
        return result;
    }

    @Override
    public void deleteAll() {
        baseRedisService.deleteByWildcardKey("*" + BaseConstant.CONNECTOR_UNDERLINE + StaticsConstant.HTML_STATICS_REDIS_KEY);
    }

    public String getFileName(HttpServletRequest request) {
        String url = request.getServletPath().substring(1);
        if (url.endsWith(StaticsConstant.HTML_END_WITH)) {
            url = url.replace(StaticsConstant.HTML_END_WITH, "");
        }
        StringBuilder fileName = new StringBuilder();
        String[] uris = url.split(BaseConstant.BACKSLASH);
        Arrays.stream(uris).forEach(e -> {
            if (e.startsWith(BaseConstant.BRACE_LEFT) && e.endsWith(BaseConstant.BRACE_RIGHT)) {
                String temp = e.substring(1, e.length() - 1);
                String uri = request.getParameter(temp);
                fileName.append(BaseConstant.BACKSLASH);
                fileName.append(uri);
            } else {
                fileName.append(BaseConstant.BACKSLASH);
                fileName.append(e);
            }

        });
        String temp = fileName.toString();
        if (temp.startsWith(BaseConstant.BACKSLASH)) {
            temp = temp.substring(1);
        }
        String result = htmlPath + BaseConstant.FILE_SEPARATOR + temp + StaticsConstant.HTML_END_WITH;
        return result;
    }
}
