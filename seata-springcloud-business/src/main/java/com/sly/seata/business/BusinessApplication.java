package com.sly.seata.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * business 启动类
 * @author sly
 * @time 2019年6月12日
 */
@SpringBootApplication(scanBasePackages = "com.sly.seata")
@EnableDiscoveryClient
@EnableFeignClients
public class BusinessApplication {
	public static void main(String[] args) {
		SpringApplication.run(BusinessApplication.class, args);
	}

}
