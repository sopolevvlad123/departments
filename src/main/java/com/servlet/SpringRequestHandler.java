package com.servlet;

import com.dao.DepartmentDAO;
import com.dao.impl.hiber.HiberDepartmentDao;
import com.exception.AppException;
import com.exception.DAOException;
import com.exception.ServiceException;
import com.sandbox.AutowiredTest;
import com.service.DepartmentService;
import com.servletHandler.ServletHandler;
import com.utils.ServletHandlerFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        } catch (AppException e) {
            logger.error(e);
            request.setAttribute("error", e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }

    }
}
