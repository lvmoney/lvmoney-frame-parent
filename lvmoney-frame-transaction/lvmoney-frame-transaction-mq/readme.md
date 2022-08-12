分布式事务  
主要通过kafka+redis来解决  
1、消息提供者在本地方法执行完成后，调用transactionProviderService.distributedTransaction(transactionDto)  
2、会将信息同步到redis，状态为NEW，为了防止数据重复，会额外新增随机字段uuid。  
key=TRANSACTION_MQ_PREFIX_当前消息状态_系统名称_transactionVo参数排序连接后的base值  
value=transactionRo  
说明:transactionVo是transactionRo里面的一个确定值，在进行排序拼接加base64时，会是一个固定值，方便数据异常时，provider,customer做数据校验  
3、保存到redis后，会发送消息到kafka，发送成功后会更新redis状态为PUBLISHED  
4、消息消费者拿到消息后，会首先将数据保存到redis，状态为RECEIVED   
5、当业务处理完成后，会修改redis状态为PROCESSED  
6、使用 
消息发送者provider需要发送异步消息时需要显示使用transactionProviderService.distributedTransaction(transactionDto)   
消息消费者customer需要在自定义注解（CustomerService）的MqDataHandService的handData方法上加上注解 @HandleTransaction  
7、@HandleTransaction注解通过aop来实现事务处理，before:将受到的消息保存到redis，AfterReturning当6中的handData处理不报错时修改状态为PROCESSED  
8、当provider数据的状态为NEW时说明发送消息到kafka失败，customer的状态为RECEIVED时说明消息处理报错   
通过查看redis的中对应的事务数据，进行重试等操作（一般情况不会有）保证数据的最终一致  