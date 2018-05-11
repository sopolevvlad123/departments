package com.utils;

import com.servletHandler.ServletHandler;
import com.servletHandler.implement.Page404Handler;
import com.servletHandler.implement.department.*;
import com.servletHandler.implement.emploee.DepartmentEmployeeListHandler;
import com.servletHandler.implement.emploee.SaveEmployeeHandler;
import com.servletHandler.implement.emploee.DeleteEmployeeHandler;
import com.servletHandler.implement.emploee.PrepareEmployeeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static com.utils.ServletHandlerConstants.*;
public class ServletHandlerFactory {

    private Map<String, ServletHandler> servletHandlerMap = new HashMap<>();




/*
    public ServletHandlerFactory() {
        servletHandlerMap.put(DEFAULT_URL, departmentListHandler);
        servletHandlerMap.put(GET_DEPARTMENT_LIST, departmentListHandler);
        servletHandlerMap.put(DELETE_DEPARTMENT, deleteDepartmentHandler);
        servletHandlerMap.put(SAVE_DEPARTMENT, saveDepartmentHandler);
        servletHandlerMap.put(GET_DEP_EMPLOYEES, departmentEmployeeListHandler);
        servletHandlerMap.put(SAVE_EMPLOYEE, saveEmployeeHandler);
        servletHandlerMap.put(DELETE_EMPLOYEE,deleteEmployeeHandler);
        servletHandlerMap.put(PREPARE_EMPLOYEE,prepareEmployeeHandler);
        servletHandlerMap.put(PREPARE_DEPARTMENT,prepareDepartmentHandler);
        servletHandlerMap.put(PAGE_404,page404Handler);

    }

    public ServletHandler getHandler(String url) {
        if (servletHandlerMap.get(url) == null){
            return servletHandlerMap.get(PAGE_404);
        }
        return servletHandlerMap.get(url);
    }*/
}
