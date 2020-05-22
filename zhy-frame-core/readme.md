1、通用模块，基本每个module都应该引入改module  
2、使用时需要注意zhy-frame-base-serializer这个引入的module是来处理拦截器和序列化的  
3、zhy-frame-base-serializer 继承了zhy-frame-base-interceptor并用spring就行管理  
4、有些系统可能会需要多个拦截器，这时去继承zhy-frame-base-serializer 加入我们自定义的拦截器并用spring托管  
5、zhy-frame-base-rm 改module我们大部分系统都需要，它是来做错误处理和swagger的，但是由于我们用的gateway和spring servlet实现不同，所以需要在gateway exclusion zhy-frame-base-rm  