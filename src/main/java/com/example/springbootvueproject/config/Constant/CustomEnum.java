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

    SECURITY_01(HttpStatus.UNAUTHORIZED, "S0001", "권한이 없습니다."),
    MEMBER_01(HttpStatus.BAD_REQUEST,"M0001","회원이 없습니다."),
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
