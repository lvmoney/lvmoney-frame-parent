1、对同一个人通过同一个ip发起的同一个请求加上分布式锁，以限制重复提交  
2、frame.repeactsubmit.support=true 表示支持防止重复提交   
3、repeactsubmit.time=10 表示限制重复请求的时间默认为10s  
4、系统通过aop，默认会对所有的控制器进行重复校验，如果想某个控制器不需要重复判断校验，
在控制器上加上注解@NoRepeatSubmit required=true 表示支持，required=false表示该控制器不需要重复校验  

