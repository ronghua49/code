package com.haohua;    /*
 * @author  Administrator
 * @date 2018/7/17
 */



import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
//代替xlm文件的java类
@Configuration // 标明配置文件
@ComponentScan//自动扫描注解
@EnableAspectJAutoProxy//允许通知类的自动代理
public class BaseContext {
}
