package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction {
    private Long id;
    private Long userId;
    private Long orderId;
    private Double amount;
    private String type;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}