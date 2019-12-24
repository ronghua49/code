package com.haohua.socket.config;

import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

/**
 * 简单的用户添加和接口安全配置
 *
 * @author haohua
 * @version 1.0
 * @describe
 * @date 2019/12/24 11:03
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 密码加密 重写不加密
    @Bean
    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        };
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("荣")
                .password("123")
                .roles("admin")
                .and()
                .withUser("曹")
                .password("123")
                .roles("user")
                .and()
                .withUser("程")
                .password("123")
                .roles("user")
                .and()
                .withUser("张")
                .password("123")
                .roles("user")
                .and()
                .withUser("葛")
                .password("123")
                .roles("user");
    }

    // 任何请求需要进行授权认证才能访问
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .permitAll();
    }

}
