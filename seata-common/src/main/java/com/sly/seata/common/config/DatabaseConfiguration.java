package com.sly.seata.common.config;


import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * 分布式事物使用
 * @author YH
 *
 */
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
