package com.utils;

import com.servletHandler.ServletHandler;
import com.servletHandler.implement.department.*;
import com.servletHandler.implement.emploee.CreateEmployeeHandler;
import com.servletHandler.implement.emploee.DeleteEmployeeHandler;
import com.servletHandler.implement.emploee.UpdateEmployeeHandler;

import java.util.HashMap;
import java.util.Map;

import static com.utils.ServletHandlerConstants.*;

public class ServletHandlerFactory {

    private Map<String, ServletHandler> servletHandlerMap = new HashMap<>();

    public ServletHandlerFactory() {
        servletHandlerMap.put(DEFAULT_URL, new DepartmentListHandler());
        servletHandlerMap.put(GET_DEPARTMENT_LIST, new DepartmentListHandler());
        servletHandlerMap.put(DELETE_DEPARTMENT, new DeleteDepartmentHandler());
        servletHandlerMap.put(CREATE_DEPARTMENT, new CreateDepartmentHandler());
        servletHandlerMap.put(UPDATE_DEPARTMENT, new UpdateDepartmentHandler());
        servletHandlerMap.put(GET_DEP_EMPLOYEES, new DepartmentEmployeeListHandler());
        servletHandlerMap.put(CREATE_EMPLOYEE, new CreateEmployeeHandler());
        servletHandlerMap.put(DELETE_EMPLOYEE, new DeleteEmployeeHandler());
        servletHandlerMap.put(UPDATE_EMPLOYEE, new UpdateEmployeeHandler());
        servletHandlerMap.put(PAGE_404, new UpdateEmployeeHandler());

    }

    public ServletHandler getHandler(String url) {

        return servletHandlerMap.get(url);
    }
}
