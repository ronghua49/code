package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/7/22
 */

import comhaohua.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 类上方添加RestController 表示该控制器的方法返回值为json数据
 * 直接发送到页面，不由视图解析器解析
 */
@RestController
public class TransObject {
    @GetMapping("/user/send")
    public List<User> sendUser(){
        User user1 = new User("张三","110","巩义","2352");
        User user2 = new User("李四  ","120","商丘","4956");
        List<User> userList = Arrays.asList(user1,user2);
        return userList;
    }
}
