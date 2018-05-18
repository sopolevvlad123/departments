package com.utils;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext){
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
        dispatcherContext.register(SpringConfig.class);
        String DISPATCHER_NAME = "springDispatcher";
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet(DISPATCHER_NAME, new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

}
