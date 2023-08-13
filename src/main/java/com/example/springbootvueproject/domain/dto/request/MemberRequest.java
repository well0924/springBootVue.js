package com.example.springbootvueproject.domain.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberRequest {
    @NotBlank(message = "회원 이름을 입력해주세요.")
    private String userName;
    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;
    @NotBlank(message = "회원 나이를 입력해주세요.")
    private Integer userAge;
    @NotBlank(message = "회원 이메일을 입력해주세요.")
    private String userEmail;
}
