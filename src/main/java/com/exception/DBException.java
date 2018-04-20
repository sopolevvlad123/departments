package com.exception;

public class DBException extends RuntimeException {

    public DBException(String message, Throwable e){
        super(message,e);
    }
}
