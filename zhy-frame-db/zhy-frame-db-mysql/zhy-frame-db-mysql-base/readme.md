mysql+mybatisplus  
ip:port/druid 用druid对mysql进行慢sql查看  
新增百度的数据库主键策略，使用的时候需要新增一个表同时拷贝resources的数据库操作文件    
在主方法上加@MapperScan("com.xfvape.uid")  
主键的类型使用type = IdType.ASSIGN_ID  
mysqlslap mysql压力测试工具    