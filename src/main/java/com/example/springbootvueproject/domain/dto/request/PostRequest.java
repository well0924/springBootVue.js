package com.example.springbootvueproject.domain.dto.request;

import lombok.*;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {
    private String title;
    private String contents;
    private String author;
}
