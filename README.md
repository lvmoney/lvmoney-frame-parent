# zhy-frame-parent  


#### 介绍

分布式后台常用技术完全整合架构。基于java springboot，通过maven依赖简单的引入即可使用各种常用技术。支持本地开发测试，nacos，k8s环境   

#### 目的  

统一公司所有系统的技术栈  
提升项目的开发进度   
省去技术负责人重复的造轮子  
解放开发的双手，使其有更多的时间陪陪自己的对象  

#### 使用方式

通过maven依赖的方式引用到项目模块即可。把zhy-frame-parent作为所有项目的parent，子项目一般都要引入zhy-frame-core，其他模块根据系统需要引入，详见各个module的readme.md。demo 可见 zhy-frame-demo

#### 服务支持

访问安全认证(oauth2,jwt)  
zhy-frame-authentication  
   
架构基础   
zhy-frame-base    
 
缓存框架  
zhy-frame-cache  

验证码  
zhy-frame-captcha  

云支持(k8s)  
zhy-frame-cloud  

统一配置中心（nacos,cloud config)  
zhy-frame-config   

架构通用基础(全局错误处理，配置加解密，序列化，swagger)  
zhy-frame-core  

关系型数据库（mysql分区分片,读写分离，分布式事务）  
zhy-frame-db  

框架demo  
zhy-frame-demo  

rpc服务调用访问(feign,httpclient)   
zhy-frame-dispatch  

dubbo相关（粗略，主要用springcloud）  
zhy-frame-dubbo  

html相关（静态化,https）    
zhy-frame-html  

定时任务管理器  
zhy-frame-job  

ipfs  
zhy-frame-ipfs  
  
统一日志收集框架（本地收集、远程收集)  
zhy-frame-log   

异步消息处理(队列，解耦)  
zhy-frame-mq   

新数据库(kv，类关系型,图)    
zhy-frame-newsql  

通知(邮箱，短信，钉钉，微信)  
zhy-frame-notice  

办公（excel，word）  
zhy-frame-office   

运维（监控，限流，爬虫，容器）     
zhy-frame-ops   

文件存储(本地，远程)   
zhy-frame-oss  

多线程（异步，线程池，多生产者多消费者）  
zhy-frame-pool  

注册中心（consul，nacos）  
zhy-frame-registry  

全文检索   
zhy-frame-retrieval   

路由网关(权限校验，黑白名单，是否允许被调用)  
zhy-frame-route   

边车（不同开发语言服务接入）  
zhy-frame-sidecar  

决策    
zhy-frame-strategy  

队列（粗略）  
zhy-frame-steam   

数据同步   
zhy-frame-sync  

测试  
zhy-frame-test  

工作流  
zhy-frame-workflow  
  

#### 技术罗列

springboot  
springcloud  
nacos  
sentinel  
ipfs  
docker  
k8s1.16.0  
istio1.3.0  
flink  
hadoop  
shiro  
oauth2  
activti  
jwt  
kafka  
rabbitmq  
seata(fescar)  
mongo  
mybatisplus  
tidb    
scylla  
vitess  
hugegraph  
等等  


#### 可行性

大部分技术已用到正式项目环境，以校验整体技术框架的正确性和可行性

#### 代码风格

开发的时候用了阿里代码的扫描工具，除某些测试代码均需通过扫描工具的验证

#### 支持力度

整个技术实现正在不断的完善

#### 参与贡献

框架构架和开发人员:  
1300515928@qq.com  
lvmoney


