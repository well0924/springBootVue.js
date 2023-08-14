package com.example.springbootvueproject.config.Constant;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum CustomEnum {
    RUNTIME_EXCEPTION(HttpStatus.BAD_REQUEST, "E0001"),
    ACCESS_DENIED_EXCEPTION(HttpStatus.UNAUTHORIZED, "E0002"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "E0003"),
    //security jwt 관련
    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다."),
    SECURITY_02(HttpStatus.BAD_REQUEST,"S0002","아이디와 비밀번호가 일치하지 않습니다."),
    EMPTY_ACCESS_JWT(HttpStatus.BAD_REQUEST,"S0003", "Access 토큰을 입력해주세요."),
    EMPTY_REFRESH_JWT( HttpStatus.BAD_REQUEST, "S0004","Refresh 토큰을 입력해주세요."),
    INVALID_JWT( HttpStatus.BAD_REQUEST,"S0005", "지원되지 않거나 잘못된 토큰 입니다."),
    NOT_EXIST_REFRESH_JWT(HttpStatus.BAD_REQUEST,"S0006","존재하지 않거나 만료된 Refresh 토큰입니다. 다시 로그인해주세요."),
    EXPIRED_JWT(HttpStatus.INTERNAL_SERVER_ERROR,"S0007","만료된 Access 토큰입니다. Refresh 토큰을 이용해서 새로운 Access 토큰을 발급 받으세요."),
    //member 관련
    MEMBER_01(HttpStatus.BAD_REQUEST,"M0001","회원이 없습니다."),
    MEMBER_02(HttpStatus.BAD_REQUEST,"M0002","비밀번호가 일치하지 않습니다."),
    //post 관련
    POST_01(HttpStatus.BAD_REQUEST,"P0001","게시글이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private String message;

    CustomEnum(HttpStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    CustomEnum(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
