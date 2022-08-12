package com.lvmoney.frame.newsql.clickhouse.sink.sink;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.sink
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */


import com.google.common.base.Preconditions;
import com.lvmoney.frame.newsql.clickhouse.sink.util.JsonUtil;
import com.lvmoney.frame.newsql.clickhouse.sink.util.StringUtil;
import com.lvmoney.frame.newsql.clickhouse.sink.vo.LogbackBodyVo;
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
import java.util.ArrayList;
import java.util.List;

import static com.lvmoney.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 15:59
 */
public class ClickHouseLogbackSink extends AbstractSink implements Configurable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClickHouseLogbackSink.class);
    private BalancedClickhouseDataSource dataSource = null;
    private SinkCounter sinkCounter = null;
    private String host = null;
    private String port = null;
    private String user = null;
    private String password = null;
    private String database = null;
    private String table = null;
    private int batchSize;

    @Override
    public Status process() throws EventDeliveryException {
        Status status = null;
        Channel ch = getChannel();
        Transaction txn = ch.getTransaction();
        txn.begin();
        List<LogbackBodyVo> insertData = new ArrayList<>();
        try {
            ClickHouseConnectionImpl conn = (ClickHouseConnectionImpl) dataSource.getConnection();
            int count;
            for (count = 0; count < batchSize; ++count) {
                Event event = ch.take();
                if (event == null) {
                    break;
                }
                insertData.add(StringUtil.buildLog(new String(event.getBody())));
            }
            if (count <= 0) {
                sinkCounter.incrementBatchEmptyCount();
                txn.commit();
                return Status.BACKOFF;
            } else if (count < batchSize) {
                sinkCounter.incrementBatchUnderflowCount();
            } else {
                sinkCounter.incrementBatchCompleteCount();
            }
            sinkCounter.addToEventDrainAttemptCount(count);
            ClickHouseStatement sth = conn.createStatement();
            sth.write().table(String.format(" %s.%s", database, table)).data(new ByteArrayInputStream(JsonUtil.t2JsonString(insertData).getBytes()), ClickHouseFormat.JSONEachRow).addDbParam(ClickHouseQueryParam.MAX_PARALLEL_REPLICAS, MAX_PARALLEL_REPLICAS_VALUE).send();
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
        super.stop();
        LOGGER.info("clickHouse sink {} stopped", getName());
    }
}
