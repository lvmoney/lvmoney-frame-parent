package com.lvmoney.demo.uniformity.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.controller
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.sync.uniformity.db.ro.ShardingLogicRo;
import com.lvmoney.frame.sync.uniformity.db.service.UniformityRedisService;
import com.lvmoney.frame.sync.uniformity.unique.bo.UniqueCodeGetBo;
import com.lvmoney.frame.sync.uniformity.unique.dto.UniqueCodeGetDto;
import com.lvmoney.frame.sync.uniformity.unique.service.UniqueCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 13:35
 */
@RestController
public class UniqueController {
    @Autowired
    UniqueCodeService uniqueCodeService;
    @Autowired
    UniformityRedisService uniformityRedisService;

    @PostMapping(value = "frame/uniformity/test")
    public ApiResult<String> testJwt(@RequestBody UniqueCodeGetDto uniqueCodeGetDto) {
        List<UniqueCodeGetBo> byKey = uniqueCodeService.getByKey(uniqueCodeGetDto);
        return ApiResult.success(JsonUtil.t2JsonString(byKey));
    }

    @PostMapping(value = "frame/uniformity/db")
    public ApiResult<String> test() {
        List<ShardingLogicRo>   shardingLogicRoList = new ArrayList<>();
        uniformityRedisService.uniformityConfig2Redis(shardingLogicRoList);
        return null;
    }
}
