package com.lvmoney.frame.demo.clickhouse.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.demo.clickhouse.controller
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.core.util.DateUtil;
import com.lvmoney.frame.log.logback.clickhouse.dto.SysLogDto;
import com.lvmoney.frame.log.logback.clickhouse.dto.item.SysLogDtoItem;
import com.lvmoney.frame.newsql.clickhouse.base.prop.DataSourceProp;
import com.lvmoney.frame.newsql.clickhouse.base.service.ClickhouseBaseService;
import com.lvmoney.frame.newsql.clickhouse.base.vo.BaseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 16:33
 */
@RestController
public class DemoClickhouseController {
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern(BaseConstant.API_RESULT_DATA_DATE_FORMAT);

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoClickhouseController.class);
    @Autowired
    ClickhouseBaseService clickhouseBaseService;
    @Autowired
    DataSourceProp dataSourceProp;
    @Value("${frame.logback.clickhouse.tableName:sys_log}")
    private String tableName;
    @GetMapping("/save")
    public void save() {
        List<SysLogDtoItem> result = new ArrayList<>();
        SysLogDtoItem sysLogDtoItem = new SysLogDtoItem();
        LocalDateTime time = LocalDateTime.now();
        sysLogDtoItem.setCreateDate(DateUtil.localDateTimeFormatter(time, BaseConstant.COMMON_DATE_FORMAT));
        sysLogDtoItem.setExeDate(DateUtil.localDateTimeFormatter(time, BaseConstant.COMMON_DATE_FORMAT));
        sysLogDtoItem.setId("dfa");
        sysLogDtoItem.setLevel("test");
        sysLogDtoItem.setMsg("test");
        sysLogDtoItem.setSysName("test");
        sysLogDtoItem.setThread("test");
        result.add(sysLogDtoItem);
        SysLogDto sysLogDto = new SysLogDto();
        sysLogDto.setTableName("sys_log");
        sysLogDto.setSysLogDtoItems(new ArrayList() {{
            add(sysLogDtoItem);
        }});
        BaseVo baseVo = new BaseVo();
        baseVo.setTableName(tableName);
        baseVo.setDatabase(dataSourceProp.getDatabase());
        baseVo.setData(result);
        clickhouseBaseService.batchSave(baseVo);
//        sysLogService.saveBatch(result);
//        testTableService.save(sysLogDtoItem);
        LOGGER.info("test");
    }
}
