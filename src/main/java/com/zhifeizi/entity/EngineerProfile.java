package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EngineerProfile {
    private Long id;
    private Long userId;
    private String realName;
    private String idCard;
    private String skills;
    private String verificationStatus;
    private Double rating;
    private Double balance;
    private Double servicePrice;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}