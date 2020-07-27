1、我们业务系统的系统日志会在日志目录生成日志，并通过flume同步到clickhouse  
2、通过flume同步kafka的里面数据到clickhouse  
3、flume同步kakfa的数据，为了达到批量保存到clickhouse中的目的，先把flume同步的数据
保存到cache（caffeine）中，等数据达到了一定大小再批量保存，同时当间隔时间达到一定时间后
也同步(主要考虑数据延迟)  
4、flume配置参考  

a1.sources = r1
a1.sinks = k1
a1.channels = c1

# Describe/configure the source


a1.sources.r1.type = org.apache.flume.source.kafka.KafkaSource
a1.sources.r1.batchSize = 4
a1.sources.r1.batchDurationMillis = 2000
a1.sources.r1.kafka.bootstrap.servers = 10.20.128.235:9092
a1.sources.r1.kafka.topics = test

# Describe the sink
a1.sinks.k1.type = com.zhy.frame.newsql.clickhouse.sink.sink.ClickHouseKafkaSink
#当数据list达到8开始同步
a1.sinks.k1.batchSize = 8
a1.sinks.k1.host = localhost
a1.sinks.k1.port = 8123
a1.sinks.k1.database = default
a1.sinks.k1.table = sys_log
#数据同步间隔时间
al.sinks.k1.intervalDate = 8000

# Use a channel which buffers events in memory
a1.channels.c1.type = memory
a1.channels.c1.capacity = 1000
a1.channels.c1.transactionCapacity = 100

# Bind the source and sink to the channel
a1.sources.r1.channels = c1
a1.sinks.k1.channel = c1

5、这里没有做处理，一般我们实体字段命名都是驼峰，数据库是下划线。由于查询没有做处理，所以需要kafka里面的json格式数据
的key和数据库字段名称一致
