package com.controller;

import com.exception.AppException;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

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
        model.addAttribute("error",new Exception("something goes wrong"));
        return "errorPage";
    }
}
