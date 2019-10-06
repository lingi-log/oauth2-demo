package com.oauth2resourceserver.base.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.http.HttpStatus;

// @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BaseException extends RuntimeException{
    public BaseException() {
        super();
    }
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
    public BaseException(String message) {
        super(message);
    }
    public BaseException(Throwable cause) {
        super(cause);
    }
}