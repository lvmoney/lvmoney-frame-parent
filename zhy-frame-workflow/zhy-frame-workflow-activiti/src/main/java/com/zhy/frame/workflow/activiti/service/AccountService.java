package com.zhy.frame.workflow.activiti.service;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/24
 * Copyright xxxx科技有限公司
 */

/**
 * @describe：维护activiti的用户和权限 和自己设计的表数据做关联和数据同步
 * 1、通过接口完成,该接口来完成
 * ACT_ID_GROUP
 * ACT_ID_INFO
 * ACT_ID_MEMBERSHIP
 * ACT_ID_USER
 * 2、定义activiti表的视图，通过操作视图来完成
 * 2.1.删除已创建的ACT_ID_*表
 * 创建视图必须删除引擎自动创建的ACT_ID_*表，否则不能创建视图。
 * <p>
 * 2.2.创建视图：
 * ACT_ID_GROUP
 * ACT_ID_INFO
 * ACT_ID_MEMBERSHIP
 * ACT_ID_USER
 * 创建的视图要保证数据类型一致，
 * 例如用户的ACT_ID_MEMBERSHIP表的两个字段都是字符型，
 * 一般系统中都是用NUMBER作为用户、角色的主键类型，所以创建视图的时候要把数字类型转换为字符型。
 * 2.3.修改引擎默认配置
 * 在引擎配置中设置属性dbIdentityUsed为false即可。
 * <p>
 * <beanidbeanid="processEngineConfiguration"class="org.activiti.spring.SpringProcessEngineConfiguration">
 * ...
 * <propertynamepropertyname="dbIdentityUsed"ref="false">
 * ...
 * </property></bean>
 * <beanid="processEngineConfiguration"class="org.activiti.spring.SpringProcessEngineConfiguration">
 * ...
 * <propertyname="dbIdentityUsed"ref="false">
 * ...
 * </property></bean>
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */

public interface AccountService {

}
