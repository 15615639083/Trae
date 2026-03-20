package com.zhifeizi.service.impl;

import com.zhifeizi.entity.Message;
import com.zhifeizi.mapper.MessageMapper;
import com.zhifeizi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public boolean saveMessage(Message message) {
        return messageMapper.insert(message) > 0;
    }

    @Override
    public List<Message> getMessagesByOrderId(Long orderId) {
        return messageMapper.selectByOrderId(orderId);
    }
}
