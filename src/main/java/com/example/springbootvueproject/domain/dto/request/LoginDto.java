package com.example.springbootvueproject.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class LoginDto {
    @NotBlank(message = "회원 이름을 입력해 주세요.")
    private String userName;
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
