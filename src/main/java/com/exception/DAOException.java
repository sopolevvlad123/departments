package com.exception;


public class DAOException extends Throwable {

    public DAOException(String message, Throwable e){
        super(message,e);
    }
}
