package com.sly.seata.business;

import feign.Contract;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2020/1/6 14:07
 */
@Configuration
public class FeignConfig {

    public FeignConfig() {
    }

    @Bean
    public Contract feignContract() {
        return new SpringMvcContract();
    }


}
