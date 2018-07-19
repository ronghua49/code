package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/7/19
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloSprinMvc {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hellow springMVC");
        return  "mySpringMVC";
    }
}
