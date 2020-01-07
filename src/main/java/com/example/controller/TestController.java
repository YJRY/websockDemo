package com.example.controller;

import com.example.websocket.WebsocketService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private WebsocketService websocketService;

    @RequestMapping("/socket")
    public String socket() {
        return "socket";
    }

    @RequestMapping("/send")
    public String send() {
        return "send";
    }

    @RequestMapping("/sendUser")
    public String sendUser() {
        return "sendUser";
    }

    @RequestMapping("/receive")
    public String receive() {
        return "receive";
    }

    @RequestMapping("/receiveUser")
    public String receiveUser() {
        return "receiveUser";
    }

    @RequestMapping("/purchase")
    public String purchase() {
        return "purchase";
    }

    //订阅监测
    @SubscribeMapping("/user/{id}")//订阅
    public void subscribe(@DestinationVariable Integer id) {
        System.out.println("收到订阅，用户id：" + id);
    }

    //接收客户端发送的消息，路径：/request/hello
    @MessageMapping("/hello")//接收以"/request/hello"的消息，前缀在websocketConfig中设置
    public void msg(String name) {
        System.out.println(name);
    }

    //接收客户端发送的消息，路径：/request/hello
    @MessageMapping("/send")
    public void sendMsg(String value) {
        websocketService.send("/sub/chat", value);
    }

    // 将消息发送给特定用户
    @MessageMapping("/sendUser")
    public void sendToUser(String body) {
        String srcUser = "user1";
        // 解析用户和消息
        String[] args = body.split(",");
        String desUser = args[0];
        String message = "【" + srcUser + "】给你发来消息：" + args[1];
        // 发送到用户和监听地址
        websocketService.sendToUser(desUser, "/queue/customer", message);
    }
}
