package com.servlet;

import com.exception.AppException;
import com.servletHandler.ServletHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.ERROR_PAGE;

@Component("springHandler")
public class SpringRequestHandler implements HttpRequestHandler {

    final static Logger logger = Logger.getLogger(SpringRequestHandler.class);
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
           ((ServletHandler) applicationContext.getBean(request.getRequestURI())).execute(request,response);
        } catch (Throwable e) {
            logger.error(e);
            e.printStackTrace();
            request.setAttribute("error", e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }

    }
}
