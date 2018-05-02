package com.utils;

import com.servletHandler.ServletHandler;
import com.servletHandler.implement.department.*;
import com.servletHandler.implement.emploee.DepartmentEmployeeListHandler;
import com.servletHandler.implement.emploee.SaveEmployeeHandler;
import com.servletHandler.implement.emploee.DeleteEmployeeHandler;

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
    }

    public ServletHandler getHandler(String url) {
        if (url == null){
            return servletHandlerMap.get(PAGE_404);
        }
        return servletHandlerMap.get(url);
    }
}
