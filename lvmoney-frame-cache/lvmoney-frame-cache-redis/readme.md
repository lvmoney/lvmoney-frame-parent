1、为了更好的通过redis desktop查看redis数据，在RedisConfig配置了redis key和value的
序列化方式  
2、经过测试发现，如果redis的value 实体嵌套了，那么反序列话要报错，所以如果有嵌套建议
value 通过JsonUtil序列化（jsonString）后存到redis，取出值以后再转化成对应的实体对象  
3、不配置1desktop看着很乱，但是实体嵌套的问题没有，value存什么实体，取什么就行。  
4、着重注意一下redisConfig的说明  
5、有时候redis数据有版本的控制，例如：一天的数据一个版本，各个服务间调用不同版本的数据。
建议在业务系统考虑，在key_version,在查询的时候获得要得版本号，根据key构造key_version再到redis请求  
6、redis里面的数据根据业务需求某一类数据可能需要定时更新，或者设置过期时间，或者多久没更新就删除某些数据等等。这个时候可以去判断某个key的ttl，
这个时候需要获得key的创建时间或者它的失效时间，这里有两种思路提供：  
1）如果只去更新某个key，同时业务中清晰的知道过期时间开始时间什么的（有专门的存储），那么直接获得对应的值即可  
2）如果是批量去更新，通过redis自身的方式去更新，建议在save的时候在key值上记录创建时间和失效时间：key_创建时间_失效时间，这样方便
直接通过key就可以获得然后去更新
7、使用frame.redis.listener.support 配置来支持redis的监听策略,代码层面目前支持增，删，expired,rename的拓展。注意配置redis.conf notify-keyspace-events Exg$  
K：keyspace事件，事件以__keyspace@<db>__为前缀进行发布；         
E：keyevent事件，事件以__keyevent@<db>__为前缀进行发布；         
g：一般性的，非特定类型的命令，比如del，expire，rename等；        
$：字符串特定命令；         
l：列表特定命令；         
s：集合特定命令；         
h：哈希特定命令；         
z：有序集合特定命令；         
x：过期事件，当某个键过期并删除时会产生该事件；         
e：驱逐事件，当某个键因maxmemore策略而被删除时，产生该事件；         
A：g$lshzxe的别名，因此”AKE”意味着所有事件。 
