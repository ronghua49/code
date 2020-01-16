package com.sly.seata.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.jdbc.core.JdbcTemplate;
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
@SpringBootApplication(scanBasePackages = "com.sly.seata")
@EnableDiscoveryClient
@RestController
public class AccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }
    @Autowired
     private JdbcTemplate jdbcTemplate;
    @PostMapping("/account/insert")
    public String purchase(@RequestParam("accountId") String accountId,
                           @RequestParam("orderId") String orderId, @RequestParam("storageId") String storageId) {
        System.out.println(accountId + orderId + storageId);
        return "新增账户成功";
    }

    @GetMapping("/test")
    public String test(@RequestParam String  name){
        int update = jdbcTemplate.update("insert into product (pro_price,pro_name,inventory,version) values ('2000','小米9','400','2.0')");
        if(update==1){
            return  "success"+name;
        }else{
            return "insert fail";
        }
    }

}
