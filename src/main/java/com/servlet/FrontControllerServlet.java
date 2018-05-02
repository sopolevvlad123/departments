package com.servlet;

import com.exception.DAOException;
import com.servletHandler.ServletHandler;
import com.utils.HibernateSessionFactory;
import com.utils.ServletHandlerFactory;
import org.apache.log4j.Logger;

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
        try {
            servletHandler.execute(request, response);
        } catch (DAOException e) {
            Logger log = (Logger)request.getSession().getServletContext().getAttribute("appLogger");
            log.error(e);
            throw new ServletException(e);
        }
    }

}

