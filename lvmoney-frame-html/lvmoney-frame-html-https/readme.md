1、http---->https访问  
1）在pom中加入
             <plugin>  
                <groupId>org.apache.maven.plugins</groupId>  
                <artifactId>maven-resources-plugin</artifactId>  
                <configuration>  
                    <nonFilteredFileExtensions>  
                        <!--这里是文件后缀-->
                        <nonFilteredFileExtension>p12</nonFilteredFileExtension>  
                    </nonFilteredFileExtensions>  
                </configuration>  
            </plugin>  
2）可以用mkcert生成对应的证书 
下载   
mv mkcert /usr/local/bin/  
mkcert localhost 127.0.0.1   
openssl pkcs12 -export -inkey localhost+1-key.pem -in localhost+1.pem -name localhost -out localhost.p12
记住这里输入的密码  
keytool -importkeystore -srckeystore localhost.p12 -srcstoretype pkcs12 -destkeystore localhost.jks输入上面的密码 
yml中加配置  
server:  
  port: 9901  
  #你生成的证书名字  

ssl:  
key-store: classpath:localhost.p12  
key-store-password: 123456  
key-store-type: PKCS12 也可以用jks,pom中的nonFilteredFileExtension该成jks  
server:
port: 9901
  #你生成的证书名字
  ssl:
    key-store: classpath:localhost.jks
    key-store-password: 123456
    key-store-type: JKS  
3）代码  
可在base 下的BaseHttpsConfig查看，默认是http 8080-->转到https服务端口  
