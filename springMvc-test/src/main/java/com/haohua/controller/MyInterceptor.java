package com.haohua.controller;    /*
 * @author  Administrator
 * @date 2018/7/23
 */

import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--pre-interceptor");
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
       /* if (uri.startsWith("/user/login")){
            return  true;
        }
        if (session.getAttribute("admin")==null){
            response.sendRedirect("/login");
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("---------post-response-interceptor");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("-----after----complete-----");
    }
}
