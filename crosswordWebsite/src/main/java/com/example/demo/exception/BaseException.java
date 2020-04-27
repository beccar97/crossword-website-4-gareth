package com.example.demo.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException{
    protected HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseException(String message) {
        super(message);
    }

    public HttpStatus getCode() {
        return code;
    }
}
