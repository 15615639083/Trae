package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String status;
    private LocalDateTime registerTime;
    private LocalDateTime updateTime;
}