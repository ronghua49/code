package com.sly.seata.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * account 启动类
 *
 * @author sly
 * @time 2019年6月12日
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
    @PostMapping("/account/insert")
    public String purchase(@RequestParam("accountId") String accountId,
                           @RequestParam("orderId") String orderId, @RequestParam("storageId") String storageId) {
        System.out.println(accountId + orderId + storageId);
        return "新增账户成功";
    }

    @GetMapping("/test")
    public String test(@RequestParam(name = "name") String  name){
        return  "success"+name;
    }

}
