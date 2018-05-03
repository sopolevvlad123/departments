package com.listener;

import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Logger globalLogger = Logger.getRootLogger();
        servletContextEvent.getServletContext().setAttribute("appLogger",globalLogger);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
