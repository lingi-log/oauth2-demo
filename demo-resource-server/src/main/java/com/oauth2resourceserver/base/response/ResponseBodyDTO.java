package com.oauth2resourceserver.base.response;

import com.oauth2resourceserver.base.response.code.ResponseCodeEnum;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResponseBodyDTO<E> {
    private ResponseCodeEnum responseCode;
    private E data;

    public ResponseBodyDTO(){
        this.responseCode = ResponseCodeEnum.S_200;
    }
}