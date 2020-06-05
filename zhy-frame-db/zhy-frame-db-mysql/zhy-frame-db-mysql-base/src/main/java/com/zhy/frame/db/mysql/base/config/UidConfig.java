package com.zhy.frame.db.mysql.base.config;/**
 * 描述:
 * 包名:com.zhy.platform.member.info.handler
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 16:52
 */
@Configuration
@ImportResource(locations = {"classpath:uid/cached-uid-spring.xml"})
public class UidConfig {
}
