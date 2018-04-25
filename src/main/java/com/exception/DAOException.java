package com.exception;

import java.sql.SQLException;

public class DAOException extends SQLException {

    public DAOException(String message, Throwable e){
        super(message,e);
    }
}
