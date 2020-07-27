package com.zhy.frame.demo.clickhouse.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.demo.clickhouse.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */

import com.zhy.frame.demo.clickhouse.dao.TestTableMapper;
import com.zhy.frame.demo.clickhouse.entity.TestTableEntity;
import com.zhy.frame.demo.clickhouse.service.TestTableService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:31
 */
@Service
public class TestTableServiceImpl extends ServiceImpl<TestTableMapper, TestTableEntity> implements TestTableService {
}
