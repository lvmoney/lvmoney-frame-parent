1、启动seata服务，注意版本要和pom里的版本一致，demo用的是0.6.1版本
2、执行resources下的sql文件
3、修改test，client，server里面的数据库连接配置
4、分别启动test，server，client服务
5、通过postman测试localhost:8071/fegin/test3?SUserId=1&AUserId=1
6、可在5中接口里面显示Throw 错误查看回滚。
**7、必须要有undo_log表，执行过程中可以看到该表有数据，回滚完成后，
该表会自动清空。如果要查看该表数据，在6中throw处打断点去数据库查看**
**8、新建的表必须要有主键，同时在po上要有对应的主键注解，否则seata会读不到表的元数据，会报错**
