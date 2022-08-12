module划分项目结构demo  
使用maven命令可以迅速帮我们生成项目  
1、进入 lvmoney-frame-archetype 目录 执行mvn archetype:create-from-project
2、执行1后在lvmoney-frame-archetype下会生成target目录。进入这个目录下generated-sources\archetype  
3、执行mvn clean install  
4、执行完3后，我们就可以在我们本地仓库里看到我们的模板工程了  
5、这里我们可以在仓库./m2下的archetype-catalog.xml查看  
<?xml version="1.0" encoding="UTF-8"?>
<archetype-catalog xsi:schemaLocation="http://maven.apache.org/plugins/maven-archetype-plugin/archetype-catalog/1.0.0 http://maven.apache.org/xsd/archetype-catalog-1.0.0.xsd"
    xmlns="http://maven.apache.org/plugins/maven-archetype-plugin/archetype-catalog/1.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <archetypes>
    <archetype>
      <groupId>com.lvmoney.frame</groupId>
      <artifactId>lvmoney-frame-archetype-archetype</artifactId>
      <version>1.0.RELEASE</version>
      <description>lvmoney-frame-archetype-archetype</description>
    </archetype>
  </archetypes>
</archetype-catalog>

6、在合适的目录下（例如：D:/data）执行下面的语句会生成和我们的模板类似的项目  
mvn archetype:generate -DgroupId=com.lvmoney.platform -DartifactId=user -Dversion=1.0.RELEASE -Dpackage=com.lvmoney.platform -DarchetypeGroupId=com.lvmoney.frame -DarchetypeArtifactId=lvmoney-frame-archetype-archetype -DarchetypeVersion=1.0.RELEASE -B -DarchetypeCatalog=local -DinteractiveMode=false  
