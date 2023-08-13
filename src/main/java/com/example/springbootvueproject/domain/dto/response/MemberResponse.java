package com.example.springbootvueproject.domain.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
@AllArgsConstructor
public class MemberResponse {
    private Long id;
    private String userName;
    private String password;
    private Integer userAge;
    private String userEmail;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
