package com.exception;


public class DAOException extends Exception {

    public DAOException(String message, Throwable e){
        super(message,e);
    }
}
