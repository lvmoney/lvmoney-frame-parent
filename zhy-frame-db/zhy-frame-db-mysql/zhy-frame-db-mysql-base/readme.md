mysql+mybatisplus  
ip:port/druid 用druid对mysql进行慢sql查看  
新增百度的数据库主键策略，使用的时候需要新增一个表同时拷贝resources的数据库操作文件    
在主方法上加@MapperScan("com.xfvape.uid")  
主键的类型使用type = IdType.ASSIGN_ID  
mysqlslap mysql压力测试工具 Percona Server+grafna     
多租户支持：frame.saas.support，默认不支持  
多租户表中的租户字段：frame.saas.tenant.filed 默认是：tenant_id。系统会默认拿到请求的token然后去解析获得tenant_id的值  
多租户忽略表：frame.saas.filterTable 默认是：role。多表用逗号(,)隔开  
多租户在方法上使用：在dao方法上使用注解@SqlParser(filter = true)  