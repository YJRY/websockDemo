package com.example.websocket;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class WebsocketService {

    @Resource
    private SimpMessagingTemplate template;//注入消息发送模板，发送方法自定义

    public void send(String channel, String msg) {
        template.convertAndSend(channel, msg);
    }

    public void sendToUser(String desUser, String channel, String message) {
        template.convertAndSendToUser(desUser, channel, message);
    }
}
