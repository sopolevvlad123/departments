package com.servlet;

import com.dao.impl.hiber.HiberDepartmentDao;
import com.exception.AppException;
import com.exception.DAOException;
import com.servletHandler.ServletHandler;
import com.utils.ServletHandlerFactory;
import com.utils.SpringAppConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.utils.ServletHandlerConstants.ERROR_PAGE;

public class FrontControllerServlet extends HttpServlet {
    final static Logger logger = Logger.getLogger(FrontControllerServlet.class);


    private ServletHandlerFactory servlethandlerFactory;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        List<String> beans = Arrays.asList(context.getBeanDefinitionNames());
        System.out.println("beans --- " + beans);

        ServletHandler servletHandler = servlethandlerFactory.getHandler(request.getRequestURI());

        servlethandlerFactory = (ServletHandlerFactory) context.getBean("servletHandlerFactory");


        try {
            servletHandler.execute(request, response);
        } catch (AppException e) {
            logger.error(e);
            request.setAttribute("error", e);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        }
    }

}

