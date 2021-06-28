# lvmoney-frame-parent  


#### 介绍

java版分布式微服务后台常用技术完全整合架构。基于springboot ，springcloud istio，通过maven依赖简单的引入即可使用各种常用技术。支持本地开发测试，nacos，k8s(istio)环境

#### 最近新增  
新增区块链模块，主要借助webase完成对FISCO BCOS的操作  

#### 目的  

统一公司所有系统的技术栈  
提升项目的开发进度   
省去技术负责人重复的造轮子  
解放开发的双手，使其有更多的时间陪陪自己的对象  

#### 使用方式

通过maven依赖的方式引用到项目模块即可。框架通过lvmoney-frame-bom引入了所有依赖和版本，子项目无需把框架作为parent，只需要把lvmoney-frame-bom作为dependencyManagement即可。子项目一般都要引入lvmoney-frame-core，其他模块根据系统需要引入，详见各个module的readme.md。demo 可见 lvmoney-frame-demo。某服务作为rpc服务时，需要自定义api发布到nexus中，那么服务提供方需要引入依赖实现接口功能，服务使用方引入依赖通过feign访问即可


#### 发布

整合jenkins。通过配置可一键构建系统的docker 镜像推送到私服 hardor，运行到k8s集群。同时伴随完成基于prometheus体系监控体系  
通过开源工具的部署和开发规范，完整支持DEVOPS整个流程

#### 服务支持


访问安全认证(oauth2,jwt)  
lvmoney-frame-authentication  
   
架构基础   
lvmoney-frame-base    
 
缓存框架  
lvmoney-frame-cache  

验证码  
lvmoney-frame-captcha  

云支持(k8s)  
lvmoney-frame-cloud  

统一配置中心（nacos,cloud config)  
lvmoney-frame-config   

架构通用基础(全局错误处理，配置加解密，序列化，swagger)  
lvmoney-frame-core  

关系型数据库（mysql分区分片,读写分离，分布式事务）  
lvmoney-frame-db  

框架demo  
lvmoney-frame-demo  

rpc服务调用访问(feign,httpclient)   
lvmoney-frame-dispatch  

dubbo相关（粗略，主要用springcloud）  
lvmoney-frame-dubbo  

html相关（静态化,https）    
lvmoney-frame-html  

定时任务管理器  
lvmoney-frame-job  

ipfs  
lvmoney-frame-ipfs  
  
统一日志收集框架（本地收集、远程收集)  
lvmoney-frame-log   

异步消息处理(队列，解耦)  
lvmoney-frame-mq   

新数据库(kv，类关系型,图)    
lvmoney-frame-newsql  

通知(邮箱，短信，钉钉，微信)  
lvmoney-frame-notice  

办公（excel，word）  
lvmoney-frame-office   

运维（监控，限流，爬虫，容器）     
lvmoney-frame-ops   

文件存储(本地，远程)   
lvmoney-frame-oss  

多线程（异步，线程池，多生产者多消费者）  
lvmoney-frame-pool  

注册中心（consul，nacos）  
lvmoney-frame-registry  

全文检索   
lvmoney-frame-retrieval   

路由网关(权限校验，黑白名单，是否允许被调用)  
lvmoney-frame-route   

边车（不同开发语言服务接入）  
lvmoney-frame-sidecar  

决策    
lvmoney-frame-strategy  

队列（粗略）  
lvmoney-frame-steam   

数据同步   
lvmoney-frame-sync  

测试  
lvmoney-frame-test  

工作流  
lvmoney-frame-workflow  

区块链  
lvmoney-frame-blockchain  


#### 技术罗列

springboot  
springcloud  
nacos  
sentinel  
ipfs  
docker  
k8s1.18.0  
istio1.8.0  
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
webase  
FISCO BCOS
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

#### 其他 



![说明](https://images.gitee.com/uploads/images/2020/0724/095934_b4551d4f_107773.png "说明.png")

以nacos为核心

![以nacos为核心](https://images.gitee.com/uploads/images/2020/0724/094358_2bb5e1a0_107773.png "nacos.png")

我们的架构

![输入图片说明](https://images.gitee.com/uploads/images/2021/0119/114349_87aab8df_107773.png "微信截图_20210119114013.png")
roadmap

![roadmap](https://images.gitee.com/uploads/images/2020/0724/095048_f4de7e47_107773.png "roadmap.png")  

服务发布
![输入图片说明](https://images.gitee.com/uploads/images/2021/0119/114028_9a74a927_107773.png "微信截图_20210119114013.png")

运行监控
![输入图片说明](https://images.gitee.com/uploads/images/2021/0119/114120_899f8c87_107773.png "微信截图_20210119114013.png")

我们内部使用的业务中台和数据中台双中台战略，具体ppt可以联系lvmoney


部分博客文档:https://www.infoq.cn/profile/1B873773DECDB0/publish






