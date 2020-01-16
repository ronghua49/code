# 						seata的使用

### 1.事务和分布式事务

单体事务：在单片应用中，所有的模块使用同一个数据库连接，事务的控制使用*@Transciational* 注解控制本地数据一致性。

分布式事务：分布式事务是由一批分支事务组成的全局事务，各个模块应用属于一个微服务有不同的数据库链接。

### 2.Seata 的三个组成部分

- **事务协调器（TC）:**维护全局事务和分支事务的状态，驱动全局提交或者回滚（分支事务将注册到这里）
- **事务管理器（TM）:** 定义全局事务的范围：开始全局事务，提交或者回滚全局事务
- **资源管理器（RM）:**管理正在处理的分支事务的资源，与TC对话以注册分支事务并报告分支事务的状态，并区驱动分支事务的提交和回滚

### 3.Seata 管理事务的生命周期

1. TM要求TC开始一项新的全局事务。TC生成代表全局交易的XID。
2. XID通过微服务的调用链传播。
3. RM将本地事务注册为XID到TC的相应全局事务的分支。
4. TM要求TC提交或回退相应的XID全局事务。
5. TC驱动XID的相应全局事务下的所有分支事务以完成分支提交或回滚。

### 4.Seata 服务端的下载和安装  http://seata.io/en-us/docs/ops/deploy-server.html

安装启动

- ./bin/seata-server.sh(linux)
- bin\seata-server.bat（windows）

启动若报错需要删除 sessionStore 文件夹

### 5.使用步骤

1. 在各个业务数据库中执行 undo_log.sql  这张表是seata记录事务和回滚事务用的，必须创建.

2. 服务端seata-server 的启动  
   - seata-server.bat -p 8091  -h 127.0.0.1 -m file >seata.log&
   - nohup sh seata-server.sh -p 8091 -h 127.0.0.1 -m file &> seata.log &
   
3. 配置文件要点 

   ```java
   #服务端配置要点  file.conf 的 service模块
   default.grouplist = "127.0.0.1:8091" #指定服务端地址 
    disableGlobalTransaction = false  #开启分布式事务
    #微服务客户端配置要点
     vgroup_mapping.seata-springcloud-account-fescar-service-group = "default"
     default.grouplist = "127.0.0.1:8091"
     #disable seata  false 开启  true 关闭
     disableGlobalTransaction = false
   ```

4. yml配置要点

```java
spring:
  datasource:
    name: druidDataSource
    type: com.alibaba.druid.pool.DruidDataSource
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://127.0.0.1:3306/car?serverTimezone=UTC
      username: root
      password: root
```

5.DruidDataSource 数据源配置和注入容器，供seata 使用

```java
/**
 * Druid数据源配置
 * @author gsh456
 * @date 2019-03-15 11:10
 */
@Configuration
@EnableConfigurationProperties({DruidDataSourceProperties.class})
public class DruidConfig {
	private Logger logger = LoggerFactory.getLogger(DruidConfig.class);
    @Autowired
    private DruidDataSourceProperties properties;

    @Bean
    @ConditionalOnMissingBean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(properties.getDriverClassName());
        druidDataSource.setUrl(properties.getUrl());
        druidDataSource.setUsername(properties.getUsername());
        druidDataSource.setPassword(properties.getPassword());
        druidDataSource.setInitialSize(properties.getInitialSize());
        druidDataSource.setMinIdle(properties.getMinIdle());
        druidDataSource.setMaxActive(properties.getMaxActive());
        druidDataSource.setMaxWait(properties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(properties.getValidationQuery());
        druidDataSource.setTestWhileIdle(properties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(properties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(properties.isTestOnReturn());
        druidDataSource.setPoolPreparedStatements(properties.isPoolPreparedStatements());
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());

        try {
            druidDataSource.setFilters(properties.getFilters());
            druidDataSource.init();
        } catch (SQLException e) {
            logger.error("[DruidConfig:druidDataSource:SQLException]", e);
        }

        return druidDataSource;
    }
```



```Java
@Configuration
public class DatabaseConfiguration {

	private final ApplicationContext applicationContext;
	
	public DatabaseConfiguration(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.druid")
	public DruidDataSource druid() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}
	@Primary
	@Bean
	public DataSource dataSource(DruidDataSource druid) {
		DataSourceProxy druidDataSource = new DataSourceProxy(druid);
		return druidDataSource;
	}
}
```

6.注意事项

- 服务端seata  server 的 地址需要的客户端配置一样 并且可以双向访问。最佳实践 微服务数据库和seata服务端在一台机器。