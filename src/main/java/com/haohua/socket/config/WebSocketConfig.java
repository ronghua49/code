package com.haohua.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @Description: 启用websocket消息处理，以及收发消息的域
 * @Param:
 * @return:
 * @Author: 荣浩华
 * @Date: 2019/12/23
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //topic和queue这两个域上可以向客户端广播消息,（@SendTo）发送接口前缀
        config.enableSimpleBroker("/topic", "/queue");
        // 设定后端接受前端消息的接口前缀省略， 即客户端向服务端发送时的主题上面需要加"/app"作为前缀；
        config.setApplicationDestinationPrefixes("/app");
    }

    // 注册后端webSocket 和前端链接通道  这个和客户端创建连接时的url有关
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").setAllowedOrigins("*").withSockJS();
        registry.addEndpoint("/endpointWisely").withSockJS();
        registry.addEndpoint("/endpointChat").withSockJS();//1
    }

}