###
说明:
仓库中的配置文件会被转换成 Web 接口，访问可以参照以下的规则：

/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties
上面的 URL 会映射{application}-{profile}.yml对应的配置文件，其中{label}对应 Git 上不同的分支，
默认为 master。以 config-client-dev.yml 为例子，它的 application 是 config-client，profile 是 dev。
###
服务验证

http://localhost:8800/cms-application-dev.yml
http://localhost:8800/cms-application/dev