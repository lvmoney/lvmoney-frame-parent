package com.lvmoney.frame.db.mysql.base.config;/**
 * 描述:
 * 包名:com.lvmoney.common.handler
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 成都三合力通科技有限公司
 */


import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantSqlParser;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.base.core.util.SupportUtil;
import com.lvmoney.frame.db.mysql.base.exception.MysqlException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Configuration
@MapperScan("com.lvmoney.**.dao*")
public class MysqlConfig {
    /**
     * 租户id
     */
    @Value("${frame.saas.tenant.filed:tenant_id}")
    private String tenantId;
    /**
     * 多租户支持
     */
    @Value("${frame.saas.support:false}")
    private boolean saasSupport;


    @Value("${frame.saas.filterTable:role}")
    private String filterTable;


    /**
     * @describe: 分页插件
     * @param: []
     * @return: com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     * @author： lvmoney /四川******科技有限公司
     * 2019/2/20 9:29
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        if (!SupportUtil.support(saasSupport)) {
            throw new BusinessException(MysqlException.Proxy.SAAS_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE_BOOL == saasSupport) {
            return paginationInterceptor;
        }
        ArrayList<ISqlParser> sqlParsers = new ArrayList<>();
        // 租户解析器
        TenantSqlParser tenantSqlParser = new TenantSqlParser();

        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId(boolean where) {
                String token = SpringBeanUtil.getToken();
                return new StringValue(JwtUtil.getUserVo(JwtUtil.getRealToken(token)).getTenantId());
            }

            // 多租户字段是什么
            @Override
            public String getTenantIdColumn() {
                // 这是表中的字段名,即 用于区分租户的字段
                return tenantId;
            }

            // 是否需要排除某些表，不加租户字段
            @Override
            public boolean doTableFilter(String tableName) {
                if (Arrays.asList(filterTable.split(BaseConstant.CHARACTER_COMMA)).contains(tableName)) {
                    return true;
                }
                return false;
            }
        });
        sqlParsers.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParsers);
        return paginationInterceptor;
    }

}
