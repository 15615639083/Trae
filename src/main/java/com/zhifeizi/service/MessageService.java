package com.zhifeizi.service;

import com.zhifeizi.entity.Message;

import java.util.List;

public interface MessageService {
    boolean saveMessage(Message message);
    List<Message> getMessagesByOrderId(Long orderId);
}
