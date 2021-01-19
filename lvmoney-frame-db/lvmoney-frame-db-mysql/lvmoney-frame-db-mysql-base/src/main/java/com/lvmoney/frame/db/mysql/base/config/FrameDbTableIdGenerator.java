package com.lvmoney.frame.db.mysql.base.config;/**
 * 描述:
 * 包名:com.lvmoney.mysql.base.handler
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.lvmoney.frame.db.mysql.base.service.UidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @describe：自定义主键生成策略，主键类型 type = IdType.ASSIGN_ID
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 11:26
 */
@Component
public class FrameDbTableIdGenerator extends DefaultIdentifierGenerator {
    @Autowired
    UidService uidService;

    @Override
    public Long nextId(Object entity) {
        //使用百度提供的主键生成工具
        long id = uidService.getUid();
        //返回生成的id值即可.
        return id;
    }
}
