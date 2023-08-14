package com.example.springbootvueproject.domain.dto.response;

import com.example.springbootvueproject.domain.Role;
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
    private Role role;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
