package com.servlet;

import com.exception.AppException;
import com.servletHandler.ServletHandler;
import com.utils.ServletHandlerFactory;
import com.validator.Validator;
import org.apache.log4j.Logger;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.ERROR_PAGE;

public class FrontControllerServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(FrontControllerServlet.class);
    private ServletHandlerFactory servletHandlerFactory = new ServletHandlerFactory();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       /* try {
            servletHandlerFactory.getHandler(request.getRequestURI()).execute(request, response);
        } catch (AppException e) {
            logger.error(e);
            request.setAttribute("error", e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }*/
    }

}

