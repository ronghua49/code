package com.forezp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceHiApplication.class, args);
        System.out.println("Hi service 启动成功！");
    }

    @Value("${server.port}")
    String port;

    @Value("${hoo}")
    String hoo;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi " + name + ",i am from port:" + port;
    }




    @RequestMapping("/hoo")
    public String path() {
        return "get hoo  " + hoo + ",i am from port:" + port;
    }


    @PostMapping("/pro")
    public List<Product> home(@RequestBody Product product) {
        ArrayList<Product> list = new ArrayList<>();
        list.add(product);
        return list;
    }


}
