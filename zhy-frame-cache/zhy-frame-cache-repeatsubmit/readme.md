1、对同一个人通过同一个ip发起的同一个请求加上分布式锁，以限制重复提交  
2、frame.repeactsubmit.support=true 表示支持防止重复提交   
3、repeactsubmit.time=10 表示限制重复请求的时间默认为10s  
4、系统通过aop，默认会对所有的控制器进行重复校验，如果想某个控制器不需要重复判断校验，
在控制器上加上注解@NoRepeatSubmit NoRepeatSubmitEnum ALLOW 表示支持，NOT_ALLOW表示该控制器不需要重复校验.
FILE表示是有文件，注意：默认都使用NOT_ALLOW，这个会把请求参数就行签名然后在加锁来验证，FLIE:级别会高一些，不会去签名请求参数，只会针对请求的url加锁  
5、接口的重复访问时间可以灵活设置，需要@NoRepeatSubmit required=true time=2，表示限制2s

