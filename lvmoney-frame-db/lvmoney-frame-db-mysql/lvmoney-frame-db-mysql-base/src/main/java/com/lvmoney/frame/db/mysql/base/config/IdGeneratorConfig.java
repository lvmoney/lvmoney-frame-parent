package com.lvmoney.frame.db.mysql.base.config;/**
 * 描述:
 * 包名:com.lvmoney.db.mysql.common.config
 * 版本信息: 版本1.0
 * 日期:2020/10/21
 * Copyright XXXXXX科技有限公司
 */


import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0
 * 2020/10/21 15:30
 */
@Configuration
public class IdGeneratorConfig {

    /**
     * 需要显式生成ID时使用
     *
     * @return: com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator
     * @author: yangbo / XXXXXX科技有限公司
     * @date: 2020/10/12 18:10
     */
    @Bean
    public IdentifierGenerator identifierGenerator() {
        return new DefaultIdentifierGenerator();
    }

}
