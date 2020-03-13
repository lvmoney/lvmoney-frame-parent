nacos+springboot从服务器获得配置   
**安装nacos**  
从github获得nacos的安装包，在resouces目录下已有  
上传至contos，解压并用sh startup.sh -m standalone命令启动  
通过ip:8848/nacos 查看，密钥和密码nacos/nacos  
1、配置  
新增命令空间frame其id为10399e42-5201-471b-9206-f990f2801793  
在命名空间frame新增配置  
test环境  
Data Id:nacos-client-test.yaml  
Group:frame-application  
选择类型：yaml  
内容：就是常用的application.yml里面的配置  
dev环境  
Data Id:nacos-client-dev.yaml  
Group:frame-application  
选择类型：yaml  
内容：就是常用的application.yml里面的配置  

2、1中配置的目的是通过profile切换不同的环境的配置  
本地bootstrap的配置参考bootstrap，bootstrap-dev，bootstrap-test，同时需要在pom中构造profiles  
下面一个简单的说明
spring:
  application:
    name: nacos
  cloud:
    nacos:
      config:
        #nacos安装地址
        server-addr: 10.20.128.234:8848
        # 配置文件后缀名为yaml
        file-extension: yaml
        #对应1中的Group
        group: frame-application
        #对应1中的命令空间frame对应的id
        namespace: 10399e42-5201-471b-9206-f990f2801793
        #对应1中的Data Id 的名字不包括后缀
        name: nacos-client-dev


通过如上配置就能根据需求切换不同的配置的环境  

**nacos 把数据放到mysql中**  
Nacos Server 默认使用的是内嵌的数据库，生产环境建议修改使用 mysql 数据库存储配置信息。  

在配置文件application.properties添加配置：  

spring.datasource.platform=mysql  
db.num=1  
db.url.0=jdbc:mysql://10.20.128.234:3306/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true  
db.user=root  
db.password=root  
创建数据库，在Nacos Server conf文件夹下，找到nacos-mysql.sql文件，导入创建的数据库即可。  

Nacos默认账号密码为：nacos，修改密码需要使用引入：  
在当前nacosmodule里面加如下配置会报没有权限的错误  
<dependency>  
        <groupId>org.springframework.boot</groupId>  
        <artifactId>spring-boot-starter-security</artifactId>  
</dependency>  
然后使用代码加密：  
public class PasswordEncoderUtil {  
    public static void main(String[] args) {  
        System.out.println(new BCryptPasswordEncoder().encode("nacos"));  
    }  
}  


