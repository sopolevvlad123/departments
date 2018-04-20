package com.servlet;

import com.servletHandler.ServletHandler;
import com.utils.ServletHandlerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontControllerServlet extends HttpServlet {

    private ServletHandlerFactory servlethandlerFactory = new ServletHandlerFactory();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletHandler servletHandler = servlethandlerFactory.getHandler(request.getRequestURI());
        servletHandler.execute(request, response);
    }

}

