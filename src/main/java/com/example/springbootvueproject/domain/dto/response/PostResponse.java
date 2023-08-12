package com.example.springbootvueproject.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String title;
    private String contents;
    private String author;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
