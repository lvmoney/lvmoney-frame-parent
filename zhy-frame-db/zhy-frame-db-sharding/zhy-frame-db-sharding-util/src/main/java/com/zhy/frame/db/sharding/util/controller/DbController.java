package com.zhy.frame.db.sharding.util.controller;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.function
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.Mapper;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.db.sharding.util.service.DbService;
import com.zhy.frame.db.sharding.util.vo.req.BatchTableReqVo;
import com.zhy.frame.db.sharding.util.vo.req.DbReqVo;
import com.zhy.frame.db.sharding.util.vo.req.TableReqVo;
import com.zhy.frame.db.sharding.util.vo.resp.BatchTableRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:10
 */
@RestController
public class DbController {
    @Autowired
    DbService dbService;

    @Autowired
    Mapper dozerMapper;

    /**
     * 从数据库中获得数据库创建sql
     *
     * @param dbReqVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/23 11:24
     */
    @GetMapping("/db/sql")
    public String dbSql(DbReqVo dbReqVo) {
        boolean isSeq = dbReqVo.isSeq();
        StringBuilder sql = new StringBuilder();
        if (!isSeq) {
            sql.append(dbService.showDbSql(dbReqVo));
            return sql.toString();
        }
        return sql.toString();
    }

    /**
     * 从指定的数据库获得指定表的创建sql
     *
     * @param tableReqVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/23 11:25
     */
    @GetMapping("/table/sql")
    public String tableSql(TableReqVo tableReqVo) {
        boolean isSeq = tableReqVo.isSeq();
        StringBuilder sql = new StringBuilder();
        if (!isSeq) {
            sql.append(dbService.showTableSql(tableReqVo));
            return sql.toString();
        }
        return sql.toString();
    }

    /**
     * 批量创建表
     *
     * @param batchTableReqVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/23 11:25
     */
    @GetMapping("/table/create/batch")
    public ApiResult<List<BatchTableRespVo>> batchCreateTable(BatchTableReqVo batchTableReqVo) {
        List<BatchTableRespVo> respVoList = dbService.createTable(batchTableReqVo);
        return ApiResult.success(respVoList);
    }


}
