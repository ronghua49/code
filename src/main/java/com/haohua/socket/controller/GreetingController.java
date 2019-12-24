package com.haohua.socket.controller;

import com.haohua.socket.model.Greeting;
import com.haohua.socket.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @Description: socket 消息的发送和接受
 * @Param:
 * @return:
 * @Author: 荣浩华
 * @Date: 2019/12/23
 */
@Controller
@EnableScheduling
public class GreetingController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpMessagingTemplate messagingTemplate;//消息模板


    /**
     * @Description: webSocket的广播式通信
     * @Param: 接受前端推送的消息类
     * @return:
     * @Author: 荣浩华
     * @Date: 2019/12/23
     */
    @MessageMapping("/hello")//浏览器发送请求通过@messageMapping 映射/hello 这个地址。
    @SendTo("/topic/greetings")//服务器端有消息时,会订阅@SendTo 中的路径向浏览器发送消息。
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println(message.getName());
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }


    /**
     * @Description: 订阅消息处理接口
     * @Param:
     * @return: 返回消息到订阅该接口的客户端
     * @Author: 荣浩华
     * @Date: 2019/12/23
     */
    @SubscribeMapping("/subscribeTest")
    public Greeting greeting2() {
        logger.info("xxx用户订阅了我");
        return new Greeting("感谢xxx订阅此消息");
    }

    /**
     * @Description: 接受客户端1对1消息，并推送消息到该客户端
     * @Param: sendToUser 默认会加上/user前缀作为地址找寻前端订阅客户端
     * @return:
     * @Author: 荣浩华
     * @Date: 2019/12/23
     */
    @MessageMapping("/message")
    @SendToUser("/queue/message")
    public Greeting message(StompHeaderAccessor stompHeaderAccessor, HelloMessage userMessage) {
        Principal user = stompHeaderAccessor.getUser();
        String name1 = user.getName();
        System.out.println(name1);
        logger.info("接收到客户端消息为：" + userMessage.getName());
        return new Greeting("hello," + userMessage.getName());
    }


    /**
      * @Description:   MessageExceptionHandler注解处理消息方法中的异常 SendToUser 把错误信息抛送指定用户
      * @Param:  
      * @return:  
      * @Author: 荣浩华
      * @Date: 2019/12/24
     */
    @MessageExceptionHandler(Exception.class)
    @SendToUser("/queue/errors")
    public String handleExceptions(Exception t) {
        t.printStackTrace();
        return t.getClass().toString();
    }


    /**
     * @Description: 定时精准推送消息到指定客户端
     * @Param:jia
     * @return:
     * @Author: 荣浩华
     * @Date: 2019/12/23
     */
    @Scheduled(cron = "0/3 * * * * ? ")
    public void senMessageToUser() {
        messagingTemplate.convertAndSendToUser("荣", "/queue/message", new Greeting("Hello, " + HtmlUtils.htmlEscape("hello rong") + "!"));
    }

    /**
     * @Description: 后端定时广播向前端推送消息
     * @Param:
     * @return:
     * @Author: 荣浩华
     * @Date: 2019/12/23
     */
    @Scheduled(cron = "0/5 * * * * ? ")
    public void senMessageToClient() {
        messagingTemplate.convertAndSend("/topic/greetings", new Greeting("Hello, " + HtmlUtils.htmlEscape("send...topic") + "!"));
    }


}
