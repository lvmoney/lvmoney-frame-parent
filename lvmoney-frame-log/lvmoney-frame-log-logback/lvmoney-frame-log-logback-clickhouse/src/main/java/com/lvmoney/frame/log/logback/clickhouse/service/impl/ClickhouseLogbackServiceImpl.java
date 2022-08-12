package com.lvmoney.frame.log.logback.clickhouse.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.elasticsearch.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.log.logback.clickhouse.dto.item.SysLogDtoItem;
import com.lvmoney.frame.log.logback.common.prop.LogbackProp;
import com.lvmoney.frame.newsql.clickhouse.base.prop.DataSourceProp;
import com.lvmoney.frame.newsql.clickhouse.base.service.ClickhouseBaseService;
import com.lvmoney.frame.newsql.clickhouse.base.vo.BaseVo;
import com.github.dozermapper.core.Mapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.core.util.DateUtil;
import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
import com.lvmoney.frame.log.logback.common.service.LogbackService;
import com.lvmoney.frame.log.logback.common.vo.LogVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.lvmoney.frame.log.logback.clickhouse.constant.ClickhouseConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 17:51
 */
@Service
public class ClickhouseLogbackServiceImpl implements LogbackService {
    @Getter
    @Autowired
    LogbackProp logbackProp;

    @Autowired
    DataSourceProp dataSourceProp;
    @Autowired
    ClickhouseBaseService clickhouseBaseService;
    Cache<String, Object> cache = Caffeine.newBuilder()
            .maximumSize(DEFAULT_CACHE_MAX_SIZE)
            .build();
    @Autowired
    Mapper mapper;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern(BaseConstant.COMMON_DATE_FORMAT);
    @Value("${frame.logback.clickhouse.batchSize:100000}")
    private int batchSize;
    @Value("${frame.logback.clickhouse.intervalDate:8000}")
    private int intervalDate;
    @Value("${spring.application.name:lvmoney}")
    private String systemName;

    @Value("${frame.logback.clickhouse.tableName:sys_log}")
    private String tableName;

    @Override
    public void saveLog(LogVo logVo) {
        Long num = SnowflakeIdFactoryUtil.nextId();
        logVo.setId(num.toString());
        cache.put(num.toString(), logVo);
        handData(logVo);
    }

    @Override
    public String getSystemName() {
        return systemName;
    }

    /**
     * 处理数据的逻辑处理
     * 1：当缓存的size大于 配置的batchSize，同步缓存数据
     * 2：最新更新时间比现在超过DEFAULT_INTERVAL_DATE（默认8秒），同步缓存数据
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/22 9:02
     */
    private void handData(LogVo logVo) {
        if (cache.asMap().size() >= batchSize) {
            data2Db(logVo);
        } else {
            String lastDate = (String) cache.get(DEFAULT_CACHE_DATE_KAFKA, k -> LocalDateTime.now().format(df));
            String now = LocalDateTime.now().format(df);
            Long interval = ChronoUnit.MILLIS.between(
                    LocalDateTime.parse(lastDate, df).atZone(
                            ZoneId.of(DEFAULT_ZONE_ID)).toInstant(),
                    LocalDateTime.parse(now, df).atZone(
                            ZoneId.of(DEFAULT_ZONE_ID)).toInstant());
            if (interval >= intervalDate) {
                data2Db(logVo);
            }
        }
    }

    /**
     * 同步数据并且更新同步时间
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/22 9:04
     */
    private void data2Db(LogVo logVo) {
        List<LogVo> insertData = new ArrayList<>();
        cache.asMap().forEach((k, v) -> {
            if (!k.equals(DEFAULT_CACHE_DATE_KAFKA)) {
                insertData.add(logVo);
            }
        });
        if (insertData == null || insertData.size() == 0) {
            return;
        }
        List<SysLogDtoItem> result = new ArrayList<>();
        LocalDateTime localDateTime = DateUtil.localDateTimeFormatter(LocalDateTime.now(), BaseConstant.COMMON_DATE_FORMAT);
        insertData.stream().forEach(e -> {
            SysLogDtoItem sysLogDtoItem = mapper.map(e, SysLogDtoItem.class);
            sysLogDtoItem.setCreateDate(localDateTime);
            result.add(sysLogDtoItem);
        });
        BaseVo baseVo = new BaseVo();
        baseVo.setTableName(tableName);
        baseVo.setDatabase(dataSourceProp.getDatabase());
        baseVo.setData(result);
        clickhouseBaseService.batchSave(baseVo);
        // sysLogService.saveBatch(result);
        cache.put(DEFAULT_CACHE_DATE_KAFKA, LocalDateTime.now().format(df));
        cache.invalidateAll();
    }

}
