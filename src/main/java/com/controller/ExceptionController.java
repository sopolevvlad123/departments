package com.controller;

import com.exception.AppException;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler  {

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

    @ExceptionHandler(value = NoHandlerFoundException.class )
    public String sss(Throwable throwable, Model model){
        throwable.printStackTrace();
        logger.error(throwable);
        model.addAttribute("error",new Exception("something goes wrong"));
        return "errorPage";
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("sdfsdfsdfd00000000000000000000000000000000000000000000000000000000");
        return handleExceptionInternal(ex, "errorPage", headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(
            MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        System.out.println("sdfsdfsdfd00000000000000000000000000000000000000000000000000000000");

        return handleExceptionInternal(ex, null, headers, status, request);
    }


}
