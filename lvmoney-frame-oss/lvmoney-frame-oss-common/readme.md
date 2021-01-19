文件上传类型和大小限制  
1、大小通过两种限制一个是springboot提供的通过servlet来拦截  
spring:  
  servlet:  
    multipart:  
      max-file-size: 1MB  
      max-request-size: 1MB  
这种配置全局错误拦截能拦截，但是无法把错误返回给客户端（servlet请求直接断了）  
2、通过interceptor来拦截判断类型和大小，文件：fileHeader.properties。由于文件后缀可以篡改，我们通过文件字节前缀来过滤拦截，类型增加需要自行添加    
file.fileHeader[0]=JPEG=FFD8FF  
file.fileHeader[1]=PNG=89504E47  
file.fileHeader[2]=GIF=47494638  
file.fileHeader[3]=TXT=75736167  
file.fileHeader[4]=PDF=255044462D312E  
file.fileHeader[5]=DOC=D0CF11E0  
file.fileHeader[6]=XML=3C3F786D6C  
file.fileHeader[7]=DOCX=504B0304  
file.fileHeader[8]=APK=504B030414000808  
file.fileHeader[9]=IPA=504B03040A000000  
file.urlFileType[0]=/frame/file/add=JPEG,PNG  ###url运行的文件为JPEG，PNG
file.fileSize[0]=/frame/file/add=100  ###url允许文件长传大小100字节