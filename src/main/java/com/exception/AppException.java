package com.exception;

public class AppException extends Exception {

    public AppException(String message, Throwable e){
        super(message,e);
    }

}
