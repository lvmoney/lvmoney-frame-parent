#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可
2、注意！注意！注意！如果引入统一的网关路由那么官方提供的注解功能基本全部都不会起作用了
1）官方的注解基本都是在controller上的；
2）统一网关，注解是到到了对应的接口方法上
3）所以需要自定义注解去做一些在过滤器或者拦截器中的功能
3、目前基本完成的注解功能有：
@RouterService(path = "aService")：对应调用的接口
@RouterMethod(name = "test")：对应调用的接口方法
@ValidateMethod(isValidate=false)：方法入参是否需要校验，调用hibernate-validated完成。
该注解用到接口方法中，入参实体内部参数注解配置参考hibernate-validated。
这里的方法入参只能是一个实体
