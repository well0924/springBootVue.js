package com.example.springbootvueproject.config.Exception;

import com.example.springbootvueproject.config.Exception.custom.ApiException;
import com.example.springbootvueproject.config.Exception.dto.apiErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionAdviceController {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<apiErrorDto> exceptionHandler(HttpServletRequest request, final ApiException e) {
        //e.printStackTrace();
        return ResponseEntity
                .status(e.getCustomEnum().getStatus())
                .body(apiErrorDto.builder()
                        .errorCode(e.getCustomEnum().getCode())
                        .errorMessage(e.getCustomEnum().getMessage())
                        .build());
    }
}
