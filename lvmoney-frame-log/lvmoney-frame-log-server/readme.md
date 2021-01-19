通过加注解的方式记录各个控制器的请求日志  
1、需要自己实现AbstractLogService,把日志放到某个组件  
2、这个要慎用，记录每个请求的日志是很耗时的，会降低系统的性能  
3、默认有一个frame.log.support的日志记录开关，当其为true的时候表示每个控制器都会记录日志。  
@NotLog注解可以不记录日志  
4、内部服务调用需要在head加入token结合jwt使用，登录接口会记录请求默认参数：username（可以在yml中配置）  
三方接口调我们的接口可能别人不会传token，系统会默认记录用户为：NotLogged  