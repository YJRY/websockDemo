package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //设置连接端点，跨域以及sockJS支持。客户端连接示例：http://localhost:8080/ws
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //设置发送给指定用户时频道的前缀。默认为user，可以不进行该项设置
        //用于SimpMessagingTemplate的convertAndSendToUser("user", {channel}, {data})方法
        registry.setUserDestinationPrefix("/user");
        //服务端点请求的前缀
        registry.setApplicationDestinationPrefixes("/request");
        //客户端订阅路径的前缀
        registry.enableSimpleBroker("/sub", "/topic");
    }
}
