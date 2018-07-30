package com.haohua.erp.controllor;    /*
 * @author  Administrator
 * @date 2018/7/26
 */

import com.haohua.erp.entity.Employee;
import com.haohua.erp.exception.ServiceException;
import com.haohua.erp.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.*;

@Controller

public class LoginControllor {
    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/")
    public String employeeLogin(@CookieValue(required = false) String userTel,Model model){
        if(StringUtils.isNotEmpty(userTel)){
            model.addAttribute("userTel",userTel);
        }
        return "index";
    }
    @PostMapping("/")
    public String employeeLogin(HttpServletRequest request ,
                                HttpServletResponse response,
                                String userTel,
                                String password,
                                String remember,
                                HttpSession session,
                                RedirectAttributes redirectAttributes){
            String loginIp = request.getRemoteAddr();
        try{
            Employee employee = employeeService.findByTelAndPassword(userTel,password,loginIp);
            session.setAttribute("employee",employee);
            if (remember!=null){
                Cookie cookie = new Cookie("userTel",userTel.toString());
                cookie.setMaxAge(60*60*24*7);
                cookie.setDomain("localhost");
                cookie.setPath("/");
                response.addCookie(cookie);
            }else{
                Cookie[] cookie = request.getCookies();
               for(Cookie curr :cookie){
                   if (curr.getName().equals("userTel")){
                       curr.setMaxAge(0);
                       response.addCookie(curr);
                   }
               }
            }
        }catch (ServiceException e){
            redirectAttributes.addFlashAttribute("message",e.getMessage());
            return "redirect:/";
        }
        return  "redirect:/home";
    }
}


