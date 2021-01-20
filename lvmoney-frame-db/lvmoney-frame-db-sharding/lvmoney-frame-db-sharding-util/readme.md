关于多租户的思考  
1）每个中台服务独享一个数据库节点，不能在一个中台服务针对不同的租户单独搞一个数据库节点，因为租户太多，数据库的量太大了，不方便维护   
2）按照算法将租户的数据均匀的分布到不同的节点
3）由于租户太多，虽然单数据库节点可能只会存入单一领域很强的数据，但是也不能一个表存入大量的数据，所以需要按照一定的规则进行分表  
4）分表的想法，采用一致性hash算法，在一定范围内根据租户的系统id，自动进行分表  
5) 通过表把租户的数据分开，以确保其数据的安全性  
6）当单表数据到一定条数时，新增表，注意把数据尽量新增到新增的表上 


着重考虑：
数据库表出问题，数据如何恢复
新增数据库或者表的时候，对应关系就会重置，如何处理已有的数据
强制不允许减少已有的库表
   