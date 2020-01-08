##                                      springcloud 配置中心

#### config-server

configserver 服务端启动后，GIT仓库中的配置文件会被自动转换成当前项目的web api。

访问规则：

- /{application}/{profile}[/{label}]   

　规则简单说明：{application}=配置消费方应用名称（即：config client的项目名，通俗讲：就是谁用这个配置就是谁的名字）,{profile}=配置环境（如：dev开发环境,test测试环境,prod生产环境）,{label}=仓库分支名（git或svn方式指定，native本地方式无需指定）

demo : 　http://localhost:8866/configclient/dev   、　http://localhost:8866/configclient-dev.yml

#### config-client

新建bootstrap.yml    启动加载顺序优先于application.yml  配置如下

![1578477694562](C:\Users\A\AppData\Roaming\Typora\typora-user-images\1578477694562.png)

若本地配置有和远程相同属性的配置，默认优先使用config-server 远程配置。

