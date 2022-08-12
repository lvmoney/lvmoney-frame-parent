package com.lvmoney.frame.db.sharding.util.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.controller
 * 版本信息: 版本1.0
 * 日期:2020/10/27
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.db.sharding.util.bo.GetDbTenantBo;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTableDto;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTenantDto;
import com.lvmoney.frame.db.sharding.util.ro.DbTableRelRo;
import com.lvmoney.frame.db.sharding.util.ro.item.DbTableRelRoItem;
import com.lvmoney.frame.db.sharding.util.service.TenantService;
import com.lvmoney.frame.db.sharding.util.vo.req.DbReqVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/27 16:13
 */
//@RestController
public class TenantController {
    @Autowired
    TenantService tenantService;

    @GetMapping("/db/table/save")
    public void dbTableSave() {
        DbTableRelRo dbTableRelRo = new DbTableRelRo();
        Map<String, List<DbTableRelRoItem>> data = new HashMap<>();
        DbTableRelRoItem dbTableRelRoItem = new DbTableRelRoItem();
        dbTableRelRoItem.setDb("user_cd_1");
        String classify = "chengdu";
        dbTableRelRoItem.setTable(new ArrayList() {{
            add("toca_1");
            add("toca_2");
            add("toca_3");
        }});
        data.put(classify + "_user", new ArrayList() {{
            add(dbTableRelRoItem);
        }});
        dbTableRelRo.setData(data);
        tenantService.dbTableRel2Redis(dbTableRelRo);
    }

    @GetMapping("/db/table/get")
    public ApiResult<List<DbTableRelRoItem>> getTableRel() {
        GetDbTableDto getDbTableDto = new GetDbTableDto();
        getDbTableDto.setGroup("user");
        getDbTableDto.setClassify("chengdu");
        List<DbTableRelRoItem> dbTableRelRoItems = tenantService.getDbTableByRedis(getDbTableDto);
        return ApiResult.success(dbTableRelRoItems);
    }


    @GetMapping("/db/Tenant/get")
    public ApiResult<GetDbTenantBo> getDbTenant(GetDbTenantDto getDbTenantDto) {
        return ApiResult.success(tenantService.getDbTenant(getDbTenantDto));
    }

    @GetMapping("/db/Tenant/group/get")
    public ApiResult<GetDbTenantBo> getDbTenant(String group) {
        return ApiResult.success(tenantService.getDbTenantByKey(group));
    }
}
