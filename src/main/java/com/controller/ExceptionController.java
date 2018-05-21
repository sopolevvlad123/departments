package com.controller;

import com.exception.AppException;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController   {

    private final static Logger logger = Logger.getLogger(ExceptionController.class);

    @ExceptionHandler(value = AppException.class )
    public String appExceptionHandle(AppException e, Model model){
        model.addAttribute("error",e);
        return "errorPage";
    }

    @ExceptionHandler(value = Throwable.class )
    public String generalExceptionHandle(Throwable throwable, Model model){
        throwable.printStackTrace();
        logger.error(throwable);
        model.addAttribute("error",new Exception("Something gone wrong"));
        return "errorPage";
    }

    @ExceptionHandler(value = NoHandlerFoundException.class )
    public String get404Page(Throwable throwable, Model model){
        throwable.printStackTrace();
        logger.error(throwable);
        String address = throwable.getMessage().substring(throwable.getMessage().lastIndexOf("/") );
        model.addAttribute("errorMessage","Page with address " + address + " does not exist");
        return "page404";
    }
}
