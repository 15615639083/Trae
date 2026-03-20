package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private Long id;
    private Long orderId;
    private Long senderId;
    private String content;
    private String type;
    private LocalDateTime timestamp;
}