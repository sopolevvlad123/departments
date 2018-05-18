package com.utils;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {
    private final String DISPATCHER_NAME = "springDispatcher";
    private final String SUFFIX = "-config.xml";
    private final String PREFIX = "/WEB-INF/";


    @Override
    public void onStartup(ServletContext servletContext){
        XmlWebApplicationContext appContext = new XmlWebApplicationContext();
        appContext.setConfigLocation(PREFIX + DISPATCHER_NAME + SUFFIX);
        ServletRegistration.Dynamic dispatcher =servletContext.addServlet(DISPATCHER_NAME, new DispatcherServlet(appContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
