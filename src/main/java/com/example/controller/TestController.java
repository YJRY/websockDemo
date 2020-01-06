package com.example.controller;

import com.example.websocket.WebsocketService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Controller
@RequestMapping("/test")
public class TestController {

    @Resource
    private WebsocketService websocketService;

    @RequestMapping("/test1")
    public String test() {
        return "socket";
    }


    @RequestMapping("/send")
    @ResponseBody
    public void send(String msg, Integer id) {
        websocketService.send(msg, id);
    }

    @SubscribeMapping("/user/{id}")//订阅
    public void subscribe(@DestinationVariable Integer id) {
        System.out.println("收到订阅，用户id：" + id);
    }

    @MessageMapping("/hello")//接收以"/app/hello"的消息，前缀在websocketConfig中设置
    public void msg(String name){
        System.out.println(name);
    }
}
