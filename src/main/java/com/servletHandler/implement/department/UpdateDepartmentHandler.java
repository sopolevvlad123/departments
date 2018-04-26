package com.servletHandler.implement.department;

import com.bean.Department;
import com.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.utils.ServletHandlerConstants.GET_DEPARTMENT_LIST;
import static com.utils.ServletHandlerConstants.UPDATE_DEPARTMENT_PAGE;


public class UpdateDepartmentHandler extends CreateDepartmentHandler {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DAOException {
        saveOrUpdateDepartment(buildDepartment(request), request, response, UPDATE_DEPARTMENT_PAGE,GET_DEPARTMENT_LIST);

    }

    Department buildDepartment(HttpServletRequest request) {
        return new Department(Integer.parseInt(request.getParameter("departmentId")),
                request.getParameter("departmentName"));
    }

}
