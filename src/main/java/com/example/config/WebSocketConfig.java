package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //设置连接端点，跨域以及sockJS支持
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setUserDestinationPrefix("/user")
                //客户端发送消息时的前缀，和订阅无关，可和服务端端点重合
                .setApplicationDestinationPrefixes("/Pubsub")
                //设置服务端推送消息的端点，包括发送普通消息和订阅消息
                .enableSimpleBroker("/Pubsub", "/topic");
    }
}
