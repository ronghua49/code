package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/7/19
 */
import comhaohua.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Controller 表示该类为控制器，返回值为String默认发送到视图解析器
 * @RequestMapping("/user")表示所有方法的请求路径加前缀 /user
 */
@Controller
@RequestMapping("/user")
public class HelloSprinMvc {

        @GetMapping
        public String hello(){
            return "user/hello";
        }


        @GetMapping("/login")
        public String login(@CookieValue(name = "username",required = false) String name,Model model){
            if(name!=null){
            model.addAttribute("name",name);
            }
            return "user/login";
        }
    /**
     * redirect:请求名  表示发起为重定向请求
     * 地址栏发生变化，避免表单重复提交
     * @param username 自动获得表单name属性为username对应的值
     * @param address 自动获得表单name属性为address对应的值
     * @return 发送地址到视图解析器解析
     */
        @PostMapping("/login")
        public String afterLogin(String username, String address, String remember, RedirectAttributes attributes, HttpServletRequest request,HttpServletResponse resp){
            attributes.addAttribute("username",username);
            attributes.addAttribute("address",address);
            if (remember!=null){
            Cookie cookie = new Cookie("username",username);
            cookie.setDomain("localhost");
            cookie.setMaxAge(60*24*7);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            resp.addCookie(cookie);
            }else{
                Cookie[] cookies=request.getCookies();
                if (cookies!=null){
                    for (Cookie cookie : cookies){
                        if (cookie.getName().equalsIgnoreCase("username")){
                            cookie.setMaxAge(0);
                        }
                    }
                }
            }
            return "redirect:/user/home";
        }
    /**
     *RequestParam为接受请求中的参数 参数名和属性名对应时可以不写
     * @param car 请求路径的参数
     * @param code 请求路径的参数
     * @param name 请求中的参数名为carName对应的值
     * @param model 传值对象
     * @return 视图解析器
     */
        @RequestMapping("/{car:\\w+}/{code:\\d+}")
        public String showCar(@PathVariable String car,@PathVariable Integer code,@RequestParam(name = "carName") String name,Model model){
            model.addAttribute("car",car);
            model.addAttribute("code",code);
            model.addAttribute("name",name);
            return "user/car";
        }
        @RequestMapping("/page")
      public String page(@RequestParam(value = "p") int pageNo,Model model){
            model.addAttribute("pageNo",pageNo);
            return "user/page";
      }
      @RequestMapping("/add")
      public String addUser(){
            return "user/addUser";
      }
      @RequestMapping(value = "/add",method = RequestMethod.POST)
      public String addUser(User user,Model model){
          System.out.println(user.getUsername());
          System.out.println( user.getAddress());
          model.addAttribute("username",user.getUsername());
          model.addAttribute("address",user.getAddress());
          return "redirect:/user/home";
      }
    @GetMapping("/home")
    public String home(){
            return "user/home";
    }
    /**
     * ResponseBody表示返回值直接发送页面不经过视图解析
     * produces设置发送页面信息为字符串类型
     * @return 要发送页面的字符串内容
     */
    @ResponseBody
    @GetMapping(value = "/string",produces = "text/plain;charset=UTF-8")
    public String responseString(){
            return  "直接传入页面字符串";
    }

    @ResponseBody
    @GetMapping("/json")
    public User sendAsJson(){
            User user = new User();
            user.setAddress("巩义");
            user.setPassword("3453");
            user.setTel("1234123452");
            user.setUsername("李白");
            return user;
    }
}
