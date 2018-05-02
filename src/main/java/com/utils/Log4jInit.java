package com.utils;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


public class Log4jInit extends HttpServlet {
    @Override
    public void init(){
        String log4jProperties = getInitParameter("log4jProperties");
        PropertyConfigurator.configure(log4jProperties);
        Logger globalLogger = Logger.getRootLogger();
        getServletContext().setAttribute(new String("appLogger"), globalLogger);
    }




    }