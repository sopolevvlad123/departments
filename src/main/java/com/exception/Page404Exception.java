package com.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class Page404Exception extends Exception {
    public Page404Exception(String message, Throwable e) {
        super(message, e);
    }

    public Page404Exception(String message) {
        super(message);
    }
}
