package com.lvmoney.demo.uniformity.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.uniformity.controller
 * 版本信息: 版本1.0
 * 日期:2021/12/31
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.sync.uniformity.db.dto.GetSharingListDto;
import com.lvmoney.frame.sync.uniformity.db.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/31 16:06
 */
@RestController
public class DbController {
    @Autowired
    CommonService commonService;

    @PostMapping(value = "frame/uniformity/count")
    public ApiResult<Long> test(@RequestBody GetSharingListDto getSharingListDto) {

        long count = commonService.getShardingCount(getSharingListDto);
        return ApiResult.success(count);
    }


    @PostMapping(value = "frame/uniformity/list")
    public ApiResult<List> list(@RequestBody GetSharingListDto getSharingListDto) {

        List<Map> list = commonService.getShardingList(getSharingListDto);
        return ApiResult.success(list);
    }
}
