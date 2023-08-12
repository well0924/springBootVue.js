package com.example.springbootvueproject.domain.dto.response;

import com.example.springbootvueproject.domain.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
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
