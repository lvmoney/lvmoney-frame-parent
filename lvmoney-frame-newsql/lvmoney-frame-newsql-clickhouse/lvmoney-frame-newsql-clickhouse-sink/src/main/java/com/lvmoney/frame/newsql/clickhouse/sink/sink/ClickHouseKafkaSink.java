package com.lvmoney.frame.newsql.clickhouse.sink.sink;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.sink
 * 版本信息: 版本1.0
 * 日期:2020/7/17
 * Copyright XXXXXX科技有限公司
 */


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.base.Preconditions;
import com.lvmoney.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant;
import com.lvmoney.frame.newsql.clickhouse.sink.util.JsonUtil;
import com.lvmoney.frame.newsql.clickhouse.sink.util.SnowflakeIdFactoryUtil;
import com.lvmoney.frame.newsql.clickhouse.sink.util.StringUtil;
import org.apache.flume.*;
import org.apache.flume.conf.Configurable;
import org.apache.flume.instrumentation.SinkCounter;
import org.apache.flume.sink.AbstractSink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.ClickHouseStatement;
import ru.yandex.clickhouse.domain.ClickHouseFormat;
import ru.yandex.clickhouse.settings.ClickHouseProperties;
import ru.yandex.clickhouse.settings.ClickHouseQueryParam;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.lvmoney.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/17 17:04
 */
public class ClickHouseKafkaSink extends AbstractSink implements Configurable {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClickHouseKafkaSink.class);
    private BalancedClickhouseDataSource dataSource = null;
    private SinkCounter sinkCounter = null;
    private String host = null;
    private String port = null;
    private String user = null;
    private String password = null;
    private String database = null;
    private String table = null;
    private int batchSize;
    private final DateTimeFormatter df = DateTimeFormatter.ofPattern(ClickHouseSinkConstant.DATE_FORMAT);

    private int intervalDate;
    Cache<String, Object> cache = Caffeine.newBuilder()
            .maximumSize(DEFAULT_CACHE_MAX_SIZE)
            .build();


    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        // 声明事件
        Event event;
        txn.begin();
        try {
            handData();
            int count;
            for (count = 0; count < batchSize; ++count) {
                event = ch.take();
                if (event == null) {
                    break;
                }
                Long num = SnowflakeIdFactoryUtil.nextId();
                String data = new String(event.getBody());
                cache.put(num.toString(), data);
                LOGGER.info(data);
            }
            if (count < batchSize) {
                sinkCounter.incrementBatchUnderflowCount();
            } else {
                sinkCounter.incrementBatchCompleteCount();
            }
            sinkCounter.addToEventDrainAttemptCount(count);
            handData();
            sinkCounter.incrementEventDrainSuccessCount();
            status = Status.READY;
            txn.commit();

        } catch (Throwable t) {
            txn.rollback();
            LOGGER.error(t.getMessage(), t);
            status = Status.BACKOFF;
            // re-throw all Errors
            if (t instanceof Error) {
                throw (Error) t;
            }
        } finally {
            txn.close();
        }
        return status;
    }

    @Override
    public void configure(Context context) {
        if (sinkCounter == null) {
            sinkCounter = new SinkCounter(getName());
        }
        this.intervalDate = context.getInteger(INTERVAL_DATE, DEFAULT_INTERVAL_DATE);
        Preconditions.checkArgument(context.getString(HOST) != null && context.getString(HOST).length() > 0, "ClickHouse host must be specified!");
        this.host = context.getString(HOST);
        if (!this.host.startsWith(CLICK_HOUSE_PREFIX)) {
            this.host = CLICK_HOUSE_PREFIX + this.host;
        }

        Preconditions.checkArgument(context.getString(DATABASE) != null && context.getString(DATABASE).length() > 0, "ClickHouse database must be specified!");
        this.database = context.getString(DATABASE);
        Preconditions.checkArgument(context.getString(TABLE) != null && context.getString(TABLE).length() > 0, "ClickHouse table must be specified!");
        this.table = context.getString(TABLE);
        this.port = context.getString(PORT, DEFAULT_PORT);
        this.user = context.getString(USER, DEFAULT_USER);
        this.password = context.getString(PASSWORD, DEFAULT_PASSWORD);
        this.batchSize = context.getInteger(BATCH_SIZE, DEFAULT_BATCH_MAX_SIZE);
    }


    @Override
    public void start() {
        LOGGER.info("clickHouse sink {} starting", getName());
        String jdbcUrl = String.format("%s:%s/%s", this.host, this.port, this.database);
        ClickHouseProperties properties = new ClickHouseProperties().withCredentials(this.user, this.password);
        this.dataSource = new BalancedClickhouseDataSource(jdbcUrl, properties);
        sinkCounter.start();
        super.start();
        LOGGER.info("clickHouse sink {} started", getName());
    }


    @Override
    public void stop() {
        LOGGER.info("clickHouse sink {} stopping", getName());
        sinkCounter.incrementConnectionClosedCount();
        sinkCounter.stop();
        data2Db();
        super.stop();
        LOGGER.info("clickHouse sink {} stopped", getName());
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
    private void handData() {
        if (cache.asMap().size() >= batchSize) {
            data2Db();
        } else {
            String lastDate = (String) cache.get(DEFAULT_CACHE_DATE_KAFKA, k -> LocalDateTime.now().format(df));
            String now = LocalDateTime.now().format(df);
            Long interval = ChronoUnit.MILLIS.between(
                    LocalDateTime.parse(lastDate, df).atZone(
                            ZoneId.of(DEFAULT_ZONE_ID)).toInstant(),
                    LocalDateTime.parse(now, df).atZone(
                            ZoneId.of(DEFAULT_ZONE_ID)).toInstant());
            if (interval >= intervalDate) {
                data2Db();
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
    private void data2Db() {
        List<Map> insertData = new ArrayList<>();
        cache.asMap().forEach((k, v) -> {
            if (!k.equals(DEFAULT_CACHE_DATE_KAFKA)) {
                insertData.add(StringUtil.buildKafka(JsonUtil.t2JsonString(v)));
            }
        });
        if (insertData == null || insertData.size() == 0) {
            return;
        }
        ClickHouseConnectionImpl conn = null;
        try {
            conn = (ClickHouseConnectionImpl) dataSource.getConnection();
            ClickHouseStatement sth = conn.createStatement();
            LOGGER.info("data:=" + JsonUtil.t2JsonString(insertData));
            sth.write().table(String.format(" %s.%s", database, table)).data(new ByteArrayInputStream(JsonUtil.t2JsonString(insertData).getBytes()), ClickHouseFormat.JSONEachRow).addDbParam(ClickHouseQueryParam.MAX_PARALLEL_REPLICAS, MAX_PARALLEL_REPLICAS_VALUE).send();
        } catch (SQLException e) {
            LOGGER.error("同步数据到clickhouse报错:{}", e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                LOGGER.error("关闭clickhouse Connection报错:{}", e);
            }
        }

        cache.put(DEFAULT_CACHE_DATE_KAFKA, LocalDateTime.now().format(df));
        cache.invalidateAll();
    }

}
