1、通用模块，基本每个module都应该引入改module  
2、使用时需要注意lvmoney-frame-base-serializer这个引入的module是来处理拦截器和序列化的  
3、lvmoney-frame-base-serializer 继承了lvmoney-frame-base-interceptor并用spring就行管理  
4、有些系统可能会需要多个拦截器，这时去继承lvmoney-frame-base-serializer 加入我们自定义的拦截器并用spring托管  
5、lvmoney-frame-base-rm 改module我们大部分系统都需要，它是来做错误处理和swagger的，但是由于我们用的gateway和spring servlet实现不同，所以需要在gateway exclusion lvmoney-frame-base-rm  
6、打包后的jar防止反编译可以查看https://gitee.com/roseboy/classfinal  
7、系统可能存在多个interceptor，系统提供自动注入策略，无需我们再手动注入，使用@InterceptorBean在我们自定义的各种拦截器上即可，注意我们所有的拦截器需要extends HandlerInterceptorAdapter来完成否则扫描不到