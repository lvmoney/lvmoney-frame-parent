1、调用远程服务，想直接把rpc服务的错误抛出。可以在feign client 加上配置 DisableHystrix.class  
2、使用了FallbackFactory rpc的错误就会进入我们 client的控制器，需要我们自己拿到远程服务的错误自己去处理  
3、加上配置 DisableHystrix.class后，远程服务或者服务自己配置了限制规则，会把错误直接返给客户端  
4、使用了FallbackFactory rpc 服务限流会进我们的控制器，自己服务限流会抛限流的错误不进我们的控制器  
5、@SentinelResource 上面的配置都没有，FrameUrlBlockHandler注释掉，才起作用。我们就需要每个控制器都要去处理，不推荐这么玩  