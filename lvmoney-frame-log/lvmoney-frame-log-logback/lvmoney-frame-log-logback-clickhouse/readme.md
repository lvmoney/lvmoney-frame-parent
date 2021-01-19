拓展logbackAppender，使得日志直接记录到clickhouse  
先把日志写到*.log中再通过flume 或者  fluent等归集到clickhouse，那么由于我们的服务通过docker来启动同时
很多我们就需要把docker中的日志目录挂载到同一个宿主机的目录，然后再通过flume去读这个宿主机的目录，这样来配置操作工作流
较大  
基于性能的考虑我们把日志是先保存到缓存中，然后再批量同步到clickhouse中。
配置参考  
配置参考  
frame:
  db:
    clickhouse: ##日志收集使用clickhouse完成，不引入类mybatisplus的配置是因为如果需要使用mysql会冲突
      host: 192.168.10.74
      port: 8123
      user: default
      password:
      database: log_platform
  logback:
    support: true ##是否支持日志收集
    clickhouse:
      batchSize: 2 ##clickhouse批量保存阀值 
      intervalDate: 6000  ##clickhouse 保存时间间隔
      tableName: sys_log ##默认日志表，可通过yml配置变更