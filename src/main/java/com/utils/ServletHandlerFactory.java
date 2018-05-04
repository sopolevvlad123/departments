package com.utils;

import com.servletHandler.ServletHandler;
import com.servletHandler.implement.Page404Handler;
import com.servletHandler.implement.department.*;
import com.servletHandler.implement.emploee.DepartmentEmployeeListHandler;
import com.servletHandler.implement.emploee.SaveEmployeeHandler;
import com.servletHandler.implement.emploee.DeleteEmployeeHandler;
import com.servletHandler.implement.emploee.PrepareEmployeeHandler;

import java.util.HashMap;
import java.util.Map;

import static com.utils.ServletHandlerConstants.*;

public class ServletHandlerFactory {

    private Map<String, ServletHandler> servletHandlerMap = new HashMap<>();

    public ServletHandlerFactory() {
        servletHandlerMap.put(DEFAULT_URL, new DepartmentListHandler());
        servletHandlerMap.put(GET_DEPARTMENT_LIST, new DepartmentListHandler());
        servletHandlerMap.put(DELETE_DEPARTMENT, new DeleteDepartmentHandler());
        servletHandlerMap.put(SAVE_DEPARTMENT, new SaveDepartmentHandler());
        servletHandlerMap.put(GET_DEP_EMPLOYEES, new DepartmentEmployeeListHandler());
        servletHandlerMap.put(SAVE_EMPLOYEE, new SaveEmployeeHandler());
        servletHandlerMap.put(DELETE_EMPLOYEE, new DeleteEmployeeHandler());
        servletHandlerMap.put(PREPARE_EMPLOYEE,new PrepareEmployeeHandler());
        servletHandlerMap.put(PREPARE_DEPARTMENT,new PrepareDepartmentHandler());
        servletHandlerMap.put(TO_404_PAGE,new Page404Handler());

    }

    public ServletHandler getHandler(String url) {
        System.out.println("url -- " + url);
        if (servletHandlerMap.get(url) == null){
            return servletHandlerMap.get(TO_404_PAGE);
        }
        return servletHandlerMap.get(url);
    }
}
