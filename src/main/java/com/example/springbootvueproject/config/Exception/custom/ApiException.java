package com.example.springbootvueproject.config.Exception.custom;

import com.example.springbootvueproject.config.Constant.CustomEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema
@Getter
public class ApiException extends RuntimeException{
    private CustomEnum customEnum;

    public ApiException(CustomEnum c){
        super(c.getMessage());
        this.customEnum = c;
    }
}
