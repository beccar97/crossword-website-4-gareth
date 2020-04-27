package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException{
    public NotFoundException(String message) {
        super(message);
        this.code = HttpStatus.NOT_FOUND;
    }
}
