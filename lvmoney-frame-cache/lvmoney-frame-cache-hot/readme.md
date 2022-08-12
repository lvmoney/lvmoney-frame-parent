2、实现了热点接口返回的数据同步到caffeine，如果caffeine中有数据直接返回  
3、需要配置frame.redis.hotRequest.support 来支持热点接口的控制  
4、使用@HotRequest注解来控制控制器的热点情况，threshold 阀值 默认 10000次，section监控区间 默认60s ,expired 失效时间 1800s 
5、要注意配置caffeine数据的失效时间caffeine.expire.time
6、热点数据实现逻辑，请求来时处理@HotRequest的控制器，第一次会把请求的数据和返回结果直接保存到redis并开始计数
当没有达到阀值时直接从redis中获得数据，当达到阀值时同步接口结果到caffeine中，后续请求直接从caffeine中获得数据。
当请求的计数时间区间达到section同时没有达到阀值那么重新开始计数并更新计数时间
7、热点数据一般不会持续很久，设置了caffeine失效时间和redis失效时间防止了一直热点，当到时间了还在热点，那么就会重复步骤6  
8、热点数据用到了拦截器，如果需要用我们需要在我们系统extends SerializerConfig把拦截器配置到我们系统并用spring托管，可参考HotRequestInterceptorConfig    
9、注意多个拦截器的情况  