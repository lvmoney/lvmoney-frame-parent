缓存通用设计  
1、根据需求引入pom  
2、框架通过CacheService提供统一的接口  
3、调用时采用如下代码  
@CacheImpl(CacheConstant.CACHE_REDIS)  
CacheService cacheService;  
CacheConstant.CACHE_REDIS:对应不同的cache实现方式，开发人员不需要关心，  
架构组人员不同的CacheService实现方式组件需要使用注解@CacheService，这个注解和@CacheImpl注解一一对应  

