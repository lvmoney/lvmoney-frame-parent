interceptor
1、系统可能存在多个interceptor，系统提供自动注入策略，无需我们再手动注入，使用@InterceptorBean在我们自定义的各种拦截器上即可  
2、注意我们所有的拦截器需要extends HandlerInterceptorAdapter来完成否则扫描不到  
3、对于interceptor执行顺序需要通过权重来排序，值越大越先执行。通过interceptorOrder.properties来配置  
4、interceptorOrder.properties的配置讲解  
interceptor.order[0]=jwtInterceptor=99  ## jwtInterceptor 对应interceptor的beanId，系统做了首字母小写的转化
interceptor.order[1]=fileHeaderCheckInterceptor=999  

如上配置interceptor执行顺序fileHeaderCheckInterceptor>jwtInterceptor  
5、通过interceptorOrder.properties来配置顺序（没在注解里面加值）主要是方便项目自定义执行顺序

