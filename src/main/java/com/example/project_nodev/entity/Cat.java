package com.example.project_nodev.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //id

    private String title; // 어딨냥 글 제목

    private String summary; // 어딨냥 글 소개

    private String content; // 어딨냥 글 내용

    private String filename; // 사진 자료

    private String filepath;

    private LocalDateTime time; // 업로드
}
