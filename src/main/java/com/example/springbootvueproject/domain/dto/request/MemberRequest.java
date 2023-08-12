package com.example.springbootvueproject.domain.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String userName;
    private String password;
    private Integer userAge;
    private String userEmail;
}
