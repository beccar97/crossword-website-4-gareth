package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidClueException extends BaseException {
    public InvalidClueException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST;
    }
}
