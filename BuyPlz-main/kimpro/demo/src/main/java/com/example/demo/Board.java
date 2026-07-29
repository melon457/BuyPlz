package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 번호

    private String title; // 제목

    @Column(columnDefinition = "TEXT")
    private String content; // 내용

    private String writer; // 작성자

    private LocalDateTime createdDate = LocalDateTime.now(); // 작성일

    private Integer views = 0; // 조회수

    private Integer likes = 0; // 좋아요
}