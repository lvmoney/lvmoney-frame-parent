package com.zhy.frame.html.statics.beetl.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.html.statics.beetl.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/22
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.SpringBeanUtil;
import com.zhy.frame.html.statics.common.constant.StaticsConstant;
import com.zhy.frame.html.statics.common.service.impl.BaseHtmlStaticsService;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/22 13:10
 */
@Service
public class HtmlStaticsServiceImplHtmlStaticsService extends BaseHtmlStaticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlStaticsServiceImplHtmlStaticsService.class);

    @Override
    public void buildHtmlPage(ModelAndView modelAndView) {
        if (modelAndView == null || modelAndView.getModel().isEmpty()) {
            return;
        }
        HttpServletRequest request = SpringBeanUtil.getHttpServletRequest();
        FileWriter fileWriter = null;
        try {
            String fileName = super.getFileName(request);
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null) {
                classLoader = this.getClass().getClassLoader();
            }
            ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader(classLoader,
                    StaticsConstant.HTML_ROOT_PATH);
            Configuration config = Configuration.defaultConfiguration();
            //Beetl的核心GroupTemplate
            GroupTemplate groupTemplate = new GroupTemplate(classpathResourceLoader, config);
            Template template = groupTemplate.getTemplate(modelAndView.getViewName() + StaticsConstant.HTML_END_WITH);
            ModelMap modelMap = modelAndView.getModelMap();
            for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
                template.binding(entry.getKey(), entry.getValue());
            }
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileWriter = new FileWriter(fileName);
            template.renderTo(fileWriter);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (Exception e2) {
                    LOGGER.error("html静态化关闭fileWriter报错:{}", e2.getMessage());
                }
            }
        }
    }
}
