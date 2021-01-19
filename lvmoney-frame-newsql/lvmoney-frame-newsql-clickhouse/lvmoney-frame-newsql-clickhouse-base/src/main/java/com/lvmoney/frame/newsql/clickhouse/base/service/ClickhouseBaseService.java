package com.lvmoney.frame.newsql.clickhouse.base.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.base.service
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.newsql.clickhouse.base.vo.BaseVo;

/**
 * @describe：
 * @author: lvmoney/成都三合力通科技有限公司
 * @version:v1.0 2020/10/16 16:33
 */
public interface ClickhouseBaseService {
    /**
     * 根据数据库名和表名称批量保存数据
     *
     * @param baseVo:
     * @throws
     * @return: void
     * @author: lvmoney /成都三合力通科技有限公司
     * @date: 2020/10/16 16:38
     */

    void batchSave(BaseVo baseVo);
}
