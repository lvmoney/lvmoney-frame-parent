#介绍
1、引入该模块很简单作为系统的modules或者jar直接引入到项目即可
2、shiro的token验证和权限校验均做到了redis中，引入系统后，需要加入redis的配置
#redis single
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.pool.max-active=300
spring.redis.database=0
spring.redis.pool.max-wait=-1
spring.redis.pool.max-idle=100
spring.redis.pool.min-idle=20
spring.redis.timeout=60000

或者

#redis cluster
spring.redis.cluster.nodes=192.168.159.129:7001,192.168.159.129:7002,192.168.159.129:7003,192.168.159.129:7004,192.168.159.129:7005,192.168.159.129:7006
spring.redis.pool.max-active=300
spring.redis.database=0
spring.redis.pool.max-wait=-1
spring.redis.pool.max-idle=100
spring.redis.pool.min-idle=20
spring.redis.timeout=60000
3、该模块采用token的方式进行身份验证，没有用session的方式。每个需要登录校验的访问都需要在head配置token值
4、已做了拦截器，需要登录校验时，会自动对token进行登录校验，不需要在代码中显示的校验登录。
5、系统自定义了注解，如果不需要登录校验需要使用@NotToken 注解，也可以新增jwtConfig.properties文件，加入jwt.filterChainDefinitionList[1]=/img/**=ign来配置不用校验token
7、redis中shiro的数据结构如下：
{
  "expire": 18000,//失效时间
  "id": "1234567",//用户id
  "password": "1234121",//密码
  "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxMjM0NTY3In0.aSyzM5EM4pkUzN1bQUionTbMNF_CVnxmHXx2Lqqc_mE",//token
  "username": "test"//用户名
}

key值为：token值
8、为了方便开发和测试frame.jwt.support:false 默认是不支持的，需要改为true，系统就支持token了。

9、为了区分token的类型，jwt或者oauth2，在jwt token前面加了前缀：JWT:，参考JwtUtil

10、jwt的实现用到了拦截器在使用时需要我们动手去extends SerializerConfig把拦截器配置到我们系统并用spring托管，可参考JwtInterceptorConfig   

11、注意由于一个系统可能有多个拦截器，基于如上考虑。用的时候需要extends SerializerConfig，把拦截器加入到配置器里面，注意拦截器执行顺序，jwt应该放到最前面  