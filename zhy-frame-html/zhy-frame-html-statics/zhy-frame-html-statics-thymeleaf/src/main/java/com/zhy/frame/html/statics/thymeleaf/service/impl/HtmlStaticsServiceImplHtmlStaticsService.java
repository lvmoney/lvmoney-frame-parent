package com.zhy.frame.html.statics.thymeleaf.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.html.statics.thymeleaf.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/22
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.html.statics.common.service.impl.BaseHtmlStaticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/22 10:02
 */
@Service
public class HtmlStaticsServiceImplHtmlStaticsService extends BaseHtmlStaticsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlStaticsServiceImplHtmlStaticsService.class);

    /**
     * 这是thymeleaf模板处理引擎
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Override
    public void buildHtmlPage(ModelAndView modelAndView) {
        if (modelAndView == null || modelAndView.getModel().isEmpty()) {
            return;
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        FileWriter fileWriter = null;
        try {
            String fileName = super.getFileName(request);
            File file = new File(fileName);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fileWriter = new FileWriter(file);
            WebContext ctx = new WebContext(request, response, request.getServletContext(), request.getLocale(), modelAndView.getModelMap());
            //将thymeleaf模板生成的结果存入静态文件中
            templateEngine.process(modelAndView.getViewName(), ctx, fileWriter);
            LOGGER.info("已生成静态文件：" + fileName);
        } catch (Exception e) {
            LOGGER.error("html静态化失败:{}", e.getMessage());
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
