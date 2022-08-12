package com.lvmoney.frame.log.logback.elasticsearch.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.elasticsearch.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.log.logback.common.prop.LogbackProp;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
import com.lvmoney.frame.log.logback.common.service.LogbackService;
import com.lvmoney.frame.log.logback.common.vo.LogVo;
import com.lvmoney.frame.log.logback.elasticsearch.eo.LogEo;
import com.lvmoney.frame.retrieval.elasticsearch.service.ElasticsearchService;
import com.lvmoney.frame.retrieval.elasticsearch.vo.ElasticsearchSaveVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 17:51
 */
@Service
public class EsLogbackServiceImpl implements LogbackService {
    @Getter
    @Autowired
    LogbackProp logbackProp;
    @Autowired
    private ElasticsearchService searchService;
    private static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();
    @Value("${spring.application.name:lvmoney}")
    private String systemName;

    @Override
    public void saveLog(LogVo logVo) {
        ElasticsearchSaveVo elasticsearchSaveVo = new ElasticsearchSaveVo();
        LogEo logEo = MAPPER.map(logVo, LogEo.class);
        Long num = SnowflakeIdFactoryUtil.nextId();
        logEo.setId(num.toString());
        searchService.save(elasticsearchSaveVo);
    }

    @Override
    public String getSystemName() {
        return systemName;
    }


}
