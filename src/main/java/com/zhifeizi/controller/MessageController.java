package com.zhifeizi.controller;

import com.zhifeizi.entity.Message;
import com.zhifeizi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    // 发送消息
    @PostMapping
    public Map<String, Object> sendMessage(@RequestBody Message message) {
        boolean result = messageService.saveMessage(message);
        if (result) {
            return Map.of("success", true, "message", "消息发送成功", "data", message);
        } else {
            return Map.of("success", false, "message", "消息发送失败");
        }
    }

    // 获取订单的消息列表
    @GetMapping("/order/{orderId}")
    public Map<String, Object> getOrderMessages(@PathVariable Long orderId) {
        List<Message> messages = messageService.getMessagesByOrderId(orderId);
        return Map.of("success", true, "messages", messages);
    }

    // 获取用户的消息列表（所有订单）
    @GetMapping("/user/{userId}")
    public Map<String, Object> getUserMessages(@PathVariable Long userId) {
        // 这里需要在MessageService中添加根据用户ID获取消息的方法
        // 暂时返回空列表，后续实现
        return Map.of("success", true, "messages", List.of());
    }
}
