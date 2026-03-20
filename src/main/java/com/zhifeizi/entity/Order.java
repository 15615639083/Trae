package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Order {
    private Long id;
    private Long userId;
    private Long engineerId;
    private Long adminId;
    private String title;
    private String description;
    private Double price;
    private String status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}