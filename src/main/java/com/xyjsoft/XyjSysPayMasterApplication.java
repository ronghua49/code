package com.xyjsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.xyjsoft.core.config.FeignHystrixConcurrencyStrategyIntellif;

@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication(scanBasePackages={"com.xyjsoft"})
public class XyjSysPayMasterApplication {

	public static void main(String[] args) {
		SpringApplication.run(XyjSysPayMasterApplication.class, args);
	}

	@Bean
	public FeignHystrixConcurrencyStrategyIntellif feignHystrixConcurrencyStrategy(){
		return new FeignHystrixConcurrencyStrategyIntellif();
	}
}
