package com.example.springbootvueproject.config.Exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class apiErrorDto {
    private String errorCode;
    private String errorMessage;

    @Builder
    public apiErrorDto(HttpStatus httpStatus,String errorCode,String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
