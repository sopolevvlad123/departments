package com.servletHandler.implement.emploee;

import com.bean.Employee;
import com.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

import static com.utils.ServletHandlerConstants.GET_DEP_EMPLOYEES;
import static com.utils.ServletHandlerConstants.UPDATE_EMPLOYEE_PAGE;

public class UpdateEmployeeHandler extends CreateEmployeeHandler {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, DAOException {
        saveOrUpdateEmployee(buildEmployee(request), request, response, GET_DEP_EMPLOYEES, UPDATE_EMPLOYEE_PAGE);
    }

    protected Employee buildEmployee(HttpServletRequest request) {
        return new Employee(Integer.parseInt(request.getParameter("employeeId")),
                request.getParameter("firstName"),
                request.getParameter("lastName"),
                request.getParameter("email"),
                Integer.parseInt(request.getParameter("salary")),
                Date.valueOf(request.getParameter("hireDate")),
                Integer.parseInt(request.getParameter("departmentId")));
    }
}
