package com.zhifeizi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Resource {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String fileUrl;
    private Integer downloadCount;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}